package ro.fortech.entities;


import java.util.Date;

public class Log {

    private Date date;
    private String username;
    private String doorName;

    public Log(Date date, String username, String doorName) {
        this.date = date;
        this.username = username;
        this.doorName = doorName;
    }



    //private static Log INSTANCE = new Log();

//    public static Log currentInstance(){
//        return INSTANCE;
//    }
}
