package App;

import DataSet.Movie;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {
    private ArrayList<Movie> movies;

    public Engine() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie e) {
        movies.add(e);
    }

    public void removeMovie(Movie e) {
        movies.remove(e);
    }

    public void removeMovie(int ID) {
        movies.remove(movies.get(ID));
    }

    public List<Movie> findMovie(String keyword) {
        return movies.stream().filter(f -> f.getTitle().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    public List<Movie> findMovieOgTitle(String keyword) {
        return movies.stream().filter(f -> f.getOgtitle().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    public void importCSV(String filepath) {
        DVDUtils.importCSV(filepath, this.movies);
    }

    public void sortList() {
        DVDUtils.sortMovies(this.movies);
    }

    public String listMovies() {
        String r = "Filmes";
        for (Movie m : movies) {
            r += m + "\n";
        }
        return r;
    }

    public static void main(String[] args) {
        //Test Program
        Engine p = new Engine();
        p.importCSV("DM/Collection5.csv");
        System.out.println(p.listMovies());
        System.out.println();
        System.out.println("---------Encontrou----------");
        p.findMovie("lara").stream().forEach(f -> System.out.println(f));
        System.out.println();
        System.out.println("---------Encontrou----------");
        p.findMovie("vid").stream().forEach(f -> System.out.println(f));
        System.out.println();
        System.out.println("---------Encontrou----------");
        p.findMovieOgTitle("Life").stream().forEach(f -> System.out.println(f));

    }
}