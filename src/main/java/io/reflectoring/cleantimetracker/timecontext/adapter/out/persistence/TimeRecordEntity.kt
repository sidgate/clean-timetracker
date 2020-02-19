package io.reflectoring.cleantimetracker.timecontext.adapter.out.persistence

import io.reflectoring.cleantimetracker.timecontext.domain.entity.TimeRecordStatus
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "TIME_RECORD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class TimeRecordEntity (
    @Id
    @GeneratedValue
    var id: Long? = null,
    @Column(name = "DATE")
    var date: LocalDate? = null,
    @Column(name = "MINUTES")
    var minutes: Int? = null,
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    var status: TimeRecordStatus? = null,
    @Column(name = "TASK_ID")
    var taskId: Long? = null
)