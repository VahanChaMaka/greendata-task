package ru.grishagin.greendatatask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.repository.CrudRepository;
import ru.grishagin.greendatatask.model.Client;
import ru.grishagin.greendatatask.model.OrganizationType;
import ru.grishagin.greendatatask.model.descriptor.SortDirection;
import ru.grishagin.greendatatask.model.descriptor.SortingFilteringDescriptor;
import ru.grishagin.greendatatask.service.entity.ClientService;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private CrudRepository<Client, Long> mockRepo;

    private Client client1 = new Client(0L, "IP Pupkin Vasiliy", "IP Pupkin", "some address", OrganizationType.IP);
    private Client client2 = new Client(1L, "IP Petrov Ivan", "IP Petrov", "another address", OrganizationType.IP);
    private Client client3 = new Client(2L, "organization", "short name", "add", OrganizationType.OOO);
    private Client client4 = new Client(3L, "OOO Vector", "Vector", "address", OrganizationType.OOO);
    private Client client5 = new Client(4L, "Another organization", "short name", "address", OrganizationType.OOO);
    private List<Client> clients;

    @Before
    public void init(){
        clients = new LinkedList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);
        Mockito.when(mockRepo.findAll()).thenReturn(clients);
    }

    @Test
    public void givenClients_whenGetWithoutFilters_shouldReturnAll(){
        List<Client> allClients = clientService.getByDescriptor(new SortingFilteringDescriptor(new HashMap<>(), null, null));
        assertEquals(mockRepo.findAll(), allClients);
    }

    @Test
    public void givenClients_whenGetWithFilterByName_shouldReturnOne(){
        List<Client> result = clientService.getByDescriptor(new SortingFilteringDescriptor(Collections.singletonMap("name", "organization"), null, null));
        assertEquals(Collections.singletonList(client3), result);
    }

    @Test
    public void givenClients_whenGetWithFilterByOrganizationType_shouldReturnClient1AndClient2(){
        List<Client> result = clientService.getByDescriptor(new SortingFilteringDescriptor(Collections.singletonMap("type", "IP"), null, null));
        assertEquals(Arrays.asList(client1, client2), result);
    }

    @Test
    public void givenClients_whenGetWithFilterByShortnameAndAddress_shouldReturnClient5(){
        Map<String, String> filters = new HashMap<>();
        filters.put("shortName", "short name");
        filters.put("address", "address");
        List<Client> result = clientService.getByDescriptor(new SortingFilteringDescriptor(filters, null, null));
        assertEquals(Collections.singletonList(client5), result);
    }

    @Test
    public void givenClients_whenGetWithFilterByNinexistentValue_shouldReturnEmptyList(){
        List<Client> result = clientService.getByDescriptor(new SortingFilteringDescriptor(Collections.singletonMap("name", "there is no such name"), null, null));
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void givenClients_whenGetWithOrderByNameDesc_shouldReturnOrderedDesc(){
        clients.sort(Comparator.comparing(Client::getName));

        List<Client> result = clientService.getByDescriptor(new SortingFilteringDescriptor(Collections.emptyMap(), "name", SortDirection.DESC));
        assertEquals(clients, result);
    }

    @Test
    public void givenClients_whenGetWithOrderByNameAsc_shouldReturnOrderedAsc(){
        clients.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));

        List<Client> result = clientService.getByDescriptor(new SortingFilteringDescriptor(Collections.emptyMap(), "name", SortDirection.ASC));
        assertEquals(clients, result);
    }

}
