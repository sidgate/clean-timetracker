package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.listprojects

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryProjectsPort
import org.springframework.stereotype.Service

@Service
class ListProjectsUseCase(private val queryPort: QueryProjectsPort) {
    fun listProjects(): List<Project> {
        return queryPort.listProjects()
    }

}