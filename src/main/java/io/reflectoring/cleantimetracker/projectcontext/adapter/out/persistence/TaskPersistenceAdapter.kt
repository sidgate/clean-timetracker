package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.CreateTaskPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryTasksPort
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.UpdateTaskPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
internal class TaskPersistenceAdapter(private val taskEntityRepository: TaskEntityRepository, private val taskEntityMapper: TaskEntityMapper) : CreateTaskPort, QueryTasksPort, UpdateTaskPort {
    override fun saveTask(task: Task?): Task? {
        val entity = taskEntityMapper.toEntity(task!!)
        val savedEntity = taskEntityRepository.save(entity)
        return taskEntityMapper.toDomainObject(savedEntity)
    }

    override fun listTasksForProject(projectId: ProjectId?): List<Task> {
        val tasks = taskEntityRepository.findByProjectId(projectId!!.value)
        return taskEntityMapper.toDomainObjects(tasks)
    }

    override fun findOne(taskId: TaskId): Task? {
        return taskEntityRepository.findByIdOrNull(taskId.value)?.let {
            taskEntityMapper.toDomainObject(it)
        }
    }

    override fun listByIds(taskIds: List<TaskId>): List<Task> {
        val taskEntities = taskEntityRepository.findByIdIn(taskIds
                .mapNotNull { it.value }
                .toList())
        return taskEntityMapper.toDomainObjects(taskEntities)
    }

    override fun listAllTasks(): List<Task> {
        return taskEntityMapper.toDomainObjects(taskEntityRepository.findAll())
    }

    override fun changeStatus(task: Task, status: TaskStatus) {
        taskEntityRepository.updateStatus(task.id!!.value, status)
    }

}