package ru.grishagin.greendatatask.service;

import org.springframework.stereotype.Service;
import ru.grishagin.greendatatask.model.descriptor.SortDirection;
import ru.grishagin.greendatatask.model.descriptor.SortingFilteringDescriptor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DescriptorServiceImpl implements DescriptorService {

    private static final String SORT_BY_KEY = "sortBy";
    private static final String SORT_DIRECTION_KEY = "sort";

    @Override
    public SortingFilteringDescriptor buildDescriptor(HttpServletRequest request) {
        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
        SortingFilteringDescriptor.SortingFilteringDescriptorBuilder descriptorBuilder = SortingFilteringDescriptor.builder();

        //parse sorting parameters and remove it to exclude from filters
        if(parameterMap.get(SORT_BY_KEY) != null){
            descriptorBuilder.sortBy(parameterMap.get(SORT_BY_KEY)[0]);
            parameterMap.remove(SORT_BY_KEY);
        }
        if(parameterMap.get(SORT_DIRECTION_KEY) != null){
            descriptorBuilder.sortDirection(SortDirection.valueOf(parameterMap.get(SORT_DIRECTION_KEY)[0]));
            parameterMap.remove(SORT_DIRECTION_KEY);
        }

        Map<String, String> filters = parameterMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        filter -> filter.getKey(),
                        filter -> filter.getValue()[0]
                ));
        descriptorBuilder.filters(filters);

        return descriptorBuilder.build();

    }
}
