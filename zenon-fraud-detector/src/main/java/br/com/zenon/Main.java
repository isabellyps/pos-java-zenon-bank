package br.com.zenon;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    static void main() throws IOException {
        IO.println("************************* TAREFA 02 *************************");

        Transaction transaction1 = new Transaction(1L, TransactionType.PAYMENT, new BigDecimal("9839.64"), new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")), new TransactionCustomer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0")), false, false);

        Transaction transaction2 = new Transaction(743L, TransactionType.CASH_OUT, new BigDecimal("850002.52"), new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")), new TransactionCustomer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")), true, false);

        IO.println("Transaction 1");
        IO.println(transaction1);
        IO.println("");
        IO.println("Transaction 2");
        IO.println(transaction2);

        IO.println("");
        IO.println("************************* TAREFA 03 *************************");

        var transactionsIngestor = new TransactionIngestor();

        List<Transaction> transactionsByLine = transactionsIngestor.readTransactions("zenon-fraud-detector/data/dataset.csv");

        IO.println(transactionsByLine.size());
        transactionsByLine.stream().limit(10).forEach(IO::println);

        IO.println("");
        IO.println("************************* TAREFA 04 *************************");

        List<Transaction> transactions = transactionsIngestor.readTransactions("zenon-fraud-detector/data/paysim_with_bad_data.csv");

        IO.println("Exceptions");
        IO.println(transactions);
    }
}
