package ru.grishagin.greendatatask.repository;

import org.springframework.data.repository.CrudRepository;
import ru.grishagin.greendatatask.model.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {
}
