package io.reflectoring.cleantimetracker.timecontext.domain.entity

import java.io.Serializable


class TimeRecordId(var value: Long) : Serializable {

    companion object {
        @JvmStatic
        fun of(id: Long): TimeRecordId {
            return TimeRecordId(id)
        }
    }
}