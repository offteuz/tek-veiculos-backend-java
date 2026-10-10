package br.com.teksystem.tekveiculos.user.domain.port;

import br.com.teksystem.tekveiculos.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    User update(User user);

    void deleteById(Long id);
}
