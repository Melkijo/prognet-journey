import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        FileInputStream myFile = null;
        BufferedInputStream buff = null;

        try{
            myFile = new FileInputStream("CBufferedInput\\src\\test.txt");
            buff = new BufferedInputStream(myFile);
            boolean eof = false;
            while(!eof){
                int byteValue = buff.read();
                System.out.print(byteValue + " ");
                if(byteValue == -1){
                    eof = true;
                }
            }
        }
        catch (IOException e){
            System.out.println("Tidak Bisa Membaca File");
        }
        finally {
            if(myFile != null){
                try{
                    buff.close();
                    myFile.close();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        }
    }
}