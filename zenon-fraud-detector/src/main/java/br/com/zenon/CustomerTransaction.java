package br.com.zenon;

import java.math.BigDecimal;

public record CustomerTransaction(String name, BigDecimal oldbalance, BigDecimal newbalance) {
}
