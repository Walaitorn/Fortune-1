package a13279.egco428.com.fortune;

/**
 * Created by pam on 10/31/2016.
 */
public class Fortune {

    private String fortunepic;
    private String fortunemsg;



    public Fortune( String fortunepic, String fortunemsg){

        this.fortunepic = fortunepic;
        this.fortunemsg = fortunemsg;

    }

    public String getFortunepic(){ return fortunepic; }
    public String getFortunemsg(){ return fortunemsg; }


    @Override public String toString(){return fortunepic;}

}
