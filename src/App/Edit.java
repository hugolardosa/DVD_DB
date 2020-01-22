package App;

import DataSet.Movie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Edit {
    public JPanel panel1;
    private JTable table1;
    private JButton editButton;


    public Edit(ArrayList<Movie> filmes, Engine e) throws IOException {
        table1 = App.tableCreatorEdition(filmes);
        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < filmes.size(); i++) {
                    Object b = table1.getValueAt(i, 9);
                    String bot = (String) b;
                    if (bot.equalsIgnoreCase("X")) {
                        Object[] filme = new Object[9];
                        for (int j = 0; j < 9; j++) {
                            Object newObj = table1.getValueAt(i, j);
                            filme[j] = newObj;
                        }
                        e.getMovie().get(i).setTitle((String) filme[0]);
                        e.getMovie().get(i).setOgtitle((String) filme[1]);
                        e.getMovie().get(i).setOrdertitle((String) filme[2]);
                        String seen = (String) filme[3];
                        String ogDVD = (String) filme[4];
                        e.getMovie().get(i).setSeen(seen.equalsIgnoreCase("X"));
                        e.getMovie().get(i).setOriginalDVD(ogDVD.equalsIgnoreCase("X"));

                        String year = (String) filme[5];
                        String time = (String) filme[6];
                        e.getMovie().get(i).setTime(Integer.parseInt(time));
                        e.getMovie().get(i).setYear(Integer.parseInt(year));
                        e.getMovie().get(i).setGenere((String) filme[7]);
                        e.getMovie().get(i).setCoverpath((String) filme[8]);

                    }
                }
            }

        });
    }
}
