package co.gov.ids.stationerycontrol.user.persistence.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import co.gov.ids.stationerycontrol.user.persistence.entity.UserEntity;

public interface IUserJPARepository extends PagingAndSortingRepository<UserEntity, String> {

    Optional<Page<UserEntity>> findByNameContainingIgnoreCase(String name, Pageable page);
}
