package ru.nosov.dry_cleaning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.nosov.dry_cleaning.init.DataInitializer;
import ru.nosov.dry_cleaning.init.ValidDTO;
import ru.nosov.dry_cleaning.repositories.ClientRepository;
import ru.nosov.dry_cleaning.services.ClientService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Slf4j
@RunWith(SpringRunner.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class ClientControllerTest {

    private static final String URL_PREFIX = "/client";

    MockMvc mockMvc;

    @Autowired
    private DataInitializer dataInitializer;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ClientService clientService;

    @Resource
    ClientRepository clientRepository;

    @Autowired
    ValidDTO validDTO;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        dataInitializer.initData();

    }



    @Test
    public void testDeleteById() throws Exception {
        String uri = URL_PREFIX + "/{id}";

        assertTrue(clientRepository.existsById(validDTO.getClientInDTO().getId()), "There is no Client to delete with id " + validDTO.getClientInDTO().getId());
        this.mockMvc.perform(get(uri, validDTO.getClientInDTO().getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(jsonPath("firstname").value(dataInitializer.getClientEntity().getFirstName()))
                .andExpect(status().isOk());
    }


    @Test
    public void getById() throws Exception {
        String urlTemplate = URL_PREFIX + "/{id}";

        this.mockMvc.perform(get(urlTemplate, dataInitializer.getClientEntity().getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstname", is(dataInitializer.getClientEntity().getFirstName())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstname")
                        .value(Matchers.contains(dataInitializer.getClientEntity().getFirstName())));
    }


    @Test
    public void create() throws Exception {
        this.mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataInitializer.getClientEntity())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstname", is(validDTO.getClientInDTO().getFirstName())));
    }

    @Test
    public void update() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.
                                writeValueAsString(validDTO.getClientInDTO())))
                .andExpect(status().isOk());
        assertEquals(dataInitializer.getClientEntity().getFirstName(), validDTO.getClientInDTO().getFirstName());
    }

    @Test
    public void deleteById() throws Exception {
        assertTrue(clientRepository.findById(dataInitializer.getClientEntity().getId()).isPresent());

        String urlTemplate = URL_PREFIX + "/{id}";
        this.mockMvc.perform(delete(urlTemplate, dataInitializer.getClientEntity().getId()))
                .andExpect(status().isOk());

        assertFalse(clientRepository.findById(dataInitializer.getClientEntity().getId()).isPresent());
    }


}
