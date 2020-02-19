package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

internal interface TaskEntityRepository : CrudRepository<TaskEntity, Long> {
    fun findByProjectId(projectId: Long): List<TaskEntity>
    @Query("update #{#entityName} t set t.status = :status where t.id = :id")
    @Modifying
    fun updateStatus(@Param("id") taskId: Long, @Param("status") taskStatus: TaskStatus): Int

    fun findByIdIn(ids: List<Long>): List<TaskEntity>
}