package LiteSnacks.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductsHandler {
    private File productsFile;

    // getting the product file from resource handler
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

    // different categories have different lists
    public List<Item> getItemsForCategory(String category) {
        List<Item> items = new ArrayList<>();// list of all items in the same category
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
                    items.add(new Item(splitItem[0], category, Integer.parseInt(splitItem[3]),
                            Integer.parseInt(splitItem[1]), Double.parseDouble(splitItem[2])));
                }
            }
        }

        sc.close();

        return items;
    }

    public Map<String, List<Item>> getAllItems() {

        Map<String, List<Item>> items = new HashMap<>();

        for (String category : this.getCategories()) {
            items.put(category, this.getItemsForCategory(category));
        }

        return items;
    }

    /**
     * Function to edit the quantity of a product in the products file.
     * 
     * @param productName The name of the product
     * @param delta       The change in quantity (+ve if increasing, -ve if
     *                    decreasing)
     */
    public void editQuantity(String productName, int delta) {
        Scanner sc;
        PrintWriter pw;
        try {
            sc = new Scanner(this.productsFile);
            File temp;
            pw = new PrintWriter(temp = File.createTempFile("newproducts", "tmp"));
            String prevLine = "";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.equals("_") && !prevLine.equals("_")) {
                    String[] attr = line.split(",");
                    if (attr[0].equals(productName))
                        pw.println(attr[0] + ","  + (Integer.parseInt(attr[1]) + delta) + "," + attr[2] + "," + attr[3]);
                    else
                        pw.println(line);
                } else {
                    pw.println(line);
                }
                prevLine = line;
            }
            sc.close();
            pw.flush();
            pw.close();

            ResourceHandler.copyfiles(new FileInputStream(temp), this.productsFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}