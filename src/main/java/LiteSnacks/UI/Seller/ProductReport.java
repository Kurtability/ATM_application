package LiteSnacks.UI.Seller;

import LiteSnacks.backend.Item;
import LiteSnacks.backend.ProductsHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static LiteSnacks.UI.Style.setHW;
import static LiteSnacks.UI.Style.setXY;

public class ProductReport {
    Scene scene;
    Stage stage;

    public ProductReport(double width, double height, Stage stage) {
        this.stage = stage;
        Pane root = new Pane();

        // back
        Button back = new Button(">back");
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(new Font(20));
        back.setOnAction(e -> {
            new SellerMainScene(width, height, stage).setScene();
        });
        setXY(back,500,10);

        // label
        Text label = new Text("Product Report");
        setXY(label,21,30);
        label.setFont(new Font("Arial", 30));

        TableView productReport = new TableView<>();

        TableColumn<Item, String> column1 = new TableColumn<>("Product");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, String> column2 = new TableColumn<>("Product ID");
        column2.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Item, String> column3 = new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        TableColumn<Item, String> column4 = new TableColumn<>("Quantity");
        column4.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Item, String> column5 = new TableColumn<>("Category");
        column5.setCellValueFactory(new PropertyValueFactory<>("category"));


        productReport.getColumns().add(column1);
        productReport.getColumns().add(column2);
        productReport.getColumns().add(column3);
        productReport.getColumns().add(column4);
        productReport.getColumns().add(column5);

        ProductsHandler ph = new ProductsHandler();
        for(Item item : ph.listOfItems()) {
            productReport.getItems().add(item);
        }

//        TableView.ResizeFeatures<Item> pr = new TableView.ResizeFeatures(productReport, null, Double.parseDouble("10.0"));
        root.getChildren().addAll(label, back, productReport);

        productReport.setPrefWidth(490);
        scene = new Scene(root, width, height);
    }

    public void setScene() {
        stage.setScene(scene);
    }
}
