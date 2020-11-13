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
        String success = CreditCardHandler.getSuccessMessage();
        String fail = CreditCardHandler.getFailMessage();
        assertEquals(success, CreditCardHandler.checkCard("Charles", "40691"));
        assertEquals(fail, CreditCardHandler.checkCard("Charles", "42689"));
        assertEquals(fail, CreditCardHandler.checkCard("", "42689"));
        assertEquals(fail, CreditCardHandler.checkCard("Charles", ""));
        String empty = null;
        assertEquals(fail, CreditCardHandler.checkCard("Charles", empty));
        assertEquals(fail, CreditCardHandler.checkCard(empty, "42689"));
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


