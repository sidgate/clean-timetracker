package io.reflectoring.cleantimetracker.timecontext.domain.entity

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

/**
 * Different view on the project and task data to be used by the "time tracking" bounded context.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class TimeTrackingTask (
     var id: Long? = null,
     var name: String? = null,
     var projectId: Long? = null,
     var projectName: String? = null,
     var active: Boolean? = null,
     var invoiceable: Boolean? = null
)