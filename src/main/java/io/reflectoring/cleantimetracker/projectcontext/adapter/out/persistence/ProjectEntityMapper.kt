package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId.Companion.of
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

@Component
class ProjectEntityMapper {
    fun toEntity(domainObject: Project): ProjectEntity {
        return ProjectEntity(
                id = (if (domainObject.id != null) domainObject.id!!.value else null)
                , name = (domainObject.name)
                , status = (domainObject.status)
        )
    }

    fun toEntities(domainObjects: List<Project>): List<ProjectEntity> {
        return domainObjects.stream()
                .map { domainObject: Project -> toEntity(domainObject) }
                .collect(Collectors.toList())
    }

    fun toDomainObject(entity: ProjectEntity?): Project {
        return Project(
                id = (of(entity?.id!!))
                , name = (entity.name)
                , status = (entity.status)
        )
    }

    fun toDomainObjects(entities: Iterable<ProjectEntity>): List<Project> {
        val projects: MutableList<Project> = ArrayList()
        entities.forEach(Consumer { entity: ProjectEntity -> projects.add(toDomainObject(entity)) })
        return projects
    }
}