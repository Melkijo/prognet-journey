import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream readFile = null;
        readFile = new FileInputStream("Tugas2\\src\\jurnal_pkl.zip");
        ZipInputStream myZip = null;
        myZip = new ZipInputStream(readFile);

        ZipEntry temp;
        while ((temp = myZip.getNextEntry()) != null) {
            System.out.print(temp.getName() + " \n");
        }

        myZip.close();

    }
}