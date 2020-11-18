package LiteSnacks.backend;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ReportsHandler {
    ResourceHandler handler ;
    public ReportsHandler(){
        handler = new ResourceHandler();
    }

    public String getCashesReport(){
        File reportFile = handler.getCashesReportFile();
        String st = "Saved at "+String.valueOf(reportFile);
        try{
            FileWriter writer = new FileWriter(reportFile);
            CashHandler handler = new CashHandler();
            String sr = getStringOfCashesReport(handler.readFile());
            writer.write(sr);
            writer.close();
            showReport(reportFile);

        }catch (Exception e){
            st = "Saved fail";
        }
        return st;

    }
    public String getStringOfCashesReport(List<Cash> cashes){
        String value = "\n These below are cashes available in the Vending machine" + "\n";
        for (Cash cash : cashes){
            value += "------------------------- \n\n";
            if (cash.getValue()>=1){
                int m = (int) cash.getValue();
                value += "  value : $"+m+"\n";
            }else {
                int m = (int) cash.getValue()*100;
                value += "  value : "+m+"c\n";
            }

            value += "  qty : "+cash.getQty()+"\n\n";

        }
        return value;

    }

    public String getTransactionsReport(){
        File reportFile = handler.getTransactionsReportFile();
        String st = "Saved at "+String.valueOf(reportFile);
        try{
            FileWriter writer = new FileWriter(reportFile);
            CashHandler handler = new CashHandler();
            String sr = getStringOfTransactionsReport(handler.readFile());
            writer.write(sr);
            writer.close();
            showReport(reportFile);

        }catch (Exception e){
            st = "Saved fail";
        }
        return st;

    }
    public String getStringOfTransactionsReport(List<Cash> cashes){
        String value = "\n These below are transactions details in the Vending machine" + "\n";
        /***
        for (Transaction each : cashes){
            value += "------------------------- \n\n";
            value += "  Date : "+ each.getdate() + "\n";
            value += "  Price : "+ each.getprice() + "\n";
            value += "  User : "+ each.getdate() + "\n";
            value += "  Items : \n"
            int index = 1;
            for (Item item :  items){
                value += "      ."+index+"    "+item.getName()+ + item.getQty()+"x ""   $ "+item.getunitPrice()
                index+=1;
            }


        }***/
        return value;

    }
    public void showReport(File reportFile){
        try{
            Desktop desktop = Desktop.getDesktop();
            desktop.open(reportFile);

        }catch (Exception e){

        }
    }
}
