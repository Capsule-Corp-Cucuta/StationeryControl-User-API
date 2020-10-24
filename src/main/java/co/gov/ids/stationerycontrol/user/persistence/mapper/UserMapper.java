package co.gov.ids.stationerycontrol.user.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import co.gov.ids.stationerycontrol.user.domain.dto.User;
import co.gov.ids.stationerycontrol.user.persistence.entity.UserEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "userType", target = "userType"),
            @Mapping(source = "department", target = "department"),
            @Mapping(source = "township", target = "township"),
            @Mapping(source = "institution", target = "institution"),
    })
    User toUser(UserEntity entity);

    List<User> toUsers(List<UserEntity> entities);

    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(User user);

}
