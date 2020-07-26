package ru.grishagin.greendatatask.service;

import ru.grishagin.greendatatask.model.Client;
import ru.grishagin.greendatatask.model.descriptor.SortingFilteringDescriptor;

import java.util.List;
import java.util.Optional;

public interface ProcessingService<T> {

    Optional<T> getById(Long id);

    List<T> getByDescriptor(SortingFilteringDescriptor descriptor);

    T create(T entity);

    T update(T entity);

    void delete(Long id);

}
