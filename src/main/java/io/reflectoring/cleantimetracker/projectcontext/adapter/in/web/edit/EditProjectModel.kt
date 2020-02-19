package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.edit

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus

internal class EditProjectModel(
        val id: Long? = null,
        val name: String? = null,
        val status: ProjectStatus? = null,
        val tasks: List<TaskModel>? = null
) {
    val isActive: Boolean
        get() = status == ProjectStatus.ACTIVE
}