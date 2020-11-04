package LiteSnacks.backend;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
    public String name;
    public double unitQuantity;
    public double unitPrice;

    Item(String name, double unitQuantity, double unitPrice){
        this.name = name;
        this.unitQuantity = unitQuantity;
        this.unitPrice= unitPrice;
    }

    public String toString(){
        return name;
    }
}
