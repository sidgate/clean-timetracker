package io.reflectoring.cleantimetracker.timecontext.domain.usecase.list

import java.time.LocalDate

/**
 * Parameters to define the filter for loading TimeRecords.
 */

class ListTimeRecordsQueryParameters(val start: LocalDate? = null,
                                     val end: LocalDate? = null
)