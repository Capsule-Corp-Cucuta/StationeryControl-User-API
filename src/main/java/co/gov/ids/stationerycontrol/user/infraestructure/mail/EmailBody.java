package co.gov.ids.stationerycontrol.user.infraestructure.mail;

import lombok.Data;

@Data
public class EmailBody {

    private String email;
    private String content;
    private String subject;

}
