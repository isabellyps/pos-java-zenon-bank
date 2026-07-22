package br.com.zenon;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {

    public List<Transaction> readTransactions(String filePath) throws IOException {
        return read(filePath);
    }

    private List<Transaction> read(String filePath) throws IOException {
        Path transactionsData = Paths.get(filePath);

        try (FileChannel _ = FileChannel.open(transactionsData, StandardOpenOption.READ)) {
            List<String> transactionsList = Files.readAllLines(transactionsData);
            return transactionsList.stream().skip(1).limit(50000).map(this::parseLine).filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

        } catch (Exception ex) {
            throw new IOException("Erro ao ler o arquivo: "+ ex.getMessage());
        }
    }

    private Optional<Transaction> parseLine(@NotNull String linha) {
        try {
            String[] item = linha.split(",");

            Long step = Long.parseLong(item[0]);
            TransactionType type = TransactionType.valueOf(item[1]);

            BigDecimal amount = new BigDecimal(item[2]);

            var origin = new TransactionCustomer(item[3], new BigDecimal(item[4]), new BigDecimal(item[5]));
            var destination = new TransactionCustomer(item[6], new BigDecimal(item[7]), new BigDecimal(item[8]));

            boolean isFraud = "1".equals(item[9]);
            boolean isFlaggedFraud = "1".equals(item[10]);

            return Optional.of(new Transaction(step, type, amount, origin, destination, isFraud, isFlaggedFraud));
        } catch (Exception ex) {
            System.err.println("Erro ao fazer parse: " + linha + " | " + ex);
            return Optional.empty();
        }
    }

}
