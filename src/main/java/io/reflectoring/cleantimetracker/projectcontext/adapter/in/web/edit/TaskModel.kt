package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.edit

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus


internal class TaskModel(
        var id: Long? = null,
        var name: String? = null,
        var invoiceable: Boolean? = null,
        var status: TaskStatus? = null
) {
    val isActive: Boolean
        get() = status == TaskStatus.ACTIVE
}