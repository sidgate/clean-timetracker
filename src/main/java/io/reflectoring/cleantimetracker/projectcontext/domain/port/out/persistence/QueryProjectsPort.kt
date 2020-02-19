package io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import java.util.*

interface QueryProjectsPort {
    fun listProjects(): List<Project>
    fun findOne(projectId: ProjectId?): Optional<Project>
}