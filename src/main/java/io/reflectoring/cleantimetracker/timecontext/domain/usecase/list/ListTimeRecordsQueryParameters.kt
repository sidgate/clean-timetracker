package io.reflectoring.cleantimetracker.timecontext.domain.usecase.list

import lombok.Builder
import lombok.Data
import lombok.RequiredArgsConstructor
import java.time.LocalDate

/**
 * Parameters to define the filter for loading TimeRecords.
 */
@Data
@Builder
@RequiredArgsConstructor
class ListTimeRecordsQueryParameters ( val start: LocalDate? = null,
     val end: LocalDate? = null
)