package com.example.multimodule.infrastructure.database.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

public abstract class EntityPersistable <ID> implements Persistable<ID> {
    @Transient
    protected boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PrePersist
    void notNew() {
        this.isNew = false;
    }
}
