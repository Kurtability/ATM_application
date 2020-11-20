package LiteSnacks.backend;

import java.util.*;

public class LastFive {

    public static List<String> getLastFive(String username) {
        List<String> lastFiveItems = new ArrayList<>();
        List<Transaction> usersTransactions = new ArrayList<>();
        List<Transaction> transactions = Transaction.getAllTransactions();

        // Get a list of names of the currently stocked items
        ProductsHandler ph = new ProductsHandler();
        List<Item> items = ph.listOfItems();
        List<String> currentItemNames = new ArrayList<>();
        for(Item i : items) {
            currentItemNames.add(i.getName());
        }

        // Get the transaction history of the user
        if(transactions != null) {
            for (Transaction t : transactions) {
                if (t.getUser().equals(username)) {
                    usersTransactions.add(t);
                }
            }

            // Sort list by date
            usersTransactions.sort((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));


            Set<String> products;
            for(int i=usersTransactions.size() - 1; i >= 0 && lastFiveItems.size() < 5; i--) {
                products = usersTransactions.get(i).getProducts().keySet();
                for(String s : products) {

                    /*
                    Check that the item is not already in the lastFiveItem list - list should only contain unique items
                    Check that the lastFiveItem list is not greater than 5
                    check that the item to add is currently for sale
                     */
                    if(!lastFiveItems.contains(s) && lastFiveItems.size() < 5 && currentItemNames.contains(s)) {
                        lastFiveItems.add(s);
                    }
                }
            }
        }
        return lastFiveItems;
    }

    public static List<Item> convertNamesToItems(List<String> itemNames) {
        List<Item> items = new ArrayList<>();

        ProductsHandler ph = new ProductsHandler();
        List<Item> itemsInMachine = ph.listOfItems();

        for(String s : itemNames) {
            for(Item i : itemsInMachine) {
                if(i.getName().equals(s)) {
                    //items.add(new Item(i.getName(), i.getCategory(), i.getId(), i.getQuantity(), i.getUnitPrice()));
                    items.add(i);
                }
            }
        }

        return items;
    }
}
