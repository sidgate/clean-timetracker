package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.edit

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
internal class TaskModelMapper {
    private fun toModel(domainObject: Task): TaskModel {
        return TaskModel(
                id = (domainObject.id!!.value)
                , invoiceable = (domainObject.invoiceable)
                , name = (domainObject.name)
                , status = (domainObject.status)
        )
    }

    fun toModels(domainObjects: List<Task>): List<TaskModel> {
        return domainObjects.stream()
                .map { domainObject: Task -> toModel(domainObject) }
                .collect(Collectors.toList())
    }
}