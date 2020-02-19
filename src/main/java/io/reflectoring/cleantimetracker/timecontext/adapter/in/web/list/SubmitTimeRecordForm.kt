package io.reflectoring.cleantimetracker.timecontext.adapter.`in`.web.list

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.NotNull

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SubmitTimeRecordForm (
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var date: @NotNull LocalDate? = null,
    var taskId: @NotNull Long? = null,
    var hours: @NotNull Float? = null
)