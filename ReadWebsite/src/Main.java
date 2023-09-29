import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String nextLine;

        BufferedReader reader = null;
        try {
            URL url = new URL("https://64872a94beba6297279025c6.mockapi.io/articles");
            URLConnection urlConn = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((nextLine = reader.readLine()) != null) {
                System.out.println(nextLine);
            }
        } catch (IOException e) {
            System.out.println("can't read File");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e1) {
                    System.out.println("can't close reader");
                }
            }
        }
    }
}