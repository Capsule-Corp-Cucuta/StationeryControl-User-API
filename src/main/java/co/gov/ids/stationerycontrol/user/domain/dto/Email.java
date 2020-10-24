package co.gov.ids.stationerycontrol.user.domain.dto;

import lombok.Data;

@Data
public class Email {

    private String to;
    private String subject;
    private String content;

}
