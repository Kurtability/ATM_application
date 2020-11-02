package LiteSnacks.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceHandler {
    private static File productFile;
    private final static String home = System.getProperty("user.home");
    final static String root = home + File.separator + ".litesnacks" + File.separator;

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

    private static boolean initProducts() {
        String path = home + File.separator + ".litesnacks" + File.separator + "products.csv";
        System.out.println(path);
        productFile = new File(path);
        if (!productFile.exists()) {
            try {
                productFile.createNewFile();
            } catch (IOException e) {
                System.out.println("creating folder");
                if(new File(root).mkdir() == false) {
                    System.out.println("issues creating folder");
                    return false;
                }
            }
            try {
                copyfiles(ResourceHandler.class.getResourceAsStream("/products.csv"), productFile);
            } catch (FileNotFoundException e) {
                System.out.println(ResourceHandler.class.getSimpleName() + " " + getLineNumber() + ": File Not Found error");
                return false;
            } catch (IOException e) {
                System.out.println(ResourceHandler.class.getSimpleName() + " " + getLineNumber() + ": IO error");
                return false;
            }
            System.out.println("created new file with default data");
            
        }
        return true;
        
    }

    /**
     * This is the getter for the File object for the products.csv file.
     * It creates the required files and directories and fills them with
     * the default data if not already present.
     * @return The file object for the products.csv file
     */
    public static File getProducts() {
        if (productFile == null) {
            initProducts();
        }
        return productFile;
    }

    private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    public static void main(String[] args) {
        initProducts();
        System.out.println(ResourceHandler.class.getSimpleName() + " " + getLineNumber());
    }
}