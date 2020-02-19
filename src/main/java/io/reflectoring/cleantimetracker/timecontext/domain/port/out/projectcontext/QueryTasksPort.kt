package io.reflectoring.cleantimetracker.timecontext.domain.port.out.projectcontext

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask

interface QueryTasksPort {
    fun listByIds(taskIds: Set<Long?>?): List<TimeTrackingTask?>?
    fun loadTask(taskId: Long?): TimeTrackingTask?
    fun listAllTasks(): List<TimeTrackingTask?>
}