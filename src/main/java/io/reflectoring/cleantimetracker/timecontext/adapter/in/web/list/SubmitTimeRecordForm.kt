package io.reflectoring.cleantimetracker.timecontext.adapter.`in`.web.list

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.NotNull


class SubmitTimeRecordForm(
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var date: @NotNull LocalDate? = null,
        var taskId: @NotNull Long? = null,
        var hours: @NotNull Float? = null
)