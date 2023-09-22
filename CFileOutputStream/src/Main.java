import java.io.FileInputStream;
import java.io.FileOutputStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String text = "Hello World";
        FileOutputStream myFile = null;

        try{
            myFile = new FileOutputStream("CFileOutputStream\\src\\test.txt");
            myFile.write(text.getBytes());
            myFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}