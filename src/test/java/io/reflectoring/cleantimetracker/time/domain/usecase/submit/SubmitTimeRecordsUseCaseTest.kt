package io.reflectoring.cleantimetracker.time.domain.usecase.submit

import io.reflectoring.cleantimetracker.MockitoExtension
import io.reflectoring.cleantimetracker.time.domain.entity.TimeTrackingTaskTestFactory.defaultTasks
import io.reflectoring.cleantimetracker.time.domain.usecase.submit.SubmitTimeRecordInputDataTestFactory.eightHours
import io.reflectoring.cleantimetracker.time.domain.usecase.submit.SubmitTimeRecordInputDataTestFactory.thirteenHours
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence.SaveTimeRecordsPort
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.projectcontext.QueryTasksPort
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit.SubmitTimeRecordsUseCase
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit.TooManyRecordsPerDayException
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit.TooMuchTimePerDayException
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit.TooMuchTimePerRecordException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class SubmitTimeRecordsUseCaseTest {
    @InjectMocks
    private val usecase: SubmitTimeRecordsUseCase? = null
    @Mock
    private val saveTimeRecordsPort: SaveTimeRecordsPort? = null
    @Mock
    private val queryTasksPort: QueryTasksPort? = null

    @Test
    fun whenTooManyMinutesOnSingleRecord_thenThrowsException() {
        val taskId = 42L
        val records = listOf(thirteenHours(taskId))
        whenTasksExist(taskId)
        Assertions.assertThatThrownBy { usecase!!.submitTimeRecords(records) }.isInstanceOf(TooMuchTimePerRecordException::class.java)
    }

    @Test
    fun whenTooManyMinutesPerDayInSubmittedData_thenThrowsException() {
        val records = Arrays.asList(eightHours(1L), eightHours(2L), eightHours(3L), eightHours(4L))
        whenTasksExist(1L, 2L, 3L, 4L)
        Assertions.assertThatThrownBy { usecase!!.submitTimeRecords(records) }.isInstanceOf(TooMuchTimePerDayException::class.java)
    }

    @Test
    fun whenTooManyRecordsPerDayAndTaskInSubmittedData_thenThrowsException() {
        val records = Arrays.asList(eightHours(1L), eightHours(1L), eightHours())
        whenTasksExist(1L)
        Assertions.assertThatThrownBy { usecase!!.submitTimeRecords(records) }.isInstanceOf(TooManyRecordsPerDayException::class.java)
    }

    @Test
    fun whenInputValid_thenSavesRecords() {
        val records = Arrays.asList(eightHours(1L), eightHours(2L))
        val tasks = whenTasksExist(1L, 2L)
        usecase!!.submitTimeRecords(records)
        val savedRecordsCaptor = ArgumentCaptor.forClass<List<TimeRecord>, List<TimeRecord>>(List::class.java as Class<List<TimeRecord>>)
// TODO        Mockito.verify(saveTimeRecordsPort)!!.saveTimeRecords(savedRecordsCaptor.capture())
      //  val savedRecords = savedRecordsCaptor.value
      //  Assertions.assertThat(savedRecords).hasSize(records.size)
    }

    private fun whenTasksExist(vararg taskIds: Long): List<TimeTrackingTask> {
        val tasks = defaultTasks(taskIds.toTypedArray())
        Mockito.`when`(queryTasksPort!!.listByIds(HashSet(taskIds.asList()))).thenReturn(tasks)
        return tasks
    }
}