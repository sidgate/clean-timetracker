package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import org.springframework.stereotype.Component

@Component
internal class ProjectEntityTestFactory(private val projectRepository: ProjectRepository) {
    fun defaultProject(): ProjectEntity {
        return projectRepository
                .findById(1L)
                .orElse(null)
    }

    companion object {
        const val SQL = "/io/reflectoring/cleantimetracker/projectcontext/adapter/out/persistence/default-project.sql"
    }

}