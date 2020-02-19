package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.edit

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId
import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskId
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.addtask.AddTaskUseCase
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.changetaskstatus.ChangeTaskStatusUseCase
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.listtasks.ListTasksUseCase
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.loadproject.LoadProjectUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
internal class EditProjectController(private val listTasksUseCase: ListTasksUseCase, private val loadProjectUseCase: LoadProjectUseCase, private val editProjectModelMapper: EditProjectModelMapper, private val addTaskUseCase: AddTaskUseCase, private val changeTaskStatusUseCase: ChangeTaskStatusUseCase) {
    @GetMapping("/projects/{id}")
    fun displayProjectForm(@PathVariable("id") projectId: Long?, model: Model): String {
        val project = loadProjectUseCase.loadProject(ProjectId.of(projectId))
        val tasks = listTasksUseCase.listTasksForProject(ProjectId.of(projectId))
        val projectModel = editProjectModelMapper.toModel(project, tasks)
        model.addAttribute("project", projectModel)
        model.addAttribute("addTaskForm", AddTaskForm())
        return "project/editProject.html"
    }

    @PostMapping("/projects/{id}/add-task")
    fun addTask(@PathVariable("id") projectId: Long?,
                @ModelAttribute("addTaskForm") form: AddTaskForm): String {
        addTaskUseCase.addTask(form.name, form.invoiceable, ProjectId.of(projectId))
        return "redirect:/projects/{id}"
    }

    @PostMapping("/projects/{projectId}/tasks/{taskId}/activate")
    fun activateTask(@PathVariable("taskId") taskId: Long): String {
        changeTaskStatusUseCase.activateTask(TaskId.of(taskId))
        return "redirect:/projects/{projectId}"
    }

    @PostMapping("/projects/{projectId}/tasks/{taskId}/deactivate")
    fun deactivateTask(@PathVariable("taskId") taskId: Long): String {
        changeTaskStatusUseCase.deactivateTask(TaskId.of(taskId))
        return "redirect:/projects/{projectId}"
    }

}