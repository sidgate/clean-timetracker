package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.create

import io.reflectoring.cleantimetracker.projectcontext.domain.usecase.createproject.CreateProjectUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
internal class CreateProjectController(private val createProjectUseCase: CreateProjectUseCase) {
    @GetMapping("/projects/create")
    fun displayCreateProjectForm(model: Model): String {
        model.addAttribute("project", CreateProjectForm())
        return "project/createProject.html"
    }

    @PostMapping("/projects")
    fun createProject(@ModelAttribute("project") projectModel: CreateProjectForm): String {
        createProjectUseCase.createProject(projectModel.name)
        return "redirect:/projects"
    }

}