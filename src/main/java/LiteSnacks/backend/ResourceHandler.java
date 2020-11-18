package LiteSnacks.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceHandler {

    private final static String home = System.getProperty("user.home"); // the absolute path of home directory
    final static String root = home + File.separator + ".litesnacks" + File.separator;

    private static File productFile;
    private static File cashFile;
    private static File creditCardFile;

    // copy a file from a jar resorce to a file in the project directory
    static void copyfiles(InputStream from, File to) throws FileNotFoundException, IOException {

        InputStreamReader in = new InputStreamReader(from);
        FileOutputStream out = new FileOutputStream(to);

        try {
            int readbyte;
            while ((readbyte = in.read()) != -1) {
                out.write(readbyte);
            }
        } finally {
            in.close();
            out.close();
        }
    }

    //
    private static File initFile(String file) {
        String path = root + file;
        System.out.println(path);
        File curFile = new File(path);
        if (!curFile.exists()) {
            try {
                curFile.createNewFile();
            } catch (IOException e) {
                System.out.println("creating folder");
                if (new File(root).mkdir() == false) {
                    System.out.println("issues creating folder");
                    return null;
                }
            }
            try {
                copyfiles(ResourceHandler.class.getResourceAsStream("/" + file), curFile);
            } catch (FileNotFoundException e) {
                System.out.println(
                        ResourceHandler.class.getSimpleName() + " " + getLineNumber() + ": File Not Found error");
                return null;
            } catch (IOException e) {
                System.out.println(ResourceHandler.class.getSimpleName() + " " + getLineNumber() + ": IO error");
                return null;
            }
            System.out.println("created new file with default data");

        }
        return curFile;

    }

    /**
     * This is the getter for the File object for the products.csv file. It creates
     * the required files and directories and fills them with the default data if
     * not already present.
     * 
     * @return The file object for the products.csv file
     */
    public static File getProducts() {
        return initFile("products.csv");
    }

    public static File getCashFile() {
        return initFile("cashes.json");
    }



    public static File getCashesReportFile() {return  initFile("cashesReport.txt");}

    public static File getTransactionsReportFile() {return  initFile("transactionsReport.txt");}



    public static File getCreditCardsFile() {
        if (creditCardFile == null) {
            creditCardFile = initFile("credit_cards.json");
        }
        return creditCardFile;
    }

    public static File getTransactionFile() {
        return initFile("transaction.csv");
    }

    private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

}