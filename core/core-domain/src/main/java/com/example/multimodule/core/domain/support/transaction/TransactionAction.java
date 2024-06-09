package com.example.multimodule.core.domain.support.transaction;

@FunctionalInterface
public interface TransactionAction<T> {
    T action();
}
