package App;


import DataSet.Movie;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class MovieTableCreator extends JFrame
{
    public MovieTableCreator(List<Movie> movies)
    {
        //create the model
        MovieTableModel model = new MovieTableModel(movies);
        //create the table
        JTable table = new JTable(model);

        //add the table to the frame
        this.add(new JScrollPane(table));

        this.setTitle("Movie Editor");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
