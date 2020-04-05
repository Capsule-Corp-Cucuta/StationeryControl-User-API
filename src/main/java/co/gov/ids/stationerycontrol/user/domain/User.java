package co.gov.ids.stationerycontrol.user.domain;

import lombok.Data;

/**
 * Class to represent Users.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Data
public class User {

    private String identificationCard;
    private String name;
    private String email;
    private String phone;
    private UserType userType;

}
