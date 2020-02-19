package io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId

interface QueryTasksPort {
    fun listTasksForProject(projectId: ProjectId?): List<Task>
    fun findOne(taskId: TaskId): Task?
    fun listByIds(taskIds: List<TaskId>): List<Task>
    fun listAllTasks(): List<Task>
}