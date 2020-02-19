package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.listtasks

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryTasksPort
import org.springframework.stereotype.Service

@Service
class ListTasksUseCase(private val queryTasksPort: QueryTasksPort) {
    fun listTasksForProject(projectId: ProjectId?): List<Task> {
        return queryTasksPort.listTasksForProject(projectId)
    }

}