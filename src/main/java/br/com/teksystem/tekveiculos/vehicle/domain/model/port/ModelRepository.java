package br.com.teksystem.tekveiculos.vehicle.domain.model.port;

import br.com.teksystem.tekveiculos.vehicle.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    Model save(Model model);

    List<Model> findAll();

    Optional<Model> findById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String name);

    boolean existsByNameAndBrandId(String name, Long brandId);

    Model update(Model model);

    void deleteById(Long id);
}
