package io.reflectoring.cleantimetracker.timecontext.adapter.`in`.web.list

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordWithTask
import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeTrackingTask
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.list.ListTimeRecordsQueryParameters
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.list.ListTimeRecordsUseCase
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit.SubmitTimeRecordInputData
import io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit.SubmitTimeRecordsUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDate
import javax.validation.Valid
import kotlin.math.roundToInt
import kotlin.streams.toList

@Controller
class ListTimeRecordsController(private val listTimeRecordsUseCase: ListTimeRecordsUseCase, private val submitTimeRecordsUseCase: SubmitTimeRecordsUseCase) {
    @GetMapping("/time-records")
    fun displayTimeRecordsList(model: Model): String {
        model.addAttribute("submitTimeRecordForm", SubmitTimeRecordForm())
        model.addAttribute("tasks", availableTasks)
        model.addAttribute("records", storedTimeRecords)
        return "time-records/listTimeRecords.html"
    }

    @PostMapping("/time-records")
    fun submitTimeRecord(form: @Valid SubmitTimeRecordForm?): String {
        val input = SubmitTimeRecordInputData(
                date = (form!!.date)
                 , minutes =(form.hours?.times(60)?.roundToInt())
                ,taskId = (form.taskId)
                )
        submitTimeRecordsUseCase.submitTimeRecords(listOf(input))
        return "redirect:/time-records"
    }

    private val availableTasks: List<TaskModel>
        get() {
            val tasks = listTimeRecordsUseCase.listAllTasks()
            return tasks.stream()
                    .map { task: TimeTrackingTask ->
                        TaskModel(
                                id = (task.id)
                                ,name = (task.name)
                                )
                    }.toList()
        }

    private val storedTimeRecords: List<TimeRecordListModel>
        get() {
            val records = listTimeRecordsUseCase.listTimeRecords(ListTimeRecordsQueryParameters(
                    start = (LocalDate.now().minusDays(15))
                     ,end = (LocalDate.now().plusDays(15)))
                    )
            return records.stream()
                    .map { record: TimeRecordWithTask ->
                        TimeRecordListModel(
                                date = (record.date)
                                 ,hours = (record.minutes!! / 60.toFloat())
                                 ,taskName = (record.task?.name))
                    }
                    .toList()
        }

}