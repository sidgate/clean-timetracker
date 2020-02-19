package io.reflectoring.cleantimetracker.projectcontext.domain.usecase

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId.Companion.of
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus
import java.util.*

object ProjectTestFactory {
    @JvmStatic
    fun defaultProject(): Optional<Project> {
        return Optional.of(Project(
                id = (of(1L))
                , status = (ProjectStatus.ACTIVE)
                , name = ("Project 1")
        ))
    }
}