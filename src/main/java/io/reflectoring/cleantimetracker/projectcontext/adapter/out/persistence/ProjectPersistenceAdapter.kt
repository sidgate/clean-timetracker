package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.CreateProjectPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryProjectsPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.UpdateProjectPort
import org.springframework.stereotype.Service
import java.util.*

@Service
internal class ProjectPersistenceAdapter(private val projectRepository: ProjectRepository, private val projectEntityMapper: ProjectEntityMapper) : CreateProjectPort, QueryProjectsPort, UpdateProjectPort {
    override fun createProject(project: Project?): Project {
        val entity = projectEntityMapper.toEntity(project!!)
        val savedEntity = projectRepository.save(entity)
        return projectEntityMapper.toDomainObject(savedEntity)
    }

    override fun listProjects(): List<Project> {
        val entities = projectRepository.findAll()
        return projectEntityMapper.toDomainObjects(entities)
    }

    override fun findOne(projectId: ProjectId?): Optional<Project> {
        val optionalProject = projectRepository.findById(projectId!!.value)
        return optionalProject.map { projectEntity: ProjectEntity? -> projectEntityMapper.toDomainObject(projectEntity) }
    }

    override fun changeStatus(project: Project?, status: ProjectStatus?) {
        projectRepository.updateStatus(project!!.id!!.value, status)
    }

}