package ru.grishagin.greendatatask.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.grishagin.greendatatask.model.Bank;

@Service
public class BankService extends AbstractSearchingFilteringService<Bank> {

    @Autowired
    public BankService(CrudRepository<Bank, Long> repository) {
        super(repository);
    }
}
