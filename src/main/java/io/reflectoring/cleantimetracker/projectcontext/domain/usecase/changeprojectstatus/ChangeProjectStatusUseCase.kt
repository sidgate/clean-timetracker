package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.changeprojectstatus

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryProjectsPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.UpdateProjectPort
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.ProjectNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ChangeProjectStatusUseCase(private val updateProjectPort: UpdateProjectPort,
                                 private val queryProjectsPort: QueryProjectsPort) {
    fun activateProject(projectId: ProjectId?) {
        val optionalProject = queryProjectsPort.findOne(projectId)
        if (optionalProject.isPresent) {
            updateProjectPort.changeStatus(optionalProject.get(), ProjectStatus.ACTIVE)
        } else {
            throw ProjectNotFoundException(projectId!!)
        }
    }

    fun deactivateProject(projectId: ProjectId?) {
        val optionalProject = queryProjectsPort.findOne(projectId)
        if (optionalProject.isPresent) {
            updateProjectPort.changeStatus(optionalProject.get(), ProjectStatus.INACTIVE)
        } else {
            throw ProjectNotFoundException(projectId!!)
        }
    }

}