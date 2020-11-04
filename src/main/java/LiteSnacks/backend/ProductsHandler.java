package LiteSnacks.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductsHandler {
    private File productsFile;

    public ProductsHandler() {
        productsFile = ResourceHandler.getProducts();
    }

    public List<String> getCategories() {
        Scanner sc;
        try {
            sc = new Scanner(this.productsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        List<String> categories = new ArrayList<>();

        while (sc.hasNextLine()) {
            if (sc.nextLine().equals("_")) {
                categories.add(sc.nextLine());
            }
        }
        sc.close();
        
        return categories;
    }

    public List<Item> getItemsForCategory(String category) {
        List<Item> items = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(this.productsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        while (sc.hasNextLine()) {
            if (sc.nextLine().equals(category)) {
                String nextItem;
                while (sc.hasNextLine() && !(nextItem = sc.nextLine()).equals("_")) {
                    String[] splitItem = nextItem.split(",");
                    items.add(new Item(splitItem[0], Integer.parseInt(splitItem[1]), Double.parseDouble(splitItem[2])));
                }
            }
        }

        sc.close();

        return items;
    }

    public Map<String, List<Item>> getAllItems() {

        Map<String, List<Item>> items = new HashMap<>();

        for (String category: this.getCategories()) {
            items.put(category, this.getItemsForCategory(category));
        }

        return items;
    }

}