package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.*
import org.assertj.core.api.Java6Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.persistence.EntityManager

@ExtendWith(SpringExtension::class)
@DataJpaTest
@Import(ProjectEntityMapper::class, TaskPersistenceAdapter::class, TaskEntityMapper::class, TaskEntityTestFactory::class, ProjectEntityTestFactory::class)
@TestPropertySource(properties = ["spring.datasource.data=data-test.sql"])
internal class TaskPersistenceAdapterIntegrationTest {
    @Autowired
    private val taskPersistence: TaskPersistenceAdapter? = null
    @Autowired
    private val projectEntityTestFactory: ProjectEntityTestFactory? = null
    @Autowired
    private val taskEntityTestFactory: TaskEntityTestFactory? = null
    @Autowired
    private val em: EntityManager? = null

    @Test
    @Sql(ProjectEntityTestFactory.SQL)
    fun savesNewTask() {
        val project = projectEntityTestFactory!!.defaultProject()
        val task = Task(null, "Task", null,
                Project(
                        ProjectId.of(project.id!!), null, null
                ), null)
        val savedTask = taskPersistence!!.saveTask(task)
        Java6Assertions.assertThat(savedTask?.id).isNotNull()
    }

    @Test
    @Sql(TaskEntityTestFactory.SQL)
    fun updatesStatus() {
        val taskEntity = taskEntityTestFactory!!.defaultTask()
        val task = Task(TaskId.of(taskEntity.id!!), null, null, null, null)
        em!!.clear()
        taskPersistence!!.changeStatus(task, TaskStatus.ACTIVE)
        Java6Assertions.assertThat(taskEntityTestFactory.defaultTask().status).isEqualTo(TaskStatus.ACTIVE)
        em.clear()
        taskPersistence.changeStatus(task, TaskStatus.INACTIVE)
        Java6Assertions.assertThat(taskEntityTestFactory.defaultTask().status).isEqualTo(TaskStatus.INACTIVE)
    }
}