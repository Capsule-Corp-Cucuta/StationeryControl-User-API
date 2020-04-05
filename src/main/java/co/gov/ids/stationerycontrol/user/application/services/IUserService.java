package co.gov.ids.stationerycontrol.user.application.services;

import java.util.List;
import co.gov.ids.stationerycontrol.user.domain.User;

/**
 * Interface that represents the use cases of Users.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface IUserService {

    /**
     * Function to create a new User.
     *
     * @param user User to will be created.
     * @return User created.
     */
    public User create(User user);

    /**
     * Function to update an User.
     *
     * @param identificationCard number of the document that identified the user.
     * @param user               User to will be updated.
     * @return User updated.
     */
    public User update(String identificationCard, User user);

    /**
     * Function to delete an User by the identification card.
     *
     * @param identificationCard number of the document that identified the user.
     * @return true if User was deleted or false if do not.
     */
    public boolean delete(String identificationCard);

    /**
     * Function to list all Users on pages of 20 items.
     *
     * @param page number of page to list.
     * @return List of 20 Users.
     */
    public List<User> findAll(int page);

    /**
     * Function to find an User by identification card.
     *
     * @param identificationCard number of the document that identified the user.
     * @return User identified by param.
     */
    public User findByIdentificationCard(String identificationCard);

    /**
     * Function to list Users by name on pages of 20 items.
     *
     * @param name name of Users.
     * @param page number of page to list.
     * @return List of Users by name.
     */
    public List<User> findByName(String name, int page);

    /**
     * Function to change the password of an User.
     *
     * @param identificationCard number of the document that identified the user.
     * @param oldPass            String that represents the actual password of the User.
     * @param newPass            String that represents the new password of the User.
     * @return true if the password changes or false if do not.
     */
    public boolean changePassword(String identificationCard, String oldPass, String newPass);

}
