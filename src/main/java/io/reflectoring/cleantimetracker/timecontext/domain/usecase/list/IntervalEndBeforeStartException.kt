package io.reflectoring.cleantimetracker.timecontext.domain.usecase.list

import java.time.LocalDate

class IntervalEndBeforeStartException(start: LocalDate?, end: LocalDate?) : RuntimeException(String.format("interval start (%s) must be before interval end (%s)", start, end))