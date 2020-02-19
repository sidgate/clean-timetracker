package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.addtask

import io.reflectoring.cleantimetracker.MockitoExtension
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId.Companion.of
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.CreateTaskPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryProjectsPort
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.ProjectNotFoundException
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.ProjectTestFactory.defaultProject
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class AddTaskUseCaseTest {
    @Mock
    private val queryProjectsPort: QueryProjectsPort? = null
    @Mock
    private val createTaskPort: CreateTaskPort? = null
    @InjectMocks
    private val usecase: AddTaskUseCase? = null

    @Test
    fun whenProjectNotFound_thenFails() {
        val projectId = of(42L)
        Mockito.`when`(queryProjectsPort!!.findOne(projectId)).thenReturn(Optional.empty())
        Assertions.assertThatThrownBy { usecase!!.addTask("My Task", true, projectId) }.isInstanceOf(ProjectNotFoundException::class.java)
    }

    @Test
    fun whenProjectFound_thenCallsSaveTaskPort() {
        val projectId = of(42L)
        Mockito.`when`(queryProjectsPort!!.findOne(projectId)).thenReturn(defaultProject())
        usecase!!.addTask("My Task", true, projectId)
        Mockito.verify(createTaskPort, Mockito.times(1))!!.saveTask(ArgumentMatchers.any(Task::class.java))
    }
}