package LiteSnacks.backend;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardHandlerTest {
    @Test
    public void checkCardTest() {
        assertTrue(CreditCardHandler.checkCard("Charles", "40691"));
        assertFalse(CreditCardHandler.checkCard("Charles", "42689"));
        assertFalse(CreditCardHandler.checkCard("", "42689"));
        assertFalse(CreditCardHandler.checkCard("Charles", ""));
        String empty = null;
        assertFalse(CreditCardHandler.checkCard("Charles", empty));
        assertFalse(CreditCardHandler.checkCard(empty, "42689"));
    }

    @Test
    public void addTest() {
        Object originalFile = jsonContents();
        assertFalse(CreditCardHandler.checkCard("TestName", "0000"));
        CreditCardHandler.add("TestName", "0000");
        assertTrue(CreditCardHandler.checkCard("TestName", "0000"));
        String empty = null;
        CreditCardHandler.add(empty, "0001");
        assertFalse(CreditCardHandler.checkCard(empty, "0001"));
        CreditCardHandler.add("TestName2", empty);
        assertFalse(CreditCardHandler.checkCard("TestName2", empty));
        CreditCardHandler.add("", "0002");
        assertFalse(CreditCardHandler.checkCard("", "0002"));
        CreditCardHandler.add("TestName3", "");
        assertFalse(CreditCardHandler.checkCard("TestName3", ""));
        CreditCardHandler.add("TestName4", "TestPassword");
        assertFalse(CreditCardHandler.checkCard("TestName4", "TestPassword"));

        restoreFile(originalFile);

    }


    private static Object jsonContents() {
        JSONParser jsonParser = new JSONParser();
        FileReader reader;
        Object obj = null;
        try{
            reader = new FileReader(ResourceHandler.getCreditCardsFile());
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

    private static void restoreFile(Object originalFile) {
        FileWriter writer;
        JSONArray obj = (JSONArray)originalFile;
        try {
            writer = new FileWriter(ResourceHandler.getCreditCardsFile());
            writer.write(obj.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


