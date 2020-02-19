package io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus

interface UpdateProjectPort {
    fun changeStatus(project: Project?, status: ProjectStatus?)
}