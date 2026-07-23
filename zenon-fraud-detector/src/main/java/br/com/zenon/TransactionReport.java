package br.com.zenon;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionReport {

    private record TransactionReportRecord(BigDecimal amount, boolean isFraud) { }

    public record TransactionValues(long totalTransactions, long totalFrauds, BigDecimal totalAmount) {
        private final static TransactionValues ZERO = new TransactionValues(0, 0, BigDecimal.ZERO);

        private TransactionValues addTransactionReportRecord(TransactionReportRecord transaction) {
            return new TransactionValues(totalTransactions + 1, totalFrauds + (transaction.isFraud ? 1 : 0), totalAmount.add(transaction.amount));
        }

        private TransactionValues add(TransactionValues other) {
            return new TransactionValues(totalTransactions + other.totalTransactions, totalFrauds + other.totalFrauds, totalAmount.add(other.totalAmount));
        }
    }

    public TransactionValues readNIO(String filePath) throws IOException {
        return readTransactions(filePath);
    }


    private TransactionValues readTransactions (String filePath) throws IOException {
        Path transactionsData = Path.of(filePath);

        try (Stream<String> lines = Files.lines(transactionsData)) {
            return lines.skip(1).map(this::parseLine).filter(Optional::isPresent)
                    .map(Optional::get).reduce(TransactionValues.ZERO, TransactionValues::addTransactionReportRecord, TransactionValues::add);

        } catch (Exception ex) {
            throw new IOException("Erro ao ler o arquivo: "+ ex.getMessage());
        }
    }

    private Optional<TransactionReportRecord> parseLine(@NotNull String linha) {
        try {
            String[] item = linha.split(",");

            BigDecimal amount = new BigDecimal(item[2]);
            boolean isFraud = "1".equals(item[9]);

            return Optional.of(new TransactionReportRecord(amount, isFraud));
        } catch (Exception ex) {
            System.err.println("Erro ao fazer parse: " + linha + " | " + ex);
            return Optional.empty();
        }
    }
}
