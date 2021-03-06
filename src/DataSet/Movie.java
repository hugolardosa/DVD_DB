package DataSet;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.Objects;

public class Movie implements Serializable {
    private String title;
    private String ogtitle;
    private String ordertitle;
    private int rating;
    private int year;
    private int time;
    private String genere;
    private boolean seen;
    private String coverpath;
    private boolean originalDVD;

    public Movie(String title, String ogtitle, boolean seen, boolean originalDVD, int year, int time, String genere, String coverpath) {
        this.title = title;
        this.ogtitle = ogtitle;
        this.ordertitle = stripAccents(this.title);
        this.year = year;
        this.time = time;
        this.genere = genere;
        this.seen = seen;
        this.coverpath = coverpath;
        this.originalDVD = originalDVD;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOgtitle(String ogtitle) {
        this.ogtitle = ogtitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTime() {
        return time;
    }

    public String getOrdertitle() {
        return ordertitle;
    }

    public void setOrdertitle(String ordertitle) {
        this.ordertitle = ordertitle;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getCoverpath() {
        return coverpath;
    }

    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath;
    }

    public String getTitle() {
        return title;
    }

    public String getOgtitle() {
        return ogtitle;
    }

    public int getRating() {
        return rating;
    }

    public boolean isSeen() {
        return seen;
    }

    public boolean isOriginalDVD() {
        return originalDVD;
    }

    public void setOriginalDVD(boolean originalDVD) {
        this.originalDVD = originalDVD;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public static String stripAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return  rating == movie.rating &&
                seen == movie.seen &&
                title.equals(movie.title) &&
                ogtitle.equals(movie.ogtitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ogtitle, rating, seen);
    }

    @Override
    public String toString() {
        String s =  title + " | " + ogtitle + " | " +
                "R:" + rating + " ";
        if(this.seen == true)s += "[Seen] ;";
        else s+="[Not Seen] ;";
        if(this.originalDVD == false)s+="[Not Original DVD]";
        return s;
    }
}
