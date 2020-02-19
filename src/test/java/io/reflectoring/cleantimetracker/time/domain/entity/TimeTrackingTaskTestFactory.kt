package io.reflectoring.cleantimetracker.time.domain.entity

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import java.util.*

object TimeTrackingTaskTestFactory {
    @JvmStatic
    fun defaultTasks(taskIds: Array<Long>): List<TimeTrackingTask> {
        val tasks: MutableList<TimeTrackingTask> = ArrayList()
        for (taskId in taskIds) {
            tasks.add(defaultTask(taskId))
        }
        return tasks
    }

    fun defaultTask(taskId: Long): TimeTrackingTask {
        return TimeTrackingTask(
                active = true
                , id = (taskId)
                , invoiceable = true
                , name = ("Task $taskId")
                , projectId = (42L)
                , projectName = ("Project 42")
        )
    }
}