package br.com.zenon;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionsMap;


    public TransactionMapRepository(List<Transaction> transactionsMap) {
        Objects.requireNonNull(transactionsMap);
        this.transactionsMap = transactionsMap.stream().collect(Collectors.toMap(transaction -> transaction.origin().name(), Function.identity()));
    }

    @Override
    public void transactionByOriginName(String originName) {
        Optional.ofNullable(transactionsMap.get(originName)).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para cliente: " + originName));
    }
}
