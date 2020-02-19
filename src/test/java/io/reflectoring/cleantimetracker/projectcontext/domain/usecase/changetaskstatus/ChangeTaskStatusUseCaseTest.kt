package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.changetaskstatus

import io.reflectoring.cleantimetracker.MockitoExtension
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryTasksPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.UpdateTaskPort
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.TaskNotFoundException
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.TaskTestFactory.defaultTask
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class ChangeTaskStatusUseCaseTest {
    @InjectMocks
    private val usecase: ChangeTaskStatusUseCase? = null
    @Mock
    private val queryTasksPort: QueryTasksPort? = null
    @Mock
    private val updateTaskPort: UpdateTaskPort? = null

    @Test
    fun whenTaskIsFound_activatesTask() {
        val task = defaultTask().get()
        Mockito.`when`(queryTasksPort!!.findOne(task.id)).thenReturn(Optional.of(task))
        usecase!!.activateTask(task.id)
        Mockito.verify(updateTaskPort, Mockito.times(1))!!.changeStatus(ArgumentMatchers.eq(task), ArgumentMatchers.eq(TaskStatus.ACTIVE))
    }

    @Test
    fun whenTaskIsNotFound_activateThrowsException() {
        val task = defaultTask().get()
        Mockito.`when`(queryTasksPort!!.findOne(task.id)).thenReturn(Optional.empty())
        Assertions.assertThatThrownBy { usecase!!.activateTask(task.id) }.isInstanceOf(TaskNotFoundException::class.java)
    }

    @Test
    fun whenTaskIsFound_deactivatesTask() {
        val task = defaultTask().get()
        Mockito.`when`(queryTasksPort!!.findOne(task.id)).thenReturn(Optional.of(task))
        usecase!!.deactivateTask(task.id)
        Mockito.verify(updateTaskPort, Mockito.times(1))!!.changeStatus(ArgumentMatchers.eq(task), ArgumentMatchers.eq(TaskStatus.INACTIVE))
    }

    @Test
    fun whenTaskIsNotFound_deactivateThrowsException() {
        val task = defaultTask().get()
        Mockito.`when`(queryTasksPort!!.findOne(task.id)).thenReturn(Optional.empty())
        Assertions.assertThatThrownBy { usecase!!.deactivateTask(task.id) }.isInstanceOf(TaskNotFoundException::class.java)
    }
}