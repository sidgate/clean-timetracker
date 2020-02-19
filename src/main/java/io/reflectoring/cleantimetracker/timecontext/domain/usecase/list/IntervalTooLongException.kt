package io.reflectoring.cleantimetracker.timecontext.domain.usecase.list

import java.time.LocalDate

class IntervalTooLongException(start: LocalDate?, end: LocalDate?, maxDays: Int) : RuntimeException(String.format("interval must not be longer than %d days (start: %s, end: %s)", maxDays, start, end))