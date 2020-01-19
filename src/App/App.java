package App;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class App {
    private JPanel panel1;
    private JButton addMovieButton;
    private JButton importCSVButton;
    private JButton removeMovieButton;
    private JButton findMovieButton;
    private JButton saveDatabaseButton;
    private JButton openDatabaseButton;
    private JList list1;
    private Engine e;
    private String path;

    public App() {
        importCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                path = shownavigatorCSV();
                JOptionPane.showConfirmDialog(null, "Loading CSV File");
                e.importCSV(path);
            }
        });
    }

    //mostra o navegador de ficheiros
    public static String shownavigatorCSV(){
        //navegador de ficheiros
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
        chooser.setFileFilter(filter);
        int returnValue = chooser.showOpenDialog(null);
        //int returnValue = chooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
            //frame.add(createPanelImage(frame));
            //image = new File(selectedFile.getAbsolutePath());
        }
        return "";
    }

    public static void main(String[] args) {
        JFrame frame =new JFrame("DVD BD");
        frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
