package ru.grishagin.greendatatask.service;

import ru.grishagin.greendatatask.model.descriptor.SortingFilteringDescriptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface DescriptorService {

    SortingFilteringDescriptor buildDescriptor(HttpServletRequest request);

}
