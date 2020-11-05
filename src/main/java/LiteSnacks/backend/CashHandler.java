package LiteSnacks.backend;
import LiteSnacks.UI.Cash;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class CashHandler {
    List<Cash> cashes ;

    public CashHandler(){
        this.cashes = readFile();
    }
    public List<Cash> readFile(){
        JSONParser jsonParser = new JSONParser();
        List<Cash> vale = null;
        try
        {
            //Read JSON file
            FileReader reader = new FileReader("src/main/resources/cashes.json");
            Object obj = jsonParser.parse(reader);

            JSONArray cashes = (JSONArray) obj;
            vale =  getCashes(cashes);


            //Iterate over employee array

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vale;
    }
    public void fresh(){
        this.cashes = readFile();
    }
    public List<Cash> getCashes(JSONArray cashes){
        List<Cash> list = new ArrayList<Cash>();
        for (Object cash : cashes) {
            JSONObject each = (JSONObject) cash;
            double value = (double)each.get("value");
            double qtydouble = (double)each.get("qty");
            int qty = (int) qtydouble;
            list.add(new Cash(value,qty,"cash.jpg"));

        }

        //double value = valueJ.get
        return list;

    }
    public List<Cash> getcashes(){

        return this.cashes;

    }
    public void Submit(List<Cash> ls)  {
        JSONArray array = new JSONArray();
        for (Cash each : ls){
            JSONObject eachobject = new JSONObject();
            eachobject.put("value",each.getValue());
            eachobject.put("qty",(double)each.getQty());
            array.add(eachobject);
        }
        try{
            FileWriter file = new FileWriter("src/main/resources/cashes.json");
            file.write(array.toJSONString());
            file.flush();
        }catch (Exception e){

        }


    }



}
