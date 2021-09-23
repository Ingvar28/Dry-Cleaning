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
import ru.nosov.dry_cleaning.repositories.ClothesCategoryRepository;
import ru.nosov.dry_cleaning.services.ClothesCategoryService;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Slf4j
@RunWith(SpringRunner.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class ClothesCategoryControllerTest {

    private static final String URL_PREFIX = "/clothescategory";


    MockMvc mockMvc;

    @Autowired
    private DataInitializer dataInitializer;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ClothesCategoryService clothesCategoryService;

    @Resource
    ClothesCategoryRepository clothesCategoryRepository;

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

        assertTrue(clothesCategoryRepository.existsById(validDTO.getClothesCategoryInDTO().getId()),
                "There is no Clothes Category to delete with id " + validDTO.getClothesCategoryInDTO().getId());
        this.mockMvc.perform(get(uri, validDTO.getClothesCategoryInDTO().getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(jsonPath("category").value(dataInitializer.getClothesCategoryEntity().getCategory()))
                .andExpect(status().isOk());
    }


    @Test
    public void getById() throws Exception {
        String uri = URL_PREFIX + "/{id}";

        this.mockMvc.perform(get(uri, dataInitializer.getClothesCategoryEntity().getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("category", is(dataInitializer.getClothesCategoryEntity().getCategory())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(URL_PREFIX.replace("/", "\\")))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    public void create() throws Exception {
        this.mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataInitializer.getClothesCategoryEntity())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("category", is(validDTO.getClothesCategoryInDTO().getCategory())));
    }

    @Test
    public void update() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.
                                writeValueAsString(dataInitializer.getClothesCategoryEntity())))
                .andExpect(status().isOk());
        assertEquals(dataInitializer.getClothesCategoryEntity().getCategory(), validDTO.getClothesCategoryInDTO().getCategory());
    }

    @Test
    public void deleteById() throws Exception {
        assertTrue(clothesCategoryRepository.findById(dataInitializer.getClothesCategoryEntity().getId()).isPresent());

        String uri = URL_PREFIX + "/{id}";
        this.mockMvc.perform(delete(uri, dataInitializer.getClothesCategoryEntity().getId()))
                .andExpect(status().isOk());

        assertFalse(clothesCategoryRepository.findById(dataInitializer.getClothesCategoryEntity().getId()).isPresent());
    }


}
