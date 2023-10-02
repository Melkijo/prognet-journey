
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener  {
    JButton button;
    JTextField textField;
    JTextArea textArea;
    JScrollPane scrollPane;
    JTable table;
    JProgressBar progressBar;
    ArrayList<String> downloadFileName = new ArrayList<>();
    ArrayList<String> downloadFilePath = new ArrayList<>();
    MyFrame(){
        this.setTitle("Tugas 3 Pemrograman Internet");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x272727));
//        this.setResizable(false);

        JLabel label = new JLabel("Download File Using URL");
        label.setForeground(new Color(0xFFFFFF));
        label.setFont(new Font("Poppins", Font.BOLD, 28));

        button = new JButton("Download");
        button.addActionListener(this);
        button.setBackground(new Color(0x50D890));
        button.setForeground(new Color(0xFFFFFF));
        button.setPreferredSize(new Dimension(125, 40));
        button.setFont(new Font("Poppins", Font.BOLD, 14));

        JLabel label2 = new JLabel("Input URL: ");
        label2.setForeground(new Color(0xFFFFFF));
        label2.setFont(new Font("Poppins", Font.PLAIN, 14));

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(350, 40));
        textField.setBackground( new Color(0x272727));
        textField.setForeground(new Color(0xFFFFFF));
        textField.setFont(new Font("Poppins", Font.PLAIN, 14));
        textField.setCaretColor(new Color(0xFFFFFF));
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)));

        textArea = new JTextArea(15, 35);
        textArea.setEditable(false);
        textArea.setBackground(new Color(0x272727));
        textArea.setForeground(new Color(0xFFFFFF));
        textArea.setFont(new Font("Poppins", Font.PLAIN, 14));
        scrollPane = new JScrollPane(textArea);

        String[] columnNames = {"No", "Name", "Path","Delete", "View" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        table = new JTable();
        table.setBackground(new Color(0x272727));
        table.setForeground(new Color(0xFFFFFF));
        table.setGridColor(new Color(0x50D890));
        table.setFont(new Font("Poppins", Font.PLAIN, 14));
//        table.setEnabled(false);
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        table.setShowGrid(true);
        table.setModel(model);
        table.setIntercellSpacing(new Dimension(10, 10));
        table.setRowMargin(10);

        JScrollPane sp = new JScrollPane(table);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); //give padding to each of the component
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(label, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(label2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(textField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(button, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(sp, gbc);

        this.pack();
        this.setVisible(true);

    }

     static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                setText(value.toString());
            } else {
                setText("");
            }
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String actionType;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                // Handle the button click based on the action type
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                String actionType = (String) table.getValueAt(row, column);

                if ("Delete".equals(actionType)) {
                    String filePath = downloadFilePath.get(row);
                    String fileName = downloadFileName.get(row);

                    try {
                        java.io.File file = new java.io.File(filePath);
                        if(file.delete()){
                            downloadFileName.remove(row);
                            downloadFilePath.remove(row);
                            updateTable();
                            JOptionPane.showMessageDialog(null, "File " + fileName + " deleted successfully");
                        }else{
                            JOptionPane.showMessageDialog(null, "Delete failed: " + "File tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Delete failed: " + ex.getMessage());
                    }
                } else if ("View".equals(actionType)) {
                    String filePath = downloadFilePath.get(row);
                    try {
                        Desktop.getDesktop().open(new java.io.File(filePath));
                    } catch (Exception ex) {
                        System.out.println("View failed: " + ex.getMessage());
                    }

                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            actionType = (String) value;
            button.setText(actionType);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return actionType;
        }
    }


    private void updateTable() {
        SwingUtilities.invokeLater(() -> {
            String[] columnNames = {"No", "Name", "Path", "Delete", "View"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            Integer count = 1;
            for (int i = 0; i < downloadFileName.size(); i++) {
                String[] data = {String.valueOf(count), downloadFileName.get(i), downloadFilePath.get(i), "Delete","View"};
                model.addRow(data);
                count++;
            }

            table.setModel(model);

            // Create a custom renderer and editor for the "Action" column
            table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
            table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));

            table.getColumn("View").setCellRenderer(new ButtonRenderer());
            table.getColumn("View").setCellEditor(new ButtonEditor(new JCheckBox()));

        });
    }

    public String downloadFile(String url) {
        InputStream in = null;
        FileOutputStream fOut = null;
        String fileName = null;

        try {
            URL remoteFile = new URL(url);
            URLConnection fileStream = remoteFile.openConnection();

            String paths = remoteFile.getPath();
            String[] path = paths.split("/");
            fileName = path[path.length-1];

            fOut = new FileOutputStream(fileName);
            in = fileStream.getInputStream();

            int data;
            long totalSize = fileStream.getContentLengthLong(); // Get the total file size

            long downloadedSize = 0;

            while ((data = in.read()) != -1) {
                fOut.write(data);
                downloadedSize++;

                int percentage = (int) ((downloadedSize * 100) / totalSize);
                progressBar.setValue(percentage);
            }

            String filePath = System.getProperty("user.dir") + "\\" + fileName;

            downloadFilePath.add(filePath);

        } catch (Exception ex) {
            System.out.println("Download failed: " + ex.getMessage());
        } finally {
            System.out.println("File telah berhasil didownload broh");
            try {
                assert in != null;
                in.close();
                fOut.flush();
                fOut.close();
            } catch (Exception e) {
                System.out.println("Download failed: " + e.getMessage());
            }
        }

        return fileName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String url = textField.getText();
            if(url.isEmpty()){
                JOptionPane.showMessageDialog(null, "URL tidak boleh kosong");
                return;
            }
            // Create and configure a progress bar
            progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true); // Display a percentage
            progressBar.setIndeterminate(true); // Use an indeterminate progress bar

            // Create a dialog to show the progress bar
            JDialog progressDialog = new JDialog(this, "Downloading File", true);
            progressDialog.add(progressBar);
            progressDialog.pack();
            progressDialog.setLocationRelativeTo(this);

            // Start a new thread to perform the download
            Thread downloadThread = new Thread(() -> {
                try {
                    // Perform the download (replace with your actual download logic)
                    String fileName = downloadFile(url);

                    // Close the progress dialog when the download is complete
                    progressDialog.dispose();
                    if(fileName == null){
                        JOptionPane.showMessageDialog(null, "Download failed: " + "File tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // check if filename already exist in downloadFileName
                    if(downloadFileName.contains(fileName)){
                        JOptionPane.showMessageDialog(null, "Download failed: " + "File sudah pernah didownload broh", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    downloadFileName.add(fileName);

                    // Show a success message
                    JOptionPane.showMessageDialog(null, "File telah berhasil didownload " + fileName);

                    // Update the table if needed
                    updateTable();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    // Handle any exceptions here
                    progressDialog.dispose();
                    JOptionPane.showMessageDialog(null, "Download failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Start the download thread
            downloadThread.start();

            // Show the progress dialog
            progressDialog.setVisible(true);
        }
    }
}
