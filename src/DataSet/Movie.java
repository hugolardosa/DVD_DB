package DataSet;

import java.util.Objects;

public class Movie {
    private int id;
    private String title;
    private String ogtitle;
    private int rating;
    private boolean seen;
    private boolean originalDVD;

    public Movie(int id, String title, String ogtitle) {
        this.id = id;
        this.title = title;
        this.ogtitle = ogtitle;
        this.rating = 0;
        this.seen = false;
        this.originalDVD = true;
    }

    public Movie(int id, String title, String ogtitle, int rating, boolean seen, boolean ogDVD) {
        this.id = id;
        this.title = title;
        this.ogtitle = ogtitle;
        this.rating = rating;
        this.seen = seen;
        this.originalDVD = ogDVD;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                rating == movie.rating &&
                seen == movie.seen &&
                title.equals(movie.title) &&
                ogtitle.equals(movie.ogtitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ogtitle, rating, seen);
    }

    @Override
    public String toString() {
        String s =  id + "\n" +
                "Title -> " + title + '\n' +
                "Og Title -> " + ogtitle + '\n' +
                "Rating -> " + rating + "\n";
        if(this.seen == true)s += "[Seen] \n";
        else s+="[Not Seen] \n";
        if(this.originalDVD == false)s+="[Not Original DVD] \n";
        return s;
    }
}
