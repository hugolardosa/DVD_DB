package App;

import DataSet.Movie;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DVDUtils {

    public static void importCSV(String filepath, ArrayList<Movie> movies) {
        Path p = Paths.get(filepath);
        List<String> lista = null;
        try {
            lista = Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println("IOException reading file, import failed");
            return;
        }

        for (String linha : lista) {
            //if(linha.toLowerCase().contains("production year"))break;
            String[] content = linha.split(";");
            boolean seen = false;
            if (content[2].equalsIgnoreCase("true")) seen = true;
            boolean ogDVD = false;
            if (content[3].equalsIgnoreCase("true")) ogDVD = true;
            //try {
            movies.add(new Movie(content[0], content[1], seen, ogDVD, Integer.parseInt(content[4]), Integer.parseInt(content[5]), content[6], content[7]));
            //}catch (Exception e){
            //  continue;
            //}
        }

    }

    public static void sortMovies(ArrayList<Movie> movies) {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie movie, Movie t1) {
                return movie.getOrdertitle().compareToIgnoreCase(t1.getOrdertitle());
            }
        });
    }


}
