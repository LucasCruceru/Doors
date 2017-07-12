package ro.fortech.dto;//Created by internship on 12.07.2017.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private Date date;
    private String string;
    private String doorName;

}
