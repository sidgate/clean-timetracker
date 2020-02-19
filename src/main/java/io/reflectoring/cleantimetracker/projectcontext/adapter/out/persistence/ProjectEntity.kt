package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus
import javax.persistence.*

@Entity
@Table(name = "PROJECT")
class ProjectEntity(
        @Id
        @GeneratedValue
        @Column(name = "ID")
        var id: Long? = null,
        @Column(name = "NAME")
        var name: String? = null,
        @Column(name = "STATUS")
        @Enumerated(EnumType.STRING)
        var status: ProjectStatus? = null
)