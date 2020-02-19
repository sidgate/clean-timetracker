package io.reflectoring.cleantimetracker.projectcontext.domain.entity

data class Task(
        var id: TaskId? = null,
        var name: String? = null,
        var invoiceable: Boolean? = false,
        var project: Project? = null,
        var status: TaskStatus? = TaskStatus.ACTIVE
)