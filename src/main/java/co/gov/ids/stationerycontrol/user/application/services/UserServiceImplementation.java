package co.gov.ids.stationerycontrol.user.application.services;

import java.util.List;
import java.util.ArrayList;
import co.gov.ids.stationerycontrol.user.application.exceptions.BadRequestException;
import co.gov.ids.stationerycontrol.user.application.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.gov.ids.stationerycontrol.user.domain.User;
import org.springframework.transaction.annotation.Transactional;
import co.gov.ids.stationerycontrol.user.framework.persistence.mapper.UserMapper;
import co.gov.ids.stationerycontrol.user.framework.persistence.entities.UserEntity;
import co.gov.ids.stationerycontrol.user.framework.persistence.repositories.IUserRepository;

/**
 * Class that implements IUserService.
 *
 * @author Sergio Rodriguez
 * @version 0.0.4
 * @since 2020
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImplementation implements IUserService {

    private final int SIZE_PAGE = 25;
    private final IUserRepository repository;

    public UserServiceImplementation(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User create(User user) {
        UserEntity entity = repository.findByIdentificationCard(user.getIdentificationCard());
        if (entity != null) {
            throw new BadRequestException("User exist already");
        }
        entity = UserMapper.toEntity(user);
        entity.setPassword("pass-" + user.getIdentificationCard());
        return UserMapper.toDomain(repository.save(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User update(String identificationCard, User user) {
        if (!identificationCard.equals(user.getIdentificationCard())) {
            throw new BadRequestException("The Identification Card does not agree with the User");
        }
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity == null) {
            throw new NotFoundException("User not found");
        }
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
        entity.setUserType(user.getUserType());
        return UserMapper.toDomain(repository.save(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(String identificationCard) {
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity == null) {
            throw new NotFoundException("User not found");
        }
        repository.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<User> findAll(int page) {
        List<User> users = new ArrayList<>();
        for (UserEntity entity : repository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent()) {
            users.add(UserMapper.toDomain(entity));
        }
        if (users.isEmpty()) {
            throw new NotFoundException("Users not found");
        }
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User findByIdentificationCard(String identificationCard) {
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity == null) {
            throw new NotFoundException("User not found");
        }
        return UserMapper.toDomain(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<User> findByName(String name, int page) {
        List<User> users = new ArrayList<>();
        for (UserEntity entity : repository.findByNameContainingIgnoreCase(name, PageRequest.of(page, SIZE_PAGE)).getContent()) {
            users.add(UserMapper.toDomain(entity));
        }
        if (users.isEmpty()) {
            throw new NotFoundException("Users not found");
        }
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void changePassword(String identificationCard, String oldPass, String newPass) {
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity == null) {
            throw new NotFoundException("User not found");
        }
        if (!entity.getPassword().equals(oldPass)) {
            throw new BadRequestException("Current passwords don't match");
        }
        entity.setPassword(newPass);
        repository.save(entity);
    }

}
