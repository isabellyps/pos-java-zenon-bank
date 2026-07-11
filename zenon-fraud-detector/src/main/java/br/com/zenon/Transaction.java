package br.com.zenon;

import java.math.BigDecimal;

public record Transaction(Long step, TypeTransaction type, BigDecimal amount, CustomerTransaction origin, CustomerTransaction destination, boolean isFraud, boolean isFlaggedFraud
                          ) {
}
