package io.reflectoring.cleantimetracker.timecontext.domain.usecase.list

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordWithTask
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence.QueryTimeRecordsPort
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.projectcontext.QueryTasksPort
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class ListTimeRecordsUseCase(private val queryTimeRecordsPort: QueryTimeRecordsPort, private val queryTasksPort: QueryTasksPort) {
    fun listTimeRecords(queryParams: ListTimeRecordsQueryParameters): List<TimeRecordWithTask> {
        rejectIfEndBeforeStart(queryParams)
        rejectIfIntervalIsTooLong(queryParams)
        val timeRecords = queryTimeRecordsPort.listTimeRecords(queryParams.start, queryParams.end)
        return expandTasks(timeRecords)
    }

    fun listAllTasks(): List<TimeTrackingTask> = queryTasksPort.listAllTasks()

    /**
     * Expands the task ID out a set of [TimeRecord]s to the real Task data. This is necessary since
     * the Task data is loaded from another bounded context.
     */
    private fun expandTasks(timeRecords: List<TimeRecord>): List<TimeRecordWithTask> {
        val taskIds = timeRecords.map { obj: TimeRecord -> obj.taskId }.toSet()

        val tasksById: Map<Long?, TimeTrackingTask> = queryTasksPort.listByIds(taskIds).map { it.id to it }.toMap()


        val expandedTimeRecords: MutableList<TimeRecordWithTask> = ArrayList()
        timeRecords.forEach(Consumer { record: TimeRecord -> expandedTimeRecords.add(TimeRecordWithTask.fromTimeRecord(record, tasksById[record.taskId])) })
        return expandedTimeRecords
    }

    private fun rejectIfEndBeforeStart(queryParameters: ListTimeRecordsQueryParameters) {
        if (queryParameters.start?.isAfter(queryParameters.end) == true) {
            throw IntervalEndBeforeStartException(queryParameters.start, queryParameters.end)
        }
    }

    private fun rejectIfIntervalIsTooLong(queryParameters: ListTimeRecordsQueryParameters) {
        val latestAllowedEnd = queryParameters.start?.plusDays(INTERVAL_MAXIMUM_DAYS.toLong())
        if (queryParameters.end?.isAfter(latestAllowedEnd) == true) {
            throw IntervalTooLongException(queryParameters.start, queryParameters.end, INTERVAL_MAXIMUM_DAYS)
        }
    }

    companion object {
        private const val INTERVAL_MAXIMUM_DAYS = 31
    }

}