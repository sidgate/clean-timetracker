package io.reflectoring.cleantimetracker.projectcontext.domain.usecase

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId

class ProjectNotFoundException(projectId: ProjectId) : RuntimeException(String.format("Project with ID %d not found!", projectId.value))