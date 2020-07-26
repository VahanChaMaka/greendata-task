package ru.grishagin.greendatatask.repository;

import org.springframework.data.repository.CrudRepository;
import ru.grishagin.greendatatask.model.Client;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
