package io.jose827corrza.github.pepepizza.persistence.repository;

import io.jose827corrza.github.pepepizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
