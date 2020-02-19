package io.reflectoring.cleantimetracker.projectcontext.domain.entity

import lombok.Builder
import lombok.Data

@Data
@Builder
class Project (
    var id: ProjectId? = null,
    var name: String? = null,
    var status: ProjectStatus? = null
)