package ru.grishagin.greendatatask.repository;

import org.springframework.data.repository.CrudRepository;
import ru.grishagin.greendatatask.model.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long> {
}
