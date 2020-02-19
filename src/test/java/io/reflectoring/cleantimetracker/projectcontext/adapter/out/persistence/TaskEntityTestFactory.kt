package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import org.springframework.stereotype.Component

@Component
internal class TaskEntityTestFactory(private val taskRepository: TaskEntityRepository) {
    fun defaultTask(): TaskEntity {
        return taskRepository.findById(1L)
                .orElse(null)
    }

    companion object {
        const val SQL = "/io/reflectoring/cleantimetracker/projectcontext/adapter/out/persistence/default-task.sql"
    }

}