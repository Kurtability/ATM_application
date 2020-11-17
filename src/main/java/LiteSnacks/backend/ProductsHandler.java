package LiteSnacks.backend;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductsHandler {
    private static File productsFile;

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

    public int getQuantitiy(String productName) {
        Scanner sc = null;
        try{
            sc = new Scanner(ResourceHandler.getProducts());
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }

        String line;
        String[] lineSplit;
        String quantity = "-1";
        boolean found = false;
        while(sc.hasNextLine() && !found) {
            line = sc.nextLine();
            if(!line.isEmpty()) {
                lineSplit = line.split(",");
                if (lineSplit[0].equals(productName)) {
                    quantity = lineSplit[1];
                    found = true;
                }
            }
        }
        return Integer.parseInt(quantity);
    }

    public ArrayList<Item> listOfItems() {
        ArrayList<Item> items = new ArrayList<>();
        List<String> categories = getCategories();
        for(String c : categories) {
            items.addAll(getItemsForCategory(c));
        }
        return items;
    }

    // overwrites the contents of products.csv
    public static void writeToFile(List<Item> items) {
        String[] categories = "Drinks,Chocolates,Chips,Candies".split(",");
        FileWriter writer = null;
        try {
            writer = new FileWriter(ResourceHandler.getProducts());
            writer.write("_\nLast Five\n");
            for(String s : categories) {
                writer.write("_\n" + s + "\n");
                for(Item i : items) {
                    if(s.equals(i.getCategory())) {
                        writer.write(i.write() + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String generateReport(List<Item> items) {
        String[] categories = "Drinks,Chocolates,Chips,Candies".split(",");
        FileWriter writer = null;
        String entry;
        try {
            writer = new FileWriter(ResourceHandler.getProductReport());
            for(String s : categories) {
                for(Item i : items) {
                    entry = String.format("PID: %s\nProduct: %s\nPrice: %s\nQuantity: %s\nCategory: %s\n\n", i.getId(), i.getName(), i.getUnitPrice(), i.getQuantity(), i.getCategory());
                    writer.write(entry);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return(String.format("The report is stored at: %s", ResourceHandler.getProductReport().toString()));
    }
}