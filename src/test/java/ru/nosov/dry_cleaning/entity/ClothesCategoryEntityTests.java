package ru.nosov.dry_cleaning.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.nosov.dry_cleaning.dto.in.OrderInDTO;
import ru.nosov.dry_cleaning.init.DataInitializer;
import ru.nosov.dry_cleaning.repositories.ClothesCategoryRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@EntityScan({"ru.nosov.dry_cleaning.entities"})
@Transactional

public class ClothesCategoryEntityTests {
    MockMvc mockMvc;
    


    @Resource
    private ClothesCategoryRepository clothesCategoryRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    protected DataInitializer dataInitializer;


    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");


    @Before
    public void setUp() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation))
                        .build();

        dataInitializer.initializeData();

    }


    @Test
    public void testCreateOrder() throws Exception {
        String uri = "/clothescategory";
        OrderInDTO dto = objectMapper.convertValue(clothesCategoryRepository.findById(dataInitializer.orderEntity.getId()), OrderInDTO.class);
        String content = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(jsonPath(dataInitializer.orderEntity.getId().toString()).value(dto.getId().toString()));
    }

    @Test
    public void testGetById() throws Exception {
        String uri = "/clothescategory/{id}";
        mockMvc.perform(get(uri, 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        String uri = "/clothescategory/all";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        String uri = "/clothescategory";
        OrderInDTO dto = objectMapper.convertValue(clothesCategoryRepository.findById(dataInitializer.orderEntity.getId()), OrderInDTO.class);
        dto.setOrderEndTime(LocalDateTime.now().plusDays(3));
        String content = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(jsonPath(dataInitializer.orderEntity.getOrderEndTime().toString()).value(LocalDateTime.now().plusDays(3)));

    }

    @Test
    public void testDelete() throws Exception {
        String uri = "/clothescategory/{id}";
        OrderInDTO dto = objectMapper.convertValue(clothesCategoryRepository.findById(dataInitializer.orderEntity.getId()), OrderInDTO.class);
        Long idToDelete = dto.getId();
        Assert.assertTrue("There was not such entity to remove!", clothesCategoryRepository.existsById(idToDelete));
        mockMvc.perform(delete(uri, idToDelete).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
        Assert.assertFalse("The entity was not removed!", clothesCategoryRepository.existsById(idToDelete));
    }

    @Test
    public void whenMethodArgumentMismatch_thenBadRequest() throws Exception {
        String uri = "/clothescategory/{id}";
        mockMvc.perform(get(uri, "StingInsteadLong").contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.contains("id should be of type java.lang.Long")));
    }

}
