package io.reflectoring.cleantimetracker.projectcontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.TaskStatus
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
@Table(name = "TASK")
class TaskEntity (
    @Id
    @GeneratedValue
    @Column(name = "ID")
    var id: Long? = null,
    @Column(name = "NAME")
    var name: String? = null,
    @Column(name = "INVOICEABLE")
    var invoiceable: Boolean? = null,
    @ManyToOne(optional = false)
    @JoinColumn(name = "PROJECT_ID")
    var project: ProjectEntity? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: TaskStatus? = null
)