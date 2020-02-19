package io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit

import lombok.Builder
import lombok.Data
import lombok.RequiredArgsConstructor
import java.time.LocalDate

@Data
@Builder
@RequiredArgsConstructor
class SubmitTimeRecordInputData (
    var date: LocalDate? = null,
    var minutes: Int? = null,
    var taskId: Long? = null
)