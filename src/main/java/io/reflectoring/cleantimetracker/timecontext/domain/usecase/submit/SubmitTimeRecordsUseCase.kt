package io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordStatus
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence.SaveTimeRecordsPort
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.projectcontext.QueryTasksPort
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
class SubmitTimeRecordsUseCase(private val saveTimeRecordsPort: SaveTimeRecordsPort, private val queryTasksPort: QueryTasksPort) {
    fun submitTimeRecords(records: List<SubmitTimeRecordInputData>) {
        records.forEach(Consumer { record: SubmitTimeRecordInputData -> rejectSingleRecordWhenTooManyMinutes(record) })
        rejectRecordsWhenTooManyMinutesOnDay(records)
        rejectMultipleRecordsPerTaskAndDay(records)
        saveTimeRecordsPort.saveTimeRecords(toTimeRecords(records))
    }

    private fun toTimeRecords(inputRecords: List<SubmitTimeRecordInputData>): List<TimeRecord> {
        val records: MutableList<TimeRecord> = ArrayList()
        for (inputRecord in inputRecords) {
            records.add(TimeRecord(null, inputRecord.taskId, inputRecord.date, inputRecord.minutes, TimeRecordStatus.OPEN)
            )
        }
        return records
    }

    private fun tasksById(records: List<SubmitTimeRecordInputData>): Map<Long, TimeTrackingTask> {
        val taskIds = records.stream()
                .map<Long>(SubmitTimeRecordInputData::taskId)
                .collect(Collectors.toSet())
        return queryTasksPort.listByIds(taskIds)
                .map { it.id to it }.toMap()
    }

    private fun rejectSingleRecordWhenTooManyMinutes(record: SubmitTimeRecordInputData) {
        if (record.minutes!! > MAXIMUM_MINUTES_PER_RECORD) {
            throw TooMuchTimePerRecordException(record.minutes!!, MAXIMUM_MINUTES_PER_RECORD)
        }
    }

    private fun rejectRecordsWhenTooManyMinutesOnDay(records: List<SubmitTimeRecordInputData>) {
        val minutesPerDay = records.stream()
                .collect(Collectors.groupingBy(SubmitTimeRecordInputData::date, Collectors.summingInt { it.minutes!! }))
        minutesPerDay.forEach { (date: LocalDate?, minutes: Int) ->
            if (minutes > MAXIMUM_MINUTES_PER_DAY) {
                throw TooMuchTimePerDayException(minutes, date, MAXIMUM_MINUTES_PER_DAY)
            }
        }
        // TODO: this check should include records already stored out the database
    }

    private fun rejectMultipleRecordsPerTaskAndDay(records: List<SubmitTimeRecordInputData>) {
        val recordsPerDayAndTask = records.stream()
                .collect(Collectors.groupingBy(SubmitTimeRecordInputData::date,
                        Collectors.groupingBy(SubmitTimeRecordInputData::taskId)))
        recordsPerDayAndTask.forEach { (date: LocalDate?, map: Map<Long?, List<SubmitTimeRecordInputData?>>) ->
            map.forEach { (taskId: Long?, recordsList: List<SubmitTimeRecordInputData?>) ->
                if (recordsList.size > 1) {
                    throw TooManyRecordsPerDayException()
                }
            }
        }
        // TODO: this check should include records already stored out the database
    }

    companion object {
        private const val MAXIMUM_MINUTES_PER_RECORD = 12 * 60
        private const val MAXIMUM_MINUTES_PER_DAY = 24 * 60
    }

}