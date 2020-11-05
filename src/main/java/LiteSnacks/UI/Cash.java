package LiteSnacks.UI;

public class Cash {
    int qty;
    double value;
    String image;
    public Cash(double value,int qty,String image){
        this.qty = qty;
        this.value = value;
        this.image = image;
    }
    public int getQty(){
        return this.qty;
    }
    public double getValue(){
        return this.value;
    }
    public String getImg(){return this.image;}
    public boolean modifyqty(int gap){
        if (qty + gap <0){
            return false;
        }else{
            this.qty = qty + (gap);
        }
        return true;
    }
}
