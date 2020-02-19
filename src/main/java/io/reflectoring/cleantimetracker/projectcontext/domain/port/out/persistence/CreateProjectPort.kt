package io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project

interface CreateProjectPort {
    /**
     * Persists a new project.
     */
    fun createProject(project: Project?): Project
}