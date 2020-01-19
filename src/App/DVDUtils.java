package App;

import DataSet.Movie;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DVDUtils {

    public static void importCSV(String filepath, ArrayList<Movie> movies){
        Path p = Paths.get(filepath);
        List<String> lista = null;
        try {
           lista = Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println("IOException reading file, import failed");
            return;
        }

        for(String linha:lista){
          String[] content = linha.split(",");
          boolean seen = Boolean.parseBoolean(content[2]);
          boolean ogDVD = Boolean.parseBoolean(content[3]);
          movies.add(new Movie(content[0],content[1],seen,ogDVD,Integer.parseInt(content[4]),Integer.parseInt(content[5]),content[6],content[7]));
        }

    }


}
