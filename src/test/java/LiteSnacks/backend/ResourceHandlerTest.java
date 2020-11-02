package LiteSnacks.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class ResourceHandlerTest {

    @Test void copyFilesWorks() {
        File temp;
        System.out.println("Hellooooo");
        try {
            // creates an empty temporary file
            temp = File.createTempFile("hellocopy", "tmp");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error creating temp file");
            return;
        }
        try {
            ResourceHandler.copyfiles(new FileInputStream("src/test/resources/hello.txt"), temp);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error while copying.");
        }
        try {
            InputStreamReader from = new InputStreamReader(new FileInputStream("src/test/resources/hello.txt"));
            InputStreamReader to = new InputStreamReader(new FileInputStream(temp));
            int readfrom, readto;
            boolean pass = false;
            while ((readfrom = from.read()) == (readto = to.read())) {
                if (readfrom == -1) {
                    // Pass if both files have the same content until the end character
                    pass = true;
                    break;
                }
            }

            from.close();
            to.close();

            assertTrue(pass, "the files did not have the same content...");
        } catch (Exception e) {
            fail(e.getStackTrace().toString());
        }
    }

    /**
     * Testing if getProducts creates a directory and file for products
     * in the user's home directory when it isn't already present.
     */
    @Test void getProductsTest() {
        File rootDir = new File(ResourceHandler.root);
        if (rootDir.exists()) {
            deleteDirectory(rootDir);
        }
        assertNotNull(ResourceHandler.getProducts());
    }

    /**
     * THIS CODE HAS BEEN COPIED FROM https://www.baeldung.com/java-delete-directory.
     * THIS IS NOT CODE WE WROTE.
     * @param directoryToBeDeleted
     * @return
     */
    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
