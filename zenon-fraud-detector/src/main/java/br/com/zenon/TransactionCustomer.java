package br.com.zenon;

import java.math.BigDecimal;

public record TransactionCustomer(String name, BigDecimal oldbalance, BigDecimal newbalance) {
}
