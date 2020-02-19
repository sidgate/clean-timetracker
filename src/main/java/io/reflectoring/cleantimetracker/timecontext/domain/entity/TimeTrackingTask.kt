package io.reflectoring.cleantimetracker.timecontext.domain.entity

/**
 * Different view on the project and task data to be used by the "time tracking" bounded context.
 */

class TimeTrackingTask(
        var id: Long,
        var name: String? = null,
        var projectId: Long? = null,
        var projectName: String? = null,
        var active: Boolean? = null,
        var invoiceable: Boolean? = null
)