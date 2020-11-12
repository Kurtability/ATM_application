package LiteSnacks.backend;

import org.json.simple.JSONArray;

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashMap;

public class CreditCardHandler {
    private static File creditCardFile = ResourceHandler.getCreditCardsFile();

    public static boolean checkCard(String name, String creditCardNumber) {
        boolean verified = false;
        HashMap<String, String> creditCards = getCards();
        if(creditCards.containsKey(creditCardNumber)) {
            verified = creditCards.get(creditCardNumber).equals(name);
        }
        if(verified) {
            System.out.println("Success! Enjoy your products");
        }
        else{
            System.out.println("Credit Card details are not valid");
        }
        return verified;
    }

    public static void add(String name, String creditCardNumber) {
        if(isValid(name, creditCardNumber) && !checkCard(name, creditCardNumber)) {
            Object obj = cardsObject();
            JSONArray creditCards = (JSONArray) obj;
            JSONObject newEntry = new JSONObject();
            newEntry.put("name", name);
            newEntry.put("number", creditCardNumber);
            creditCards.add(newEntry);
            FileWriter writer = null;
            try {
                writer = new FileWriter(ResourceHandler.getCreditCardsFile());
                writer.write(creditCards.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateFile();
    }

    private static boolean isValid(String name, String creditCardNumber) {
        boolean isValid = (name != null && creditCardNumber != null && !name.isEmpty() && !creditCardNumber.isEmpty());
        if(isValid) {
            try {
                Integer.parseInt(creditCardNumber);
            } catch (Exception NumberFormatException) {
                isValid = false;
            }
        }
        return isValid;
    }

    private static void updateFile() {
        creditCardFile = ResourceHandler.getCreditCardsFile();
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
        FileReader reader;
        Object obj = null;
        try{
            reader = new FileReader(creditCardFile);
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
