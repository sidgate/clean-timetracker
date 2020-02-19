package io.reflectoring.cleantimetracker.time.domain.usecase.submit

import io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit.SubmitTimeRecordInputData
import java.time.LocalDate

internal object SubmitTimeRecordInputDataTestFactory {
    @JvmStatic
    @JvmOverloads
    fun eightHours(taskId: Long? = 42L): SubmitTimeRecordInputData {
        return SubmitTimeRecordInputData(
                date = (LocalDate.of(2018, 10, 20))
                , minutes = (8 * 60)
                , taskId = (taskId)
        )
    }

    @JvmStatic
    fun thirteenHours(taskId: Long?): SubmitTimeRecordInputData {
        return SubmitTimeRecordInputData(
                date = (LocalDate.of(2018, 10, 20))
                , minutes = (13 * 60)
                , taskId = (taskId)
        )
    }
}