package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.timecontext

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId.Companion.of
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryTasksPort
import org.springframework.stereotype.Service

@Service
class TimeContextAdapter(private val queryTasksPort: QueryTasksPort) {
    fun listTasksByIds(taskIds: List<Long>): List<Task> {
        return queryTasksPort.listByIds(taskIds
                .map { obj-> of(obj) }
                .toList())
    }

    fun loadTask(taskId: Long) = queryTasksPort.findOne(of(taskId))

    fun listAll(): List<Task> {
        return queryTasksPort.listAllTasks()
    }

}