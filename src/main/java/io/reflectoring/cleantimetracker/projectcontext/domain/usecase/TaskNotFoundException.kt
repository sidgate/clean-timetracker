package io.reflectoring.cleantimetracker.projectcontext.domain.usecase

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId

class TaskNotFoundException(taskId: TaskId) : RuntimeException(String.format("Task with ID %d not found!", taskId.value))