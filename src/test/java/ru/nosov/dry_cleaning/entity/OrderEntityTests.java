package ru.nosov.dry_cleaning.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.entities.OrderEntity;
import ru.nosov.dry_cleaning.repositories.ClientRepository;
import ru.nosov.dry_cleaning.repositories.OrderRepository;
import ru.nosov.dry_cleaning.services.OrderService;
import ru.nosov.dry_cleaning.webservices.OrderWebService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
////@DataJpaTest
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@EntityScan({"ru.nosov.dry_cleaning.entities"})
@Transactional

public class OrderEntityTests {
    MockMvc mockMvc;


    @Resource
    private ClientRepository clientRepository;
    private OrderRepository orderRepository;
    private OrderWebService orderWebService;
    private OrderService orderService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    private Long clientSavedId;


    @Before
    public void setUp() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation))
                        .build();
        ClientEntity testClient = new ClientEntity();
        testClient.setFirstName("John");
        testClient.setLastName("Weak");
        testClient.setPhone("123456789");
        testClient.setEmail("JohnWeak@gmail.com");
        testClient.setClientLevel("Bronze");
        testClient.setDescription("Angry man");
        ClientEntity clientSaved = clientRepository.save(testClient);
        clientSavedId = clientSaved.getId();
        //TODO Finish test
//        OrderEntity testOrder = orderService.create(null,
//                savedId,)

    }


    @Test
    public void testCreateOrder() throws Exception {
        String uri = "/order";
        OrderInDTO dto = new OrderInDTO();
        dto.setClientId(clientSavedId);
        String content = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("clientId").value(clientSavedId));
    }

    @Test
    public void testGetById() throws Exception {
        String uri = "/client/{id}";
        mockMvc.perform(get(uri, 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        String uri = "/client/all";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        String uri = "/client";
        ClientInDTO dto = new ClientInDTO();
        dto.setId(1L);
        dto.setFirstName("Bruce");
        dto.setLastName("Wain");
        dto.setPhone("123456789");
        dto.setEmail("BruceWain@batman.com");
        dto.setClientLevel("Gold");
        dto.setDescription("Dark Knight");
        String content = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("lastName").value("Wain"));

    }

    @Test
    public void testDelete() throws Exception {
        String uri = "/client/{id}";
        Long idToDelete = 1L;
        Assert.assertTrue("There was not such entity to remove!", clientRepository.existsById(idToDelete));
        mockMvc.perform(delete(uri, idToDelete).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
        Assert.assertFalse("The entity was not removed!", clientRepository.existsById(idToDelete));
    }

    @Test
    public void whenMethodArgumentMismatch_thenBadRequest() throws Exception {
        String uri = "/client/{id}";
        mockMvc.perform(get(uri, "StingInsteadLong").contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.contains("id should be of type java.lang.Long")));
    }

}
