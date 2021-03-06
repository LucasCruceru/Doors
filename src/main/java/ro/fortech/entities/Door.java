package ro.fortech.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Door {
    
    @Id
    @GeneratedValue
    private Long id;

    private boolean isClosed; //status

    private String name;

    public Long getId() {return id;}

    public String getName() {return name;}

    public boolean isClosed() {return isClosed;}

    public Door(String name, boolean isClosed) {
        this.name = name;
        this.isClosed = isClosed;
    }

    public boolean open(){
        return this.isClosed = false;
    }

    public boolean close(){
        return this.isClosed = true;
    }

    Door(){}

    public void updateDoor(Door input) {
        this.name = input.name;
        this.isClosed = input.isClosed;
    }
}
