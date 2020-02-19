package io.reflectoring.cleantimetracker.timecontext.adapter.`in`.web.list

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class TimeRecordListModel (
    var date: LocalDate? = null,
    var hours: Float? = null,
    var taskName: String? = null
)