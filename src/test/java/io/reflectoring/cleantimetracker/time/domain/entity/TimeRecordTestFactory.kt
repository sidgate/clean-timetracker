package io.reflectoring.cleantimetracker.time.domain.entity

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordId.Companion.of
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordStatus
import java.time.LocalDate
import java.util.*

object TimeRecordTestFactory {
    fun defaultRecords(vararg taskIds: Long?): List<TimeRecord> {
        val records: MutableList<TimeRecord> = ArrayList()
        for (taskId in taskIds) {
            records.add(TimeRecord(
                    minutes =(8 * 60)
                    ,date =(LocalDate.of(2018, 10, 22))
                    ,status= (TimeRecordStatus.OPEN)
                    ,taskId =(taskId)
                    ,id = (of(43L))
                    ))
        }
        return records
    }
}