package a13279.egco428.com.fortune;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pam on 10/31/2016.
 */
public class DataProvider {
    private  static List<Fortune> data = new ArrayList<>();
    public static List<Fortune> getData(){ return data; }
    static {
        data.add(new Fortune(1,"opened_cookie_grade","You will get A","10"));
        data.add(new Fortune(2,"opened_cookie_lucky","You're lucky","10"));
        data.add(new Fortune(3,"opened_cookie_surprise","Something surprise you today","10"));
        data.add(new Fortune(4,"opened_cookie_panic","Don't panic","10"));
        data.add(new Fortune(5,"opened_cookie_work","work harder","10"));
    }

}
