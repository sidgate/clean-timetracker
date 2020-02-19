package io.reflectoring.cleantimetracker.timecontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence.QueryTimeRecordsPort
import io.reflectoring.cleantimetracker.timecontext.domain.port.out.persistence.SaveTimeRecordsPort
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TimeRecordPersistenceAdapter(private val timeRecordMapper: TimeRecordMapper, private val timeRecordRepository: TimeRecordRepository) : QueryTimeRecordsPort, SaveTimeRecordsPort {
    override fun listTimeRecords(from: LocalDate, until: LocalDate): List<TimeRecord?> {
        val recordEntities = timeRecordRepository.findByDateBetween(from, until)
        return timeRecordMapper.toDomainObjects(recordEntities)
    }

    override fun saveTimeRecords(records: List<TimeRecord>) {
        val recordEntities = timeRecordMapper.toEntities(records)
        timeRecordRepository.saveAll(recordEntities)
    }

}