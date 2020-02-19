package io.reflectoring.cleantimetracker.timecontext.domain.entity

import lombok.AccessLevel
import lombok.Data
import lombok.RequiredArgsConstructor
import java.io.Serializable

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class TimeRecordId(var value: Long) : Serializable {

    companion object {
        @JvmStatic
        fun of(id: Long): TimeRecordId {
            return TimeRecordId(id)
        }
    }
}