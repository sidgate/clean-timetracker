package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.ProjectStatus
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PROJECT")
 class ProjectEntity (
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