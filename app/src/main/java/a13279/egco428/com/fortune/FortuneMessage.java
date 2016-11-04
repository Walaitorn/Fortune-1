package a13279.egco428.com.fortune;

/**
 * Created by pam on 11/3/2016.
 */
public class FortuneMessage {
    private long id;
    private String comment;
    private String date;
    private String pic;
    private String color;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate(){ return date;}

    public void setDate(String date){this.date = date;}

    public String getPic(){return pic;}

    public void setPic(String pic){this.pic = pic;}

    public String getColor(){return color;}

    public void setColor(String color){this.color = color;}



    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}
