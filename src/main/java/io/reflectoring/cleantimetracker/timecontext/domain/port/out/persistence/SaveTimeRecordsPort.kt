package io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord

interface SaveTimeRecordsPort {
    fun saveTimeRecords(records: List<TimeRecord?>)
}