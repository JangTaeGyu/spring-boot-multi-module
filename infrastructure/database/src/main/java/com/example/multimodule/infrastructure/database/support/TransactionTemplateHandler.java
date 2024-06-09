package com.example.multimodule.infrastructure.database.support;

import com.example.multimodule.core.domain.support.transaction.TransactionAction;
import com.example.multimodule.core.domain.support.transaction.TransactionHandler;
import com.example.multimodule.core.domain.support.transaction.TransactionHandlerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@RequiredArgsConstructor
public class TransactionTemplateHandler implements TransactionHandler {
    private final TransactionTemplate transactionTemplate;

    @Override
    public <T> T execute(TransactionAction<T> action) throws TransactionHandlerException {
        return transactionTemplate.execute(status -> action.action());
    }
}
