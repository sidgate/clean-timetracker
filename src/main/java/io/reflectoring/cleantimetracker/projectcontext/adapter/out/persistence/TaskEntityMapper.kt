package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId.Companion.of
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import javax.persistence.EntityManager

@Component
 class TaskEntityMapper(private val entityManager: EntityManager, private val projectEntityMapper: ProjectEntityMapper) {
    fun toEntity(domainObject: Task): TaskEntity {
        return TaskEntity(
                id= (if (domainObject.id != null) domainObject.id!!.value else null)
                ,name =(domainObject.name)
                ,project= (projectReference(domainObject.project!!.id))
                ,invoiceable =(domainObject.invoiceable)
                ,status =(domainObject.status)
                )
    }

    fun toEntities(domainObjects: List<Task>): List<TaskEntity> {
        return domainObjects.stream()
                .map { domainObject: Task -> toEntity(domainObject) }
                .collect(Collectors.toList())
    }

    fun toDomainObject(entity: TaskEntity): Task {
        return Task(
                id = (of(entity.id))
                ,name= (entity.name)
                ,project =(projectEntityMapper.toDomainObject(entity.project))
                ,invoiceable= (entity.invoiceable)
                ,status= (entity.status)
                )
    }

    fun toDomainObjects(entities: Iterable<TaskEntity>): List<Task> {
        val projects: MutableList<Task> = ArrayList()
        entities.forEach(Consumer { entity: TaskEntity -> projects.add(toDomainObject(entity)) })
        return projects
    }

    private fun projectReference(projectId: ProjectId?): ProjectEntity? {
        return if (projectId == null || projectId.value == null) {
            null
        } else entityManager.getReference(ProjectEntity::class.java, projectId.value)
    }

}