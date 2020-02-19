package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.addtask

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.CreateTaskPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryProjectsPort
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.ProjectNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AddTaskUseCase(private val queryProjectsPort: QueryProjectsPort, private val createTaskPort: CreateTaskPort) {
    fun addTask(taskName: String?, invoiceable: Boolean, projectId: ProjectId) {
        val project = findProjectOrFail(projectId)
        val task = Task(
                invoiceable = invoiceable,
                name = taskName,
                project = project)
        createTaskPort.saveTask(task)
    }

    private fun findProjectOrFail(projectId: ProjectId): Project {
        val project = queryProjectsPort.findOne(projectId)
        return project.orElseThrow { ProjectNotFoundException(projectId) }!!
    }

}