package br.com.zenon;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public record TransactionCustomer(@NotNull String name, @NotNull BigDecimal oldBalance, @NotNull BigDecimal newBalance) {
    public TransactionCustomer {
        if (oldBalance.signum() < 0) throw new IllegalArgumentException("O valor de 'oldBalance' não pode ser negativo: " + oldBalance);
        if (newBalance.signum() < 0) throw new IllegalArgumentException("O valor de 'newBalance' não pode ser negativo: " + newBalance);
    }
}
