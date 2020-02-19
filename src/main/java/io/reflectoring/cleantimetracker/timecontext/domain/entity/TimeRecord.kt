package io.reflectoring.cleantimetracker.timecontext.domain.entity

import java.time.LocalDate

open class TimeRecord(
        var id: TimeRecordId? = null,
        var taskId: Long? = null,
        var date: LocalDate? = null,
        var minutes: Int? = null,
        var status: TimeRecordStatus? = null
)