package ru.grishagin.greendatatask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import ru.grishagin.greendatatask.model.descriptor.SortingFilteringDescriptor;
import ru.grishagin.greendatatask.service.DescriptorService;
import ru.grishagin.greendatatask.service.DescriptorServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static ru.grishagin.greendatatask.model.descriptor.SortDirection.ASC;
import static ru.grishagin.greendatatask.model.descriptor.SortDirection.DESC;

@RunWith(MockitoJUnitRunner.class)
public class DescriptorServiceTest {

    private DescriptorService descriptorService = new DescriptorServiceImpl();

    @Test
    public void givenEmptyParameters_whenBuild_shouldReturnEmptyFields(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setQueryString("");

        SortingFilteringDescriptor expected = new SortingFilteringDescriptor(new HashMap<>(), null, null);
        SortingFilteringDescriptor result = descriptorService.buildDescriptor(request);

        assertEquals(expected, result);
    }

    @Test
    public void givenFilterParameters_whenBuild_shouldReturnFilledFilter(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("type", "IP");
        request.addParameter("name", "name");

        Map<String, String> filters = new HashMap<>();
        filters.put("type", "IP");
        filters.put("name", "name");
        SortingFilteringDescriptor expected = new SortingFilteringDescriptor(filters, null, null);
        SortingFilteringDescriptor result = descriptorService.buildDescriptor(request);

        assertEquals(expected, result);
    }

    @Test
    public void givenSortingParameters_whenBuild_shouldReturnFilledSorting(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("sortBy", "name");
        request.addParameter("sort", "ASC");

        SortingFilteringDescriptor expected = new SortingFilteringDescriptor(Collections.emptyMap(), "name", ASC);
        SortingFilteringDescriptor result = descriptorService.buildDescriptor(request);

        assertEquals(expected, result);
    }

    @Test
    public void givenSortingDesc_whenBuild_shouldReturnFilledSortingDesc(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("sort", "DESC");

        SortingFilteringDescriptor expected = new SortingFilteringDescriptor(Collections.emptyMap(), null, DESC);
        SortingFilteringDescriptor result = descriptorService.buildDescriptor(request);

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenSortingIllegal_whenBuild_shouldThrowException(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("sort", "illegal");

        descriptorService.buildDescriptor(request);
    }

    @Test
    public void givenFilterAndSortingParameters_whenBuild_shouldReturnFilledFilter(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("type", "IP");
        request.addParameter("name", "name");
        request.addParameter("sortBy", "name");
        request.addParameter("sort", "ASC");

        Map<String, String> filters = new HashMap<>();
        filters.put("type", "IP");
        filters.put("name", "name");
        SortingFilteringDescriptor expected = new SortingFilteringDescriptor(filters, "name", ASC);
        SortingFilteringDescriptor result = descriptorService.buildDescriptor(request);

        assertEquals(expected, result);
    }
}
