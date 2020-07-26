package ru.grishagin.greendatatask.service.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.StringUtils;
import ru.grishagin.greendatatask.model.descriptor.SortDirection;
import ru.grishagin.greendatatask.model.descriptor.SortingFilteringDescriptor;
import ru.grishagin.greendatatask.service.ProcessingService;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
public abstract class AbstractSearchingFilteringService<T> implements ProcessingService<T> {

    private final CrudRepository<T, Long> repository;

    public AbstractSearchingFilteringService(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getByDescriptor(SortingFilteringDescriptor descriptor) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(entity -> descriptor.getFilters()
                        .entrySet()
                        .stream()
                        .allMatch(fieldToValue -> fieldToValue.getValue()
                                .equals(getFieldValue(entity, fieldToValue.getKey()))))
                .sorted((o1, o2) -> {
                    if(StringUtils.isEmpty(descriptor.getSortBy())){
                        return 0;
                    }

                    String o1Value = getFieldValue(o1, descriptor.getSortBy());
                    String o2Value = getFieldValue(o2, descriptor.getSortBy());

                    if(o1Value == null || o2Value == null){
                        return 0;
                    }

                    if(descriptor.getSortDirection() == SortDirection.DESC){
                        return o1Value.compareTo(o2Value);
                    } else {
                        return o2Value.compareTo(o1Value);
                    }
                })
         .collect(Collectors.toList());
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private String getFieldValue(T entity, String fieldName){
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, entity.getClass());
            Object fieldValue = pd.getReadMethod().invoke(entity);//get field value by getter
            if(fieldValue != null) {
                return fieldValue.toString();
            } else {
                return null;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("An error has occurred during reading entity's fields", e);
            return null;
        } catch (IntrospectionException e){
            throw new IllegalArgumentException("Illegal field name", e);
        }
    }
}
