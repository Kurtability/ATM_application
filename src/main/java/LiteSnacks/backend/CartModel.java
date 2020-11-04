package LiteSnacks.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CartModel {
    public static ObservableList<Item>  itemsObservableList = FXCollections.observableArrayList();

    public static void loadData(){
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/kurtability/Desktop/soft2412_a2/Assignment2_r12group3/src/main/resources/products.csv"))) {
            String line = null;
            while((line = br.readLine()) != null){
                //System.out.println(line);
                String[] values = line.split(",");
                System.out.println(values[2]);
                Item item = new Item(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]));
                //System.out.println(item);
                itemsObservableList.add(item);
                //System.out.println(itemsObservableList);
//                br.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
