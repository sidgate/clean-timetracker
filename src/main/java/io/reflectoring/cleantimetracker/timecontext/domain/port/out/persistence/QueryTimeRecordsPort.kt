package io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import java.time.LocalDate

interface QueryTimeRecordsPort {
    fun listTimeRecords(from: LocalDate, until: LocalDate): List<TimeRecord?>
}