package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Pecas;

public interface PecasRepository extends CrudRepository<Pecas, Long> {
    
}
