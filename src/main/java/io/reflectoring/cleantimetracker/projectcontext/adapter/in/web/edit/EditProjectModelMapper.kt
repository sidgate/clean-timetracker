package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.edit

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import org.springframework.stereotype.Component

@Component
internal class EditProjectModelMapper(private val taskModelMapper: TaskModelMapper) {
    fun toModel(domainObject: Project, tasks: List<Task>): EditProjectModel {
        return EditProjectModel(
                id = (domainObject.id!!.value)
                , name = (domainObject.name)
                , status = (domainObject.status)
                , tasks = (taskModelMapper.toModels(tasks))
        )
    }

}