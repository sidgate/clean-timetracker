package io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus

interface UpdateTaskPort {
    fun changeStatus(task: Task, status: TaskStatus)
}