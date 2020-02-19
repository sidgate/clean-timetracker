package io.reflectoring.cleantimetracker.timecontext.domain.entity

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class TimeRecordWithTask(var task: TimeTrackingTask? = null) : TimeRecord() {

    companion object {
        fun fromTimeRecord(record: TimeRecord, task: TimeTrackingTask?): TimeRecordWithTask {
            val timeRecordWithTask = TimeRecordWithTask()
            timeRecordWithTask.date = (record.date)
            timeRecordWithTask. minutes = (record.minutes)
            timeRecordWithTask.taskId = (record.taskId)
            timeRecordWithTask.status = (record.status)
            timeRecordWithTask.task = (task)
            return timeRecordWithTask
        }
    }
}