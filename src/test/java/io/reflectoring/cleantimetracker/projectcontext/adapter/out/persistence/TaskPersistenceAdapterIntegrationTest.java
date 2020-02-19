package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence;

import javax.persistence.EntityManager;

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project;
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId;
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Task;
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId;
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Java6Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({
        ProjectEntityMapper.class,
        TaskPersistenceAdapter.class,
        TaskEntityMapper.class,
        TaskEntityTestFactory.class,
        ProjectEntityTestFactory.class
})
@TestPropertySource(properties = "spring.datasource.data=data-test.sql")
class TaskPersistenceAdapterIntegrationTest {

  @Autowired
  private TaskPersistenceAdapter taskPersistence;

  @Autowired
  private ProjectEntityTestFactory projectEntityTestFactory;

  @Autowired
  private TaskEntityTestFactory taskEntityTestFactory;

  @Autowired
  private EntityManager em;

  @Test
  @Sql(ProjectEntityTestFactory.SQL)
  void savesNewTask() {
    ProjectEntity project = projectEntityTestFactory.defaultProject();
    Task task = new Task(null,"Task", null,
            new Project(
                    ProjectId.of(project.getId()), null, null
                    ), null)
            ;
    Task savedTask = taskPersistence.saveTask(task);
    assertThat(savedTask.getId()).isNotNull();
  }

  @Test
  @Sql(TaskEntityTestFactory.SQL)
  void updatesStatus() {
    TaskEntity taskEntity = taskEntityTestFactory.defaultTask();
    Task task = new Task(TaskId.of(taskEntity.getId()), null, null, null, null);

    em.clear();
    taskPersistence.changeStatus(task, TaskStatus.ACTIVE);
    assertThat(taskEntityTestFactory.defaultTask().getStatus()).isEqualTo(TaskStatus.ACTIVE);

    em.clear();
    taskPersistence.changeStatus(task, TaskStatus.INACTIVE);
    assertThat(taskEntityTestFactory.defaultTask().getStatus()).isEqualTo(TaskStatus.INACTIVE);

  }

}