package co.gov.ids.stationerycontrol.user.application.services;

import java.util.List;
import java.util.ArrayList;
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
 * @version 0.0.2
 * @since 2020
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImplementation implements IUserService {

    private final int SIZE_PAGE = 20;
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
        if (entity == null) {
            entity = UserMapper.toEntity(user);
            entity.setPassword("pass-" + user.getIdentificationCard());
        }
        return UserMapper.toDomain(repository.save(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User update(String identificationCard, User user) {
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity != null) {
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
            entity.setPhone(user.getPhone());
            entity.setUserType(user.getUserType());
            return UserMapper.toDomain(repository.save(entity));
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public boolean delete(String identificationCard) {
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity != null) {
            repository.delete(entity);
            return true;
        }
        return false;
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
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User findByIdentificationCard(String identificationCard) {
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity != null) {
            return UserMapper.toDomain(entity);
        }
        return null;
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
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public boolean changePassword(String identificationCard, String oldPass, String newPass) {
        UserEntity entity = repository.findByIdentificationCard(identificationCard);
        if (entity != null && entity.getPassword().equals(oldPass)) {
            entity.setPassword(newPass);
            repository.save(entity);
            return true;
        }
        return false;
    }

}
