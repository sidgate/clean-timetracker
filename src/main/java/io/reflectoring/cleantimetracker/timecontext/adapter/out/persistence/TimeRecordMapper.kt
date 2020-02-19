package io.reflectoring.cleantimetracker.timecontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecord
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordId
import org.springframework.stereotype.Component
import java.util.*

@Component
class TimeRecordMapper {
    fun toDomainObject(entity: TimeRecordEntity): TimeRecord {
        return TimeRecord().apply {
            status = (entity.status)
                minutes = (entity.minutes)
                date = (entity.date)
                taskId = (entity.taskId)
                id = (if (entity.id == null) null else TimeRecordId.of(entity.id!!))
        }
    }

    fun toDomainObjects(entities: List<TimeRecordEntity>): List<TimeRecord> {
        val domainObjects: MutableList<TimeRecord> = ArrayList()
        for (entity in entities) {
            domainObjects.add(toDomainObject(entity))
        }
        return domainObjects
    }

    fun toEntity(domainObject: TimeRecord): TimeRecordEntity {
        return TimeRecordEntity().apply {
            status = (domainObject.status)
                minutes= (domainObject.minutes)
                date= (domainObject.date)
                taskId= (domainObject.taskId)
                id= (if (domainObject.id == null) null else domainObject.id!!.value)
        }
    }

    fun toEntities(domainObjects: List<TimeRecord>): List<TimeRecordEntity> {
        val entities: MutableList<TimeRecordEntity> = ArrayList()
        for (domainObject in domainObjects) {
            entities.add(toEntity(domainObject))
        }
        return entities
    }
}