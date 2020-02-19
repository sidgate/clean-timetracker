package io.reflectoring.cleantimetracker.projectcontext.domain.usecase

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId.Companion.of
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus
import java.util.*

object TaskTestFactory {
    @JvmStatic
    fun defaultTask(): Optional<Task> {
        return Optional.of(Task(
                name= ("Task 1")
                ,id= (of(1L))
                ,project=(ProjectTestFactory.defaultProject().get())
                ,invoiceable=true
                ,status= (TaskStatus.ACTIVE)
                ))
    }
}