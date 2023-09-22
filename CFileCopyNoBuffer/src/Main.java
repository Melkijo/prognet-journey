import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String inFIleStr = "CFileCopyNoBuffer\\src\\test.txt";
        String outFIleStr = "CFileCopyNoBuffer\\src\\test2.txt";
        FileInputStream in = null;
        FileOutputStream out = null;
        long startTime, elapsedTime;  // for speed benchmarking

        // Print file length
        File fileIn = new File(inFIleStr);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try{
            in = new FileInputStream(inFIleStr);
            out = new FileOutputStream(outFIleStr);
            startTime = System.nanoTime();
            int byteRead;
            // Read a raw byte, returns an int of 0 to 255.
            while((byteRead = in.read()) != -1){
                // Write the least-significant byte of int, drop the upper 3 bytes
                out.write(byteRead);
            }
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        }
        catch (IOException e){
            System.out.println("File tidak ditemukan");
        }
        finally {
            try{
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            }
            catch (IOException e){
                System.out.println("Tidak Bisa Membaca File");
            }
        }
    }
}