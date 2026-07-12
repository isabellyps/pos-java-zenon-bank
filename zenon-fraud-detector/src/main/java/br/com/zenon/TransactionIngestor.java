package br.com.zenon;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    static List<String> getTransactionsByLine(String path, int lines) throws IOException {
        Path transactionsData = Paths.get(path);
        List<String> listByLine = new ArrayList<>();

        try (FileChannel channel = FileChannel.open(transactionsData, StandardOpenOption.READ)) {
            List<String> transactionsList = Files.readAllLines(transactionsData);
            IO.println(lines + "transactions");

            for (String linha : transactionsList.subList(0, lines)) {
                listByLine.add(linha);
                IO.println(linha);
            }
            return transactionsList.subList(0, lines);
        } catch (Exception ex) {
            IO.println("Erro ao ler o arquivo: "+ ex.getMessage());
        }

    return listByLine;
    }

}
