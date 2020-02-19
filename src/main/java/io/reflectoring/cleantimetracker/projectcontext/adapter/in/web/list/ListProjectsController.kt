package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.list

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectId.Companion.of
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.changeprojectstatus.ChangeProjectStatusUseCase
import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.listprojects.ListProjectsUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
internal class ListProjectsController(private val listProjectsUseCase: ListProjectsUseCase, private val projectListModelMapper: ProjectListModelMapper, private val changeProjectStatusUseCase: ChangeProjectStatusUseCase) {
    @GetMapping(path = ["/projects", "/"])
    fun displayProjectsList(model: Model): String {
        val projects = listProjectsUseCase.listProjects()
        val projectListModels = projectListModelMapper.toModels(projects)
        model.addAttribute("projects", projectListModels)
        return "project/listProjects.html"
    }

    @PostMapping("/projects/{id}/activate")
    fun activateProject(@PathVariable("id") projectId: Long?): String {
        changeProjectStatusUseCase.activateProject(of(projectId!!))
        return "redirect:/projects"
    }

    @PostMapping("/projects/{id}/deactivate")
    fun deactivateProject(@PathVariable("id") projectId: Long?): String {
        changeProjectStatusUseCase.deactivateProject(of(projectId!!))
        return "redirect:/projects"
    }

}