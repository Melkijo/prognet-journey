import java.io.FileInputStream;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fs = null;
        //read test.txt in this src folder using relative path in window
        fs = new FileInputStream("CFileInputStream\\src\\test.txt");

        int temp = 0;
        while ((temp = fs.read()) != -1) {
            System.out.print((char) temp);
        }
        fs.close();


    }
}