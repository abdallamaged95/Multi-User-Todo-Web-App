package com.edafa.todolist.task.entity;

import com.edafa.todolist.entity.BaseEntity;
import com.edafa.todolist.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TASKS")
@SequenceGenerator(name = "SEQ_CUSTOM", sequenceName = "SEQ_TASKS", allocationSize = 1)
public class Task extends BaseEntity {
    @EmbeddedId
    private TaskId id = new TaskId();

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private String status;

}