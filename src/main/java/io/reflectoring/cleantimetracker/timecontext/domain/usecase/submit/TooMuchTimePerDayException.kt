package io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit

import java.time.LocalDate

class TooMuchTimePerDayException(bookedMinutes: Int, date: LocalDate?, maximum: Int) : RuntimeException(String.format("cannot book more than %d minutes on day %s (was: %s)", maximum, date, bookedMinutes))