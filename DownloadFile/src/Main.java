import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        InputStream in=null;
        FileOutputStream fOut=null;
        // Press Alt+Enter with your caret at the highlighted text to see how
        try {
            URL remoteFile = new URL
                    ("https://satljhmpvolluoiwdpij.supabase.co/storage/v1/object/public/oikumene/kegiatan/5214532.jpg");
            URLConnection fileStream = remoteFile.openConnection();

            String paths = remoteFile.getPath();
            String[] path = paths.split("/");
            String fileName = path[path.length-1];
            System.out.println(fileName);
//membuka input dan output stream
            fOut = new FileOutputStream(fileName);
            in = fileStream.getInputStream();
//menyimpan file
            int data;
            while ((data=in.read())!=-1){
                fOut.write(data);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            System.out.println("File telah berhasil didownload");
            try {
                in.close();
                fOut.flush();
                fOut.close();
            }
            catch (Exception e){
                e.printStackTrace(); }
        }

    }
}