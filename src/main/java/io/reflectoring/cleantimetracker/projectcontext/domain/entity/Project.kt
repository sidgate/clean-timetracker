package io.reflectoring.cleantimetracker.projectcontext.domain.entity

class Project(
        var id: ProjectId? = null,
        var name: String? = null,
        var status: ProjectStatus? = null
)