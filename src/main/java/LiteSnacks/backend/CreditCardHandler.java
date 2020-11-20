package LiteSnacks.backend;

import org.json.simple.JSONArray;

import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashMap;

public class CreditCardHandler {
    private final static String success = "Success! Enjoy your products";
    private final static String fail = "Credit Card details are not valid";

    public static String getSuccessMessage() {
        return success;
    }

    public static String getFailMessage() {
        return fail;
    }

    public static String checkCard(String name, String creditCardNumber) {
        String result = null;
        HashMap<String, String> creditCards = getCards();
        if(creditCards.containsKey(creditCardNumber) && creditCards.get(creditCardNumber).equals(name)) {
            result = success;
        }
        else{
            result = fail;
        }
        return result;
    }

    private static HashMap<String, String> getCards() {
        Object obj = cardsObject();
        JSONArray creditCards = (JSONArray)obj;
        JSONObject jsonEntry;

        String creditCardNumber;
        String name;
        HashMap<String, String> creditCardEntries = new HashMap<>();
        for (Object creditCard : creditCards) {
            jsonEntry = (JSONObject) creditCard;
            creditCardNumber = (String) jsonEntry.get("number");
            name = (String) jsonEntry.get("name");
            if(!creditCardEntries.containsKey(creditCardNumber)) {
                creditCardEntries.put(creditCardNumber, name);
            }
            else {
                System.out.println("\n-------- ERROR - CREDIT CARD NUMBER ALREADY EXISTS --------\n");
            }
        }
        return creditCardEntries;
    }

    private static Object cardsObject() {
        JSONParser jsonParser = new JSONParser();
        InputStreamReader reader;
        Object obj = null;
        try{
            reader = new InputStreamReader(CreditCardHandler.class.getResourceAsStream("/credit_cards.json"));
            obj = jsonParser.parse(reader);
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return obj;
    }


}
