package io.reflectoring.cleantimetracker.timecontext.adapter.out.projectcontext

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
 class TaskMapper {
    fun toTimeTrackingTask(task: Task): TimeTrackingTask {
        return TimeTrackingTask().apply {

            projectName = (task.project?.name)
                name= (task.name)
                invoiceable = (task.invoiceable)
                id = (task.id?.value)
                active = (task.status == TaskStatus.ACTIVE)
                projectId = (task.project?.id?.value)
        }
    }

    fun toTimeTrackingTasks(tasks: List<Task>): List<TimeTrackingTask> {
        return tasks.stream()
                .map { task: Task -> toTimeTrackingTask(task) }
                .collect(Collectors.toList())
    }
}