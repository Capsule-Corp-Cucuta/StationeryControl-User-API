package co.gov.ids.stationerycontrol.user.domain.dto;

import lombok.Data;

@Data
public class User {

    private String username;
    private String identificationCard;
    private String name;
    private String email;
    private String phone;
    private UserType userType;
    private String department;
    private String township;
    private String institution;
    private boolean enable;

}
