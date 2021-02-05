package co.gov.ids.stationerycontrol.user.domain.repository;

import java.util.List;
import java.util.Optional;
import co.gov.ids.stationerycontrol.user.domain.dto.User;

public interface IUserRepository {

    User create(User user, String password);

    User update(User user);

    Optional<User> findByUsername(String username);

    Optional<List<User>> findAll(int page);

    Optional<List<User>> findByIdentificationCard(String identificationCard, int page);

    Optional<List<User>> findByName(String name, int page);

    String getPassword(String username);

    void changePassword(User user, String newPass);

    long countUsers();

}
