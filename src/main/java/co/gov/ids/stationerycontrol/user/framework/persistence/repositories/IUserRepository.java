package co.gov.ids.stationerycontrol.user.framework.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import co.gov.ids.stationerycontrol.user.framework.persistence.entities.UserEntity;

/**
 * Interface that define the available operations to DB.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Function to find a user by identification card number.
     *
     * @param identificationCard number of the document that identified the user.
     * @return User identified by param.
     */
    public UserEntity findByIdentificationCard(String identificationCard);

    /**
     * Function to find Users by name.
     *
     * @param name name of User to find.
     * @param page page for search.
     * @return List of Users named like param.
     */
    public Page<UserEntity> findByNameContainingIgnoreCase(String name, Pageable page);

}
