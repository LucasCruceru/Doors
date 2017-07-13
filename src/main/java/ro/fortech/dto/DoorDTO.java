package ro.fortech.dto;//Created by internship on 12.07.2017.

public class DoorDTO {

    private Long id;
    private String name;
    private boolean isClosed;

    public DoorDTO() {
    }

    public DoorDTO(Long id, String name, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.isClosed = isClosed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
