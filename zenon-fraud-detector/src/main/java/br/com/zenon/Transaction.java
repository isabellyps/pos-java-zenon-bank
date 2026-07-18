package br.com.zenon;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public record Transaction(@NotNull Long step, @NotNull TransactionType type, @NotNull BigDecimal amount, @NotNull TransactionCustomer origin, @NotNull TransactionCustomer destination, boolean isFraud, boolean isFlaggedFraud
                          ) {
    public Transaction {
        if (step < 1) throw new IllegalArgumentException("O valor de 'step' deve ser maior ou igual a 1: " + step);
        if (amount.signum() < 0) throw new IllegalArgumentException("O valor de 'amount' não pode ser negativo: " + amount);
    }
}
