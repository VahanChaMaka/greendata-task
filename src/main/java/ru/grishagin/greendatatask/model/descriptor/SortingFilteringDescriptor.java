package ru.grishagin.greendatatask.model.descriptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class SortingFilteringDescriptor {

    private Map<String, String> filters;

    private String sortBy;

    private SortDirection sortDirection;
}
