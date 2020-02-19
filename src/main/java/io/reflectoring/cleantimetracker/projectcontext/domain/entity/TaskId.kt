package io.reflectoring.cleantimetracker.projectcontext.domain.entity

import lombok.AccessLevel
import lombok.Data
import lombok.RequiredArgsConstructor
import java.io.Serializable

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class TaskId( var value: Long? = null) : Serializable {

    companion object {
        @JvmStatic
        fun of(id: Long?): TaskId {
            return TaskId(id)
        }
    }
}