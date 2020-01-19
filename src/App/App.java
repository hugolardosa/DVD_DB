package App;

import DataSet.Movie;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class App implements Serializable {
    private JPanel panel1;
    private JButton addMovieButton;
    private JButton importCSVButton;
    private JButton removeMovieButton;
    private JButton findMovieButton;
    private JButton saveDatabaseButton;
    private JButton openDatabaseButton;
    private JButton findMovieByOriginalButton;
    private JButton showDatabaseButton;
    private JButton editMovieButton;
    private JButton seeSetSeenButton;
    private JButton sortDatabaseButton;
    private JScrollPane scrollTable;
    private JTable table1;
    private Engine e;
    private String path;

    public App() {
        e = new Engine();
        importCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                path = shownavigatorCSV();
                JOptionPane.showConfirmDialog(null, "Loading CSV File");
                try {
                    e.importCSV(path);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            showDB();
            }

        });

        findMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String searchword = JOptionPane.showInputDialog("Search By Title");
                System.out.println(searchword);
                try {
                    JFrame framef = new JFrame("Found by Title");
                    JScrollPane toList = new JScrollPane(tableCreatorFind(e.findMovie(searchword)));
                    framef.setContentPane(toList);
                    framef.setLocationByPlatform(true);
                    framef.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    framef.pack();
                    framef.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Not Found");
                }
            }
        });
        openDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showMessageDialog(null,"Loading info from file");
                    FileInputStream fileIn = new FileInputStream("./database.dvddb");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    Engine engine = (Engine) in.readObject();
                    in.close();
                    fileIn.close();
                    e = engine;
                } catch (IOException i) {
                    JOptionPane.showMessageDialog(null,"Something went wrong, Try Again \n "+
                    "Make sure you have permission to access the file \n" +
                    "Don't forget to start the app (option 1)");
                } catch (ClassNotFoundException c) {
                    JOptionPane.showMessageDialog(null,"Engine class was not found \n" +
                    "Don't forget to start the app (option 1)");
                }
               showDB();
            }
        });
        saveDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showMessageDialog(null,"Saving info to file");
                    FileOutputStream fileOut =
                            new FileOutputStream("./database.dvddb");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(e);
                    out.close();
                    fileOut.close();
                    JOptionPane.showMessageDialog(null,"Info was saved to file src/app/data.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                    System.out.println("Something went wrong, Try Again");
                    System.out.println("Make sure you have permition to save the file");
                }
            }
        });
        findMovieByOriginalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String searchword = JOptionPane.showInputDialog("Search By Title");
                System.out.println(searchword);
                try {
                    JFrame framef = new JFrame("Found by Title");
                    JScrollPane toList = new JScrollPane(tableCreatorFind(e.findMovieOgTitle(searchword)));
                    framef.setContentPane(toList);
                    framef.setLocationByPlatform(true);
                    framef.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    framef.pack();
                    framef.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Not Found");
                }
            }
        });
        showDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showDB();
            }
        });
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame framef = new JFrame("Add Movie");

                JTextField title = new JTextField();
                JTextField titleOg = new JTextField();
                JTextField title = new JTextField();
                JPanel painel = new JPanel();
                painel.setLayout(new GridLayout(8,2));
                painel.add(new Label("Title: "));
                painel.
                framef.setContentPane(painel);
                framef.setLocationByPlatform(true);
                framef.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                framef.pack();
                framef.setVisible(true);
            }
        });
        editMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        removeMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        seeSetSeenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        sortDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    //mostra o navegador de ficheiros
    public static String shownavigatorCSV() {
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

    public static JTable tableCreatorCSV(ArrayList<Movie> movies) {
        String col[] = {"Title", "Original Title", "Seen", "Original DVD", "Production Year", "Running Time", "Genre", "Cover"};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        // The 0 argument is number rows.
        for (Movie m : movies) {
            ImageIcon icon = new ImageIcon(m.getCoverpath());
            Object[] toAdd = {m.getTitle(), m.getOgtitle().equalsIgnoreCase("0") ? m.getTitle() : m.getOgtitle(), m.isSeen() == true ? "X" : "", m.isOriginalDVD() == true ? "X" : "", m.getYear(), m.getTitle(), m.getGenere(), icon};
            tableModel.addRow(toAdd);

        }
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        return table;
    }

    public static JTable tableCreatorFind(java.util.List<Movie> movies) {
        String col[] = {"Title", "Original Title", "Seen", "Original DVD", "Production Year", "Running Time", "Genre", "Cover"};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        // The 0 argument is number rows.
        for (Movie m : movies) {
            ImageIcon label = new ImageIcon(m.getCoverpath());
            Object[] toAdd = {m.getTitle(), m.getOgtitle().equalsIgnoreCase("0") ? m.getTitle() : m.getOgtitle(), m.isSeen() == true ? "X" : "", m.isOriginalDVD() == true ? "X" : "", m.getYear(), m.getTitle(), m.getGenere(), label};
            tableModel.addRow(toAdd);
        }
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        return table;
    }

    public void showDB(){
        try {
            JFrame framef = new JFrame("DataBase");
            JScrollPane toList = new JScrollPane(tableCreatorCSV(e.getMovie()));
            framef.setContentPane(toList);
            framef.setLocationByPlatform(true);
            framef.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            framef.pack();
            framef.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not Found");
        }
    }



    public void showadd(){





    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DVD BD");
        frame.setContentPane(new App().panel1);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocation(0,0);
        frame.setVisible(true);
    }

}
/*
 class LabelRender implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        TableColumn tc = jTable.getColumn("Cover");
        tc.setMinWidth(100);
        tc.setMaxWidth(200);
        jTable.setRowHeight(100);
        return (Component)o;
    }
}

 */
