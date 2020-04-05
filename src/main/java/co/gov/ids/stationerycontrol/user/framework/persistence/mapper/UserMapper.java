package co.gov.ids.stationerycontrol.user.framework.persistence.mapper;

import co.gov.ids.stationerycontrol.user.domain.User;
import co.gov.ids.stationerycontrol.user.framework.persistence.entities.UserEntity;

/**
 * Class to map User and UserEntity.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public final class UserMapper {

    private UserMapper() {
    }

    /**
     * Function to map UserEntity to User.
     *
     * @param entity UserEntity to be mapped.
     * @return User mapped.
     */
    public static User toDomain(UserEntity entity) {
        User domain = new User();
        domain.setIdentificationCard(entity.getIdentificationCard());
        domain.setName(entity.getName());
        domain.setEmail(entity.getEmail());
        domain.setPhone(entity.getPhone());
        domain.setUserType(entity.getUserType());
        return domain;
    }

    /**
     * Function to map User to UserEntity.
     *
     * @param domain User to be mapped.
     * @return UserEntity mapped.
     */
    public static UserEntity toEntity(User domain) {
        UserEntity entity = new UserEntity();
        entity.setIdentificationCard(domain.getIdentificationCard());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPhone(domain.getPhone());
        entity.setUserType(domain.getUserType());
        return entity;
    }

}
