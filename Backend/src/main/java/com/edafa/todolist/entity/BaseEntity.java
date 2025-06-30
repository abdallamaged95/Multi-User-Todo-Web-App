package com.edafa.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Generated
    @Column(name = "CREATED_AT", updatable = false)
    private Timestamp createdAt;

    @Generated
    @Column(name = "MODIFIED_AT")
    private Timestamp modifiedAt;

}
