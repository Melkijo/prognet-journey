import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        MyFrame myFrame = new MyFrame();

        // add icon to myFrame
        ImageIcon icon = new ImageIcon("Tugas3\\src\\assets\\icon.png");
        myFrame.setIconImage(icon.getImage());

        // make myFrame in the middle of the screen
        myFrame.setLocationRelativeTo(null);
    }
}