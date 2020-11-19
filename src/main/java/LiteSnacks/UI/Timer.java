package LiteSnacks.UI;

import LiteSnacks.UI.ShoppingCart.Cart;
import javafx.animation.AnimationTimer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Timer {
    AnimationTimer timer;
    int minutes = 1;
    int seconds = 1;
    String time = "";
    Text timeText;
    Cart cart;
    double width;
    double height;
    Stage stage;
    Products productsPage;
    long start;

    public Timer(Text timer, double width, double height, Stage stage, Products products) {
        timeText = timer;
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.productsPage = products;

        this.start = System.currentTimeMillis();
        this.timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                int pass = (int) (System.currentTimeMillis() - start);
                pass /= 1000;
                int s = pass % 60;
                pass /= 60;
                int m = pass % 60;
                timeText.setText(String.format("Timer : %02d:%02d", minutes - m, seconds - s));
                // System.out.println(String.format("%02d:%02d", minutes - m, seconds - s));
                if (seconds - s == 0) {
                    if (minutes - m == 0) {
                        stop();

                        new Products(width, height, stage).setScene();
                    } else {
                        minutes -= 1;
                        seconds = 59;
                    }

                    /***
                     * if(minutes-m==0){
                     * 
                     * System.out.println(time);
                     * 
                     * 
                     * }else{ System.out.println(time); minutes -=1; seconds = 59; }
                     ***/
                }

            }
        };
        this.timer.start();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void restart() {
        this.start = System.currentTimeMillis();
    }

    public void stop() {
        this.timer.stop();
    }

    public void cancel() {
        timer.stop();

        System.out.println("cancel");
    }

}
