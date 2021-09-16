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
import ru.nosov.dry_cleaning.repositories.ItemRepository;
import ru.nosov.dry_cleaning.services.ItemService;

import javax.annotation.Resource;

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
public class ItemControllerTest {

    private static final String URL_PREFIX = "/item";


    MockMvc mockMvc;

    @Autowired
    private DataInitializer dataInitializer;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ItemService itemService;

    @Resource
    ItemRepository ItemRepository;

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

        assertTrue(ItemRepository.existsById(validDTO.getItemInDTO().getId()), "There is no Item to delete with id " + validDTO.getItemInDTO().getId());
        this.mockMvc.perform(get(uri, validDTO.getItemInDTO().getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(jsonPath("orderId").value(dataInitializer.getItemEntity().getOrder().getId()))
                .andExpect(status().isOk());
    }


    @Test
    public void getById() throws Exception {
        String uri = URL_PREFIX + "/{id}";

        this.mockMvc.perform(get(uri, dataInitializer.getItemEntity().getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderId").value(dataInitializer.getItemEntity().getOrder().getId()));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX))
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderId")
                        .value(Matchers.contains(dataInitializer.getItemEntity().getOrder().getId())));
    }


    @Test
    public void create() throws Exception {
        this.mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataInitializer.getItemEntity())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderId").value(dataInitializer.getItemEntity().getOrder().getId()));
    }

    @Test
    public void update() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.
                                writeValueAsString(validDTO.getItemInDTO())))
                .andExpect(status().isOk());
        assertEquals(dataInitializer.getItemEntity().getOrder().getId(), validDTO.getItemInDTO().getOrderId());
    }

    @Test
    public void deleteById() throws Exception {
        assertTrue(ItemRepository.findById(dataInitializer.getItemEntity().getId()).isPresent());

        String uri = URL_PREFIX + "/{id}";
        this.mockMvc.perform(delete(uri, dataInitializer.getItemEntity().getId()))
                .andExpect(status().isOk());

        assertFalse(ItemRepository.findById(dataInitializer.getItemEntity().getId()).isPresent());
    }


}
