package co.gov.ids.stationerycontrol.user.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import co.gov.ids.stationerycontrol.user.domain.dto.User;
import co.gov.ids.stationerycontrol.user.persistence.mapper.UserMapper;
import co.gov.ids.stationerycontrol.user.persistence.entity.UserEntity;
import co.gov.ids.stationerycontrol.user.domain.repository.IUserRepository;

@Repository
public class UserRepository implements IUserRepository {

    private final UserMapper mapper;
    private final int SIZE_PAGE = 25;
    private final IUserJPARepository repository;

    public UserRepository(UserMapper mapper, IUserJPARepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public User create(User user, String password) {
        UserEntity entity = mapper.toUserEntity(user);
        entity.setPassword(password);
        return mapper.toUser(repository.save(entity));
    }

    @Override
    public User update(User user) {
        UserEntity entity = mapper.toUserEntity(user);
        entity.setPassword(getPassword(user.getId()));
        return mapper.toUser(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id).map(userEntity -> mapper.toUser(userEntity));
    }

    @Override
    public Optional<List<User>> findAll(int page) {
        return Optional.of(mapper.toUsers(repository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent()));
    }

    @Override
    public Optional<List<User>> findByName(String name, int page) {
        return repository.findByNameContainingIgnoreCase(name, PageRequest.of(page, SIZE_PAGE))
                .map(userEntities -> mapper.toUsers(userEntities.getContent()));
    }

    @Override
    public String getPassword(String id) {
        return repository.findById(id).map(userEntity -> userEntity.getPassword()).orElse(null);
    }

    @Override
    public void changePassword(User user, String newPass) {
        UserEntity entity = mapper.toUserEntity(user);
        entity.setPassword(newPass);
        repository.save(entity);
    }

    @Override
    public long countUsers() {
        return repository.count();
    }

}
