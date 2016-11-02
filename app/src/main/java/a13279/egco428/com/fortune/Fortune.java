package a13279.egco428.com.fortune;

/**
 * Created by pam on 10/31/2016.
 */
public class Fortune {
    private int fortuneid;
    private String fortunepic;
    private String fortunemsg;
    private String date;


    public Fortune(int fortuneid, String fortunepic, String fortunemsg, String date){
        this.fortuneid = fortuneid;
        this.fortunepic = fortunepic;
        this.fortunemsg = fortunemsg;
        this.date = date;
    }

    public int getFortuneid(){ return fortuneid; }
    public String getFortunepic(){ return fortunepic; }
    public String getFortunemsg(){ return fortunemsg; }
    public String getDate(){ return date; }

    @Override public String toString(){return fortunepic;}

}
