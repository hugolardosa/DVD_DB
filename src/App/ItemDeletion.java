//code with modifications from camickr
// @ https://stackoverflow.com/questions/29812387/removing-items-from-an-arraylist-through-a-jtable

package App;

import DataSet.Movie;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class ItemDeletion extends JPanel
{
    private JList<Movie> list;
    private JTable table;

    public ItemDeletion(java.util.List<Movie> listModel)
    {
        setLayout( new BorderLayout(5, 5) );

        //  Add the list

        DefaultListModel<Movie> lModel = new DefaultListModel<Movie>();

        for (Movie item: listModel)
            lModel.addElement( item );

        list = new JList<Movie>( lModel );

        JButton listDelete = new JButton( "Delete From List" );
        listDelete.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DefaultListModel model = (DefaultListModel)list.getModel();
                int row = list.getSelectedIndex();

                while (row != -1)
                {
                    model.removeElementAt( row );
                    row = list.getSelectedIndex();

                }
            }
        });

        JPanel listPanel = new JPanel( new BorderLayout(5, 5) );
        listPanel.add(new JScrollPane( list ), BorderLayout.CENTER);
        listPanel.add(listDelete, BorderLayout.PAGE_END);


        table = new JTable( new MovieTableModel(listModel) );

        table.setAutoCreateRowSorter(true);
        ((DefaultRowSorter)table.getRowSorter()).toggleSortOrder(0);

        JButton tableDelete = new JButton( "Delete From Table" );
        tableDelete.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                int row = table.getSelectedRow();

                while (row != -1)
                {
                    int modelRow = table.convertRowIndexToModel( row );
                    model.removeRow( modelRow );
                    row = table.getSelectedRow();
                }
            }
        });

        JPanel tablePanel = new JPanel( new BorderLayout(5, 5) );
        tablePanel.add(new JScrollPane( table ), BorderLayout.CENTER);
        tablePanel.add(tableDelete, BorderLayout.PAGE_END);

        add(listPanel, BorderLayout.LINE_START);
        add(tablePanel, BorderLayout.LINE_END);
    }

    public static void createAndShowGUI(java.util.List<Movie> movies)
    {
        JFrame frame = new JFrame("Multiple Item Deletion");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(new ItemDeletion(movies), BorderLayout.NORTH);
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

}