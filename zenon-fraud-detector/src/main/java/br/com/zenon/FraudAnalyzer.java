package br.com.zenon;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FraudAnalyzer {

    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    public long countTransactionsFraud() {
        return isFraud().count();
    }

    public List<BigDecimal> findHighValueFraud(int limit) {
        return highValueFraud().map(Transaction::amount).limit(limit).toList();
    }

    public List<String> findClientHighValueFraud(int limit) {
        return highValueFraud().map(transaction -> transaction.origin().name()).distinct().limit(limit).toList();
    }

    public BigDecimal totalValueFraud() {
        return isFraud().map(Transaction::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> countFraudByType() {
        return isFraud().collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }

    private Stream<Transaction> isFraud() {
        return transactions.stream().filter(Transaction::isFraud);
    }

    private Stream<Transaction> highValueFraud() {
        return isFraud().sorted(Comparator.comparing(Transaction::amount).reversed());
    }

}
