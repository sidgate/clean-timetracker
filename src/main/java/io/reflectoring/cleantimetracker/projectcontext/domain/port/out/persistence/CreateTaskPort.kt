package io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task

interface CreateTaskPort {
    fun saveTask(task: Task?): Task?
}