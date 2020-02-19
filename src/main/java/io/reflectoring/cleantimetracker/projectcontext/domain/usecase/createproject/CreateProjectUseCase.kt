package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.createproject

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.CreateProjectPort
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class CreateProjectUseCase(private val createProjectPort: CreateProjectPort) {
    fun createProject(name: String?): Project {
        val project = Project(name = name, status = ProjectStatus.INACTIVE)
        return createProjectPort.createProject(project)
    }

}