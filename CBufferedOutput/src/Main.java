import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int[] someData = {1, 2, 3, 4, 5};
        FileOutputStream myFile = null;
        BufferedOutputStream buff = null;

        try{
            myFile = new FileOutputStream("CBufferedOutput\\src\\test.txt");
            buff = new BufferedOutputStream(myFile);
            for(int i = 0; i < someData.length; i++){
                buff.write(someData[i]);
            }
            buff.close();
            myFile.close();
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