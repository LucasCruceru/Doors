package ro.fortech.dto;//Created by internship on 12.07.2017.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoorDTO {

    private Long id;
    private boolean isClosed;
    private String name;

}
