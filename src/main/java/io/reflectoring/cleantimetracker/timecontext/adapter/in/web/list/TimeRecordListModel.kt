package io.reflectoring.cleantimetracker.timecontext.adapter.`in`.web.list

import java.time.LocalDate


class TimeRecordListModel(
        var date: LocalDate? = null,
        var hours: Float? = null,
        var taskName: String? = null
)