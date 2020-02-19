package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.changetaskstatus

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryTasksPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.UpdateTaskPort
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.TaskNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ChangeTaskStatusUseCase(private val queryTasksPort: QueryTasksPort, private val updateTaskPort: UpdateTaskPort) {
    fun deactivateTask(taskId: TaskId?) {
        val optionalTask = queryTasksPort.findOne(taskId!!)
        if (optionalTask !=null) {
            updateTaskPort.changeStatus(optionalTask, TaskStatus.INACTIVE)
        } else {
            throw TaskNotFoundException(taskId)
        }
    }

    fun activateTask(taskId: TaskId?) {
        val optionalTask  = queryTasksPort.findOne(taskId!!)
        if (optionalTask !=null) {
            updateTaskPort.changeStatus(optionalTask, TaskStatus.ACTIVE)
        } else {
            throw TaskNotFoundException(taskId)
        }
    }

}