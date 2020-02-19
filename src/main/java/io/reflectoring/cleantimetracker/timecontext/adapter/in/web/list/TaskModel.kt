package io.reflectoring.cleantimetracker.timecontext.adapter.`in`.web.list

import lombok.Builder
import lombok.Data
import lombok.RequiredArgsConstructor

@Data
@RequiredArgsConstructor
@Builder
class TaskModel (
     var id: Long? = null,
    var name: String? = null
)