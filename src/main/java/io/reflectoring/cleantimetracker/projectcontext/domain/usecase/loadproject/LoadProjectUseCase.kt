package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.loadproject

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryProjectsPort
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.ProjectNotFoundException
import org.springframework.stereotype.Service

@Service
class LoadProjectUseCase(private val queryPort: QueryProjectsPort) {
    fun loadProject(projectId: ProjectId?): Project {
        val optionalProject = queryPort.findOne(projectId)
        return if (optionalProject.isPresent) {
            optionalProject.get()
        } else {
            throw ProjectNotFoundException(projectId!!)
        }
    }

}