package io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit

class TooMuchTimePerRecordException(bookedMinutes: Int, maximum: Int) : RuntimeException(String.format("cannot book more than %d minutes on a single record (was: %s)", maximum, bookedMinutes))