package io.adnanedu.ppmtool.repositories;

import io.adnanedu.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
