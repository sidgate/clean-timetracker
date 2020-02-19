package io.reflectoring.cleantimetracker.projectcontext.domain.entity

import lombok.AccessLevel
import lombok.Data
import lombok.RequiredArgsConstructor
import java.io.Serializable

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class ProjectId( var value: Long) : Serializable {

    companion object {
        @JvmStatic
        fun of(id: Long): ProjectId {
            return ProjectId(id)
        }
    }
}