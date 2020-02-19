package io.reflectoring.cleantimetracker.timecontext.domain.entity

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
open class TimeRecord (
     var id: TimeRecordId? = null,
     var taskId: Long? = null,
     var date: LocalDate? = null,
     var minutes: Int? = null,
     var status: TimeRecordStatus? = null
)