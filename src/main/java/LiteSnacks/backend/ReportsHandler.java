package LiteSnacks.backend;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ReportsHandler {
    private static File reportFile;
    public ReportsHandler(){
        reportFile = ResourceHandler.getReportFile();
    }
    public void getCashesReport(){
        try{
            FileWriter writer = new FileWriter(reportFile);
            CashHandler handler = new CashHandler();
            String sr = getStringOfCashesReport(handler.readFile());
            writer.write(sr);
            writer.close();
            showReport();

        }catch (Exception e){

        }
    }
    public String getStringOfCashesReport(List<Cash> cashes){
        String value = "\n These below are cashes available in the Vending machine" + "\n";
        for (Cash cash : cashes){
            value += "------------------------- \n\n";
            if (cash.getValue()>=1){
                int m = (int) cash.getValue();
                value += "  value : $"+m+"\n";
            }else {
                value += "  value : "+cash.getValue()*100+"c\n";
            }

            value += "  qty : "+cash.getQty()+"\n\n";

        }
        return value;

    }
    public void showReport(){
        try{
            Desktop desktop = Desktop.getDesktop();
            desktop.open(reportFile);
        }catch (Exception e){

        }
    }
}
