package io.reflectoring.cleantimetracker.timecontext.adapter.out.projectcontext

import io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.timecontext.TimeContextAdapter
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.projectcontext.QueryTasksPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectContextAdapter(private val timeContextAdapter: TimeContextAdapter, private val taskMapper: TaskMapper) : QueryTasksPort {
    override fun listByIds(taskIds: Set<Long>): List<TimeTrackingTask?>? {
        val tasks: List<Task> = timeContextAdapter.listTasksByIds(ArrayList(taskIds))
        return taskMapper.toTimeTrackingTasks(tasks)
    }

    override fun loadTask(taskId: Long): TimeTrackingTask? {
        val task = timeContextAdapter.loadTask(taskId)
        return if (task != null) {
            taskMapper.toTimeTrackingTask(task)
        } else {
            null
        }
    }

    override fun listAllTasks(): List<TimeTrackingTask?> {
        return taskMapper.toTimeTrackingTasks(timeContextAdapter.listAll())
    }

}