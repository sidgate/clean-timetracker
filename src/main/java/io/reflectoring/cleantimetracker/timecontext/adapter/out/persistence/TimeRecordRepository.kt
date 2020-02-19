package io.reflectoring.cleantimetracker.timecontext.adapter.out.persistence

import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface TimeRecordRepository : CrudRepository<TimeRecordEntity?, Long?> {
    fun findByDateBetween(start: LocalDate?, end: LocalDate?): List<TimeRecordEntity?>?
}