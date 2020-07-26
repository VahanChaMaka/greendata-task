package ru.grishagin.greendatatask.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.grishagin.greendatatask.model.Client;

@Service
public class ClientService extends AbstractSearchingFilteringService<Client> {

    @Autowired
    public ClientService(CrudRepository<Client, Long> repository) {
        super(repository);
    }
}
