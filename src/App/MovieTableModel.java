package App;

import DataSet.Movie;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;


public class MovieTableModel extends AbstractTableModel {
    private final List<Movie> movies;

    private final String[] columnNames = new String[]{
            "Title", "Original Title", "Ordering Title", "Seen", "Original DVD", "Production Year", "Running Time", "Genre", "Cover"
    };
    private final Class[] columnClass = new Class[]{
            String.class,String.class,String.class, Boolean.class,Boolean.class, Integer.class, Integer.class, String.class, JLabel.class
    };

    public MovieTableModel(List<Movie> m) {
        this.movies = m;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie row = movies.get(rowIndex);
        if (0 == columnIndex) {
            return row.getTitle();
        } else if (1 == columnIndex) {
            return row.getOgtitle();
        } else if (2 == columnIndex) {
            return row.getOrdertitle();
        } else if (3 == columnIndex) {
            return row.isSeen();
        }else if (4 == columnIndex) {
            return row.isOriginalDVD();
        }else if (5 == columnIndex) {
            return row.getYear();
        }else if (6 == columnIndex) {
            return row.getTime();
        }else if (7 == columnIndex) {
            return row.getGenere();
        }else if (8 == columnIndex) {
            ImageIcon image = new ImageIcon(row.getCoverpath());
            JLabel imagelabel = new JLabel(image, JLabel.CENTER);
            return imagelabel;
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Movie row = movies.get(rowIndex);
        if (0 == columnIndex) {
            row.setTitle((String) aValue);
        } else if (1 == columnIndex) {
            row.setOgtitle((String) aValue);
        } else if (2 == columnIndex) {
            row.setOrdertitle((String) aValue);
        } else if (3 == columnIndex) {
            row.setSeen((Boolean) aValue);
        }else if (4 == columnIndex) {
            row.setOriginalDVD((Boolean) aValue);
        }else if (5 == columnIndex) {
            row.setYear((Integer) aValue);
        }else if (6 == columnIndex) {
            row.setTime((Integer) aValue);
        }else if (7 == columnIndex) {
            row.setGenere((String) aValue);
        }
    }

}

