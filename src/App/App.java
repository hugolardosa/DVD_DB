package App;

import DataSet.Movie;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
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
    private JButton sortDatabaseButton;
    private JButton setSeenButton;
    private JButton exportToHTMLButton;
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
                    String path = shownavigatorDB();
                    JOptionPane.showMessageDialog(null, "Loading info from file");
                    FileInputStream fileIn = new FileInputStream(path);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    Engine engine = (Engine) in.readObject();
                    in.close();
                    fileIn.close();
                    e = engine;
                } catch (IOException i) {
                    JOptionPane.showMessageDialog(null, "Something went wrong, Try Again \n " +
                            "Make sure you have permission to access the file");
                } catch (ClassNotFoundException c) {
                    JOptionPane.showMessageDialog(null, "Engine class was not found");
                }
                showDB();
            }
        });
        saveDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String path = shownavigatorDB();
                    JOptionPane.showMessageDialog(null, "Saving info to file");
                    FileOutputStream fileOut =
                            new FileOutputStream(path);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(e);
                    out.close();
                    fileOut.close();
                    JOptionPane.showMessageDialog(null, "Info was saved to file" + path);
                } catch (IOException i) {
                    i.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Something went wrong, Try Again" + "Make sure you have permition to save the file");
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
                showaddMovie();
            }
        });
        editMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new MovieTableCreator(e.getMovie());
                    }
                });
            }
        });
        removeMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EventQueue.invokeLater(new Runnable()
                {
                    String searchword = JOptionPane.showInputDialog("Search By Title");
                    public void run()
                    {
                        ItemDeletion.createAndShowGUI(e.findMovie(searchword));
                    }
                });
            }
        });
        sortDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showConfirmDialog(null, "Sort Database?");
                    e.sortList();
                    JOptionPane.showConfirmDialog(null, "Sorted");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Not Sorted");
                }

            }
        });
        setSeenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String searchword = JOptionPane.showInputDialog("Search By Title");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new MovieTableCreator(e.findMovie(searchword));
                    }
                });
            }
        });
        exportToHTMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showConfirmDialog(null, "Do you want to export to HTML?");
                    DVDUtils.exportHtml(e.getMovie());
                    JOptionPane.showMessageDialog(null,"Exported!");
                }catch (Exception exp){
                    JOptionPane.showMessageDialog(null,"ERROR!");
                }
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

    public static String shownavigator() {
        //navegador de ficheiros
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image File", "jpg", "png", "jpge", "jpeg", "gif");
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

    public static String shownavigatorDB() {
        //navegador de ficheiros
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Database File", "dvddb");
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
        String col[] = {"Title", "Original Title", "Ordering Title", "Seen", "Original DVD", "Production Year", "Running Time", "Genre", "Cover"};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        // The 0 argument is number rows.
        for (Movie m : movies) {
            BufferedImage myPicture = null;
            try {
               myPicture = ImageIO.read(new File(m.getCoverpath().toLowerCase()));
            } catch (IOException ex) {
              ex.printStackTrace();
            }
            Object[] toAdd = {m.getTitle(), m.getOgtitle().equalsIgnoreCase("0") ? m.getTitle() : m.getOgtitle(), m.getOrdertitle(), m.isSeen() == true ? "X" : "", m.isOriginalDVD() == true ? "X" : "", m.getYear(), m.getTime(), m.getGenere(), m.getCoverpath()};
            tableModel.addRow(toAdd);

        }
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        return table;
    }


    public static JTable tableCreatorFind(java.util.List<Movie> movies) {
                    String col[] = {"Title", "Original Title", "Ordering Title", "Seen", "Original DVD", "Production Year", "Running Time", "Genre", "Cover"};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        // The 0 argument is number rows.
        for (Movie m : movies) {
            BufferedImage myPicture = null;
            /*
            try {
                myPicture = ImageIO.read(new File(m.getCoverpath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            */
            Object[] toAdd = {m.getTitle(), m.getOgtitle().equalsIgnoreCase("0") ? m.getTitle() : m.getOgtitle(), m.getOrdertitle(), m.isSeen() == true ? "X" : "", m.isOriginalDVD() == true ? "X" : "", m.getYear(), m.getTime(), m.getGenere(),  m.getCoverpath()};//new JLabel(new ImageIcon(myPicture))};
            tableModel.addRow(toAdd);

        }
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        return table;
    }

    public void showDB() {
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


    public void showaddMovie() {
        JLabel jcomp1;
        JTextField t;
        JLabel jcomp3;
        JTextField tOg;
        JLabel jcomp5;
        JTextField year;
        JLabel jcomp7;
        JTextField time;
        JLabel jcomp9;
        JTextField genere;
        JLabel jcomp11;
        JCheckBox seen;
        JLabel jcomp13;
        JCheckBox ogDVD;
        JLabel jcomp15;
        JButton find;
        JButton save;
        JButton cancel;

        //construct components
        jcomp1 = new JLabel("Title");
        t = new JTextField(1);
        jcomp3 = new JLabel("Original Title");
        tOg = new JTextField(1);
        jcomp5 = new JLabel("Year");
        year = new JTextField(1);
        jcomp7 = new JLabel("Running Time");
        time = new JTextField(1);
        jcomp9 = new JLabel("Genere");
        genere = new JTextField(1);
        jcomp11 = new JLabel("Seen");
        seen = new JCheckBox("Was Seen");
        jcomp13 = new JLabel("Original DVD");
        ogDVD = new JCheckBox("Is Original");
        jcomp15 = new JLabel("Cover Path");
        find = new JButton("Find");
        JTextField path = new JTextField(1);
        save = new JButton("Save");
        cancel = new JButton("Cancel");

        JPanel p1 = new JPanel();
        //adjust size and set layout
        p1.setPreferredSize(new Dimension(667, 379));
        GridLayout layout = new GridLayout(10, 2, 5, 4);
        p1.setLayout(layout);

        //add components
        p1.add(jcomp1);
        p1.add(t);
        p1.add(jcomp3);
        p1.add(tOg);
        p1.add(jcomp5);
        p1.add(year);
        p1.add(jcomp7);
        p1.add(time);
        p1.add(jcomp9);
        p1.add(genere);
        p1.add(jcomp11);
        p1.add(seen);
        p1.add(jcomp13);
        p1.add(ogDVD);
        p1.add(jcomp15);
        p1.add(find);
        p1.add(new JLabel());
        p1.add(path);
        p1.add(save);
        p1.add(cancel);

        JFrame frame = new JFrame("Movie Editor");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(p1);
        frame.pack();
        frame.setVisible(true);


        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String p = shownavigator();
                path.setText(p);
            }
        });
        save.addActionListener(b -> {

            String titulo = t.getText();
            String tituloOg = tOg.getText();
            int y = 0;
            int tim = 0;
            try {
                y = Integer.parseInt(year.getText());
                tim = Integer.parseInt(time.getText());
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Not a valid number on field year, or Running time");
            }

            e.addMovie(new Movie(t.getText(), tOg.getText(), seen.isSelected(), ogDVD.isSelected(), y, tim, genere.getText(), path.getText()));
            JOptionPane.showMessageDialog(null, "Added Successfully");
            frame.hide();
        });
        cancel.addActionListener(bb -> {
            frame.hide();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DVD BD");
        frame.setContentPane(new App().panel1);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocation(0, 0);
        frame.setVisible(true);
    }

}
