package io.reflectoring.cleantimetracker.projectcontext.adapter.`in`.web.list

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
internal class ProjectListModelMapper {
    fun toModel(domainObject: Project): ProjectListModel {
        return ProjectListModel(
                id = (domainObject.id!!.value)
                , name = (domainObject.name)
                , status = (domainObject.status)
        )
    }

    fun toModels(domainObjects: List<Project>): List<ProjectListModel> {
        return domainObjects.stream()
                .map { domainObject: Project -> toModel(domainObject) }
                .collect(Collectors.toList())
    }
}