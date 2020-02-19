package io.reflectoring.cleantimetracker.projectcontext.domain.entity

import java.io.Serializable


class ProjectId(var value: Long) : Serializable {

    companion object {
        @JvmStatic
        fun of(id: Long): ProjectId {
            return ProjectId(id)
        }
    }
}