package br.com.zenon;

import com.google.gson.Gson;

import java.math.BigDecimal;

public class Main {
    static void main() {

        Transaction transaction1 = new Transaction(1L, TypeTransaction.PAYMENT, new BigDecimal("9839.64"), new CustomerTransaction("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")), new CustomerTransaction("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0")), false, false);

        Transaction transaction2 = new Transaction(743L, TypeTransaction.CASH_OUT, new BigDecimal("850002.52"), new CustomerTransaction("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")), new CustomerTransaction("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")), true, false);

        Gson gson = new Gson();

        IO.println("Hello and welcome!");
        IO.println("");
        IO.println("Transaction 1");
        IO.println(gson.toJson(transaction1));
        IO.println("");
        IO.println("Transaction 2");
        IO.println(gson.toJson(transaction2));
    }
}
