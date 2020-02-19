package io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit

import java.time.LocalDate


class SubmitTimeRecordInputData(
        var date: LocalDate? = null,
        var minutes: Int? = null,
        var taskId: Long? = null
)