package io.reflectoring.cleantimetracker.projectcontext.domain.entity

import java.io.Serializable


class TaskId(var value: Long) : Serializable {

    companion object {
        @JvmStatic
        fun of(id: Long): TaskId {
            return TaskId(id)
        }
    }
}