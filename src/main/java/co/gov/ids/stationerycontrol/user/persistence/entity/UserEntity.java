package co.gov.ids.stationerycontrol.user.persistence.entity;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.gov.ids.stationerycontrol.user.domain.dto.UserType;

@Data
@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    private String username;

    @Column(name = "identification_card", updatable = false, nullable = false)
    private String identificationCard;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String department;

    private String township;

    private String institution;

    @Column(nullable = false)
    private boolean enable;

}
