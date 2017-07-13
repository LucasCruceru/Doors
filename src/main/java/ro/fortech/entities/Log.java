package ro.fortech.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Log {
    @Id
    @GeneratedValue
    private Long id;



    private Date date;
    private String username;
    private String doorName;

    public Log(Date date, String username, String doorName) {
        this.date = date;
        this.username = username;
        this.doorName = doorName;
    }

}
