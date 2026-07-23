package br.com.zenon;

import java.util.*;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactionsByOrigin;

    public TransactionListRepository(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactionsByOrigin = transactions;
    }

    @Override
    public void transactionByOriginName(String originName) {
        findByOriginName(originName).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para cliente: " + originName));
    }

    private Optional<Transaction> findByOriginName(String originName) {
        return transactionsByOrigin.stream().filter(transaction -> transaction.origin().name().equals(originName)).findFirst();
    }
}
