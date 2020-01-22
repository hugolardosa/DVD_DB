package App;

import DataSet.Movie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static void exportHtml(List<Movie> moveis) {
        PrintWriter toFile = null;
        try {
            toFile = new PrintWriter("index.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        toFile.println("<!DOCTYPE html>");
        toFile.println("<html>");
        toFile.println("<head>");
        toFile.println("<title> DVD DB</title>");
        toFile.println("<style>");
        toFile.println("#movies {");
        toFile.println("border-collapse: collapse;");
        toFile.println("width: 100%;");
        toFile.println("}");

        toFile.println("#movies td, #movies th {");
        toFile.println("            border: 1px solid #ddd;");
        toFile.println("            padding: 8px;");
        toFile.println("       }");

        toFile.println("#movies tr:nth-child(even){background-color: #f2f2f2;}");

        toFile.println(" #movies tr:hover {background-color: #ddd;}");

        toFile.println("#movies th {");
        toFile.println("           padding-top: 12px;");
        toFile.println("           padding-bottom: 12px;");
        toFile.println("           text-align: left;");
        toFile.println("            background-color: #4CAF50;");
        toFile.println(" color:white;");
        toFile.println("}");
        toFile.println("</style>");
                toFile.println("</head>");
        toFile.println("<body>");

        toFile.println("<h2>Movie Database</h2>");

        toFile.println("<table id= " + '"' + "movies" + '"' + ">");
        toFile.println("<tr>");
        toFile.println("<th>Title</th>");
        toFile.println("<th> Original Title </th> ");
        toFile.println("<th> Ordering Title </th> ");
        toFile.println("<th> Seen </th> ");
        toFile.println("<th> Original DVD </th>");
        toFile.println("<th> Production Year </th> ");
        toFile.println("<th> Running Time </th> ");
        toFile.println("<th> Genre </th> ");
        toFile.println("<th> Cover </th> ");
        toFile.println("  </tr > ");

        for (
                Movie m : moveis) {
            toFile.println("<tr>");
            toFile.println("<td>" + m.getTitle() + "</td>");
            toFile.println("<td>" + m.getOgtitle() + "</td>");
            toFile.println("<td>" + m.getOrdertitle() + "</td>");
            String seen = "";
            if (m.isSeen() == true) seen = "X";
            toFile.println("<td>" + seen + "</td>");
            String ogDVD = "";
            if (m.isOriginalDVD() == true) ogDVD = "X";
            toFile.println("<td>" + ogDVD + "</td>");
            toFile.println("<td>" + m.getYear() + "</td>");
            toFile.println("<td>" + m.getTime() + "</td>");
            toFile.println("<td>" + m.getGenere() + "</td>");
            toFile.println("<td>" + "<img src=" + m.getCoverpath() + " alt=" + '"' + m.getOrdertitle().toLowerCase() + '"' + " height=110 width=95>" + "</td>");
            toFile.println("</tr>");
        }
        toFile.println("</table > ");

        toFile.println("</body > ");
        toFile.println("</html > ");


    }


}
