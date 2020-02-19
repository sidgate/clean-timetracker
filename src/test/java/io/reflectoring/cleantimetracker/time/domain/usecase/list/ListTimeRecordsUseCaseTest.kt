package io.reflectoring.cleantimetracker.time.domain.usecase.list

import io.reflectoring.cleantimetracker.MockitoExtension
import io.reflectoring.cleantimetracker.time.domain.entity.TimeRecordTestFactory
import io.reflectoring.cleantimetracker.time.domain.entity.TimeTrackingTaskTestFactory
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordWithTask
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence.QueryTimeRecordsPort
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.projectcontext.QueryTasksPort
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.list.IntervalEndBeforeStartException
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.list.IntervalTooLongException
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.list.ListTimeRecordsQueryParameters
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.list.ListTimeRecordsUseCase
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.time.LocalDate
import java.util.function.Consumer

@ExtendWith(MockitoExtension::class)
internal class ListTimeRecordsUseCaseTest {
    @InjectMocks
    private val usecase: ListTimeRecordsUseCase? = null
    @Mock
    private val queryTimeRecordsPort: QueryTimeRecordsPort? = null
    @Mock
    private val queryTasksPort: QueryTasksPort? = null

    @Test
    fun whenIntervalMoreThanAMonth_thenThrowsException() {
        val params = ListTimeRecordsQueryParameters(
                start = (LocalDate.of(2018, 10, 1))
                , end = (LocalDate.of(2018, 11, 15))
        )
        Assertions.assertThatThrownBy { usecase!!.listTimeRecords(params) }.isInstanceOf(IntervalTooLongException::class.java)
    }

    @Test
    fun whenEndBeforeStart_thenThrowsException() {
        val params = ListTimeRecordsQueryParameters(
                start = (LocalDate.of(2018, 10, 1))
                , end = (LocalDate.of(2018, 9, 30))
        )
        Assertions.assertThatThrownBy { usecase!!.listTimeRecords(params) }.isInstanceOf(IntervalEndBeforeStartException::class.java)
    }

    @Test
    fun whenInputValid_thenProvidesTimeRecordsWithTask() {
        val start = LocalDate.of(2018, 10, 1)
        val end = LocalDate.of(2018, 10, 5)
        val taskIds = arrayOf(1L, 2L, 3L)
        val params = ListTimeRecordsQueryParameters(
                start = (start),
                end = (end)
        )
        val providedRecords = whenPersistenceProvidesTimeRecords(start, end, taskIds)
        whenProjectContextProvidesTasks(taskIds)
        val records = usecase!!.listTimeRecords(params)
        Assertions.assertThat(records).hasSize(providedRecords.size)
        records.forEach(Consumer { record: TimeRecordWithTask -> Assertions.assertThat(record.task).isNotNull() })
    }

    private fun whenPersistenceProvidesTimeRecords(start: LocalDate, end: LocalDate, taskIds: Array<Long>): List<TimeRecord> {
        val records = TimeRecordTestFactory.defaultRecords(*taskIds)

        //TODO Mockito.`when`(queryTimeRecordsPort!!.listTimeRecords(ArgumentMatchers.eq(start), ArgumentMatchers.eq(end))).thenReturn(records)
        Mockito.`when`(queryTimeRecordsPort!!.listTimeRecords(start, end)).thenReturn(records)

        return records
    }

    private fun whenProjectContextProvidesTasks(taskIds: Array<Long>): List<TimeTrackingTask> {
        val tasks = TimeTrackingTaskTestFactory.defaultTasks(taskIds)
        Mockito.`when`(queryTasksPort!!.listByIds(ArgumentMatchers.anySet())).thenReturn(tasks)
        return tasks
    }
}