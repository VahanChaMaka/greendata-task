package ru.grishagin.greendatatask.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.grishagin.greendatatask.model.Deposit;

@Service
public class DepositService extends AbstractSearchingFilteringService<Deposit> {

    @Autowired
    public DepositService(CrudRepository<Deposit, Long> repository) {
        super(repository);
    }

}
