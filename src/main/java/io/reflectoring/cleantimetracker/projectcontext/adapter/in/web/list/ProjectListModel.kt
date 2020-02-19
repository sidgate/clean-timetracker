package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.list

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus

internal class ProjectListModel(
        val id: Long? = null,
        val name: String? = null,
        val status: ProjectStatus? = null
) {
    val isActive: Boolean
        get() = status == ProjectStatus.ACTIVE
}