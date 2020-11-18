package LiteSnacks.backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Transaction {

    String user;
    Timestamp ts;
    double amount;
    Map<String, List<Double>> products;

    Transaction(double amount, Map<String, List<Double>> products, Timestamp ts, String user) {
        this.products = products;
        this.amount = amount;
        this.ts = ts;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public Map<String, List<Double>> getProducts() {
        return products;
    }

    public Timestamp getTimestamp() {
        return ts;
    }

    public String getUser() {
        return user;
    }

    public static void addTransaction(Map<String, List<Double>> transaction, double amount) {
        String user = "anon";
        File out = ResourceHandler.getTransactionFile();
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(out, true));

            pw.println("_");
            pw.println(new Timestamp(System.currentTimeMillis()));
            pw.println(amount);
            pw.println(user);

            for (String prod : transaction.keySet())
                pw.println(prod + "," + transaction.get(prod).get(0) + "," + transaction.get(prod).get(1));
            pw.println("*");
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static List<Transaction> getAllTransactions() {
        double amount;
        String user;
        List<Double> temp;
        String item;
        List<Transaction> transactions;
        try {
            Scanner sc = new Scanner(ResourceHandler.getTransactionFile());
            transactions = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("_")) {
                    Timestamp t = Timestamp.valueOf(sc.nextLine());
                    amount = Double.parseDouble(sc.nextLine());
                    user = sc.nextLine();

                    Map<String, List<Double>> items = new HashMap<>();

                    while (!(line = sc.nextLine()).equals("*")) {
                        String[] lspl = line.split(",");
                        temp = new ArrayList<>();
                        temp.add(Double.parseDouble(lspl[1]));
                        temp.add(Double.parseDouble(lspl[2]));
                        items.put(lspl[0], temp);
                    }
                    transactions.add(new Transaction(amount, items, t, user));
                }
            }

            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    public static void main(String[] args) {
        HashMap<String, List<Double>> hm = new HashMap<>();
        List<Double> l = new ArrayList<>();
        l.add(2.0);
        l.add(4.5);
        hm.put("tweaky", l);
        addTransaction(hm, 9.0);
        System.out.println(getAllTransactions());
    }
    */

    public String toString() {
        return "\n--------------------\n" + this.ts + " " + this.user + this.amount + "\n" + this.products.toString();
    }

}