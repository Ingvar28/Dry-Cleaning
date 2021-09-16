package ru.nosov.dry_cleaning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.init.DataInitializer;
import ru.nosov.dry_cleaning.init.ValidDTO;
import ru.nosov.dry_cleaning.repositories.ClientRepository;
import ru.nosov.dry_cleaning.services.ClientService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@EntityScan({"ru.nosov.dry_cleaning.entities"})
@Transactional
@AutoConfigureMockMvc
@Slf4j
public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Resource
    private ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private DataInitializer dataInitializer;

    @Autowired
    ValidDTO validDTO;

    @Before
    public void setUp() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation))
                        .build();
        dataInitializer.initData();

    }


    @Test
    public void testDeleteClientById() throws Exception {
        String uri = "/client/{id}";
        Long id = dataInitializer.getClientEntity().getId();
        this.mockMvc.perform(get(uri, id).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(jsonPath("FirstName").value(dataInitializer.getClientEntity().getFirstName()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllClients() throws Exception {
        String uri = "/client/all";
        this.mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void createClientWithTestName() throws Exception {
        String uri = "/client/";
        ClientInDTO dto = validDTO.getClientInDTO();
        dto.setFirstName("Test");
        String content = objectMapper.writeValueAsString(dto);
        this.mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(jsonPath("FirstName").value(dto.getFirstName()))
                .andExpect(status().isOk());
    }

    @Test
    public void createClient_NameExists() throws Exception {
        String uri = "/client/";
        ClientInDTO dto = validDTO.getClientInDTO();
        String content = objectMapper.writeValueAsString(dto);

        Throwable exception = assertThrows(DryCleaningApiException.class, () ->
        {
            try {
                this.mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                        .andDo(document(uri.replace("/", "\\")));
            } catch (NestedServletException e) {
                throw e.getCause();
            }
        });
        assertEquals(exception.getMessage(), "Client with that name already exists, id: "
                + dataInitializer.getClientEntity().getId());
    }

    @Test
    public void updateClientWithTestName() throws Exception {
        String uri = "/client/";
        ClientInDTO dto = clientService.toInDTO(dataInitializer.getClientEntity());
        dto.setFirstName("Test");
        String content = objectMapper.writeValueAsString(dto);
        this.mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(jsonPath("FirstName").value(dto.getFirstName()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        String uri = "/client/{id}";
        Long id = dataInitializer.getClientEntity().getId();

        assertTrue(clientRepository.existsById(id), "There is no Client to delete with id " + id);
        this.mockMvc.perform(delete(uri, id).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
        assertFalse(clientRepository.existsById(id), "Client was not deleted");
    }
}
