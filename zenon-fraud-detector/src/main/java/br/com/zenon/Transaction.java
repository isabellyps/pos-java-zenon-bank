package br.com.zenon;

import java.math.BigDecimal;

public record Transaction(Long step, TransactionType type, BigDecimal amount, TransactionCustomer origin, TransactionCustomer destination, boolean isFraud, boolean isFlaggedFraud
                          ) {
}
