package com.example.multimodule.core.domain.support.transaction;

public interface TransactionHandler {
    <T> T execute(TransactionAction<T> action) throws TransactionHandlerException;
}
