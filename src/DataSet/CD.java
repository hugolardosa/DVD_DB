package DataSet;

import java.util.Objects;

public class CD {
    private int id;
    private String title;
    private int rating;
    private boolean originalCD;

    public CD(int id, String title, int rating, boolean originalCD) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.originalCD = originalCD;
    }

    @Override
    public String toString() {
        String s =  id + "\n" +
                "Title -> " + title + '\n' +
                "Rating -> " + rating;
        if(this.originalCD == false)s+="[Not Original CD] \n";
        return s;
    }
}
