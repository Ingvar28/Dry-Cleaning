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
import ru.nosov.dry_cleaning.repositories.PaymentRepository;
import ru.nosov.dry_cleaning.services.PaymentService;

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
public class PaymentControllerTest {

    private static final String URL_PREFIX = "/payment";


    MockMvc mockMvc;

    @Autowired
    private DataInitializer dataInitializer;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PaymentService paymentService;

    @Resource
    PaymentRepository paymentRepository;

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

        assertTrue(paymentRepository.existsById(validDTO.getPaymentInDTO().getId()), "There is no Payment to delete with id " + validDTO.getPaymentInDTO().getId());
        this.mockMvc.perform(get(uri, validDTO.getPaymentInDTO().getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(jsonPath("paymentmethod").value(dataInitializer.getPaymentEntity().getPaymentMethod()))
                .andExpect(status().isOk());
    }


    @Test
    public void getById() throws Exception {
        String uri = URL_PREFIX + "/{id}";

        this.mockMvc.perform(get(uri, dataInitializer.getPaymentEntity().getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("paymentmethod").value(dataInitializer.getPaymentEntity().getPaymentMethod()));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX))
                .andExpect(status().isOk())
                .andExpect(jsonPath("paymentmethod")
                        .value(Matchers.contains(dataInitializer.getPaymentEntity().getPaymentMethod())));
    }


    @Test
    public void create() throws Exception {
        this.mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataInitializer.getPaymentEntity())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("paymentmethod", is(validDTO.getPaymentInDTO().getPaymentMethod())));
    }

    @Test
    public void update() throws Exception {
        this.mockMvc.perform(put(URL_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.
                                writeValueAsString(validDTO.getPaymentInDTO())))
                .andExpect(status().isOk());
        assertEquals(dataInitializer.getPaymentEntity().getPaymentMethod(), validDTO.getPaymentInDTO().getPaymentMethod());
    }

    @Test
    public void deleteById() throws Exception {
        assertTrue(paymentRepository.findById(dataInitializer.getPaymentEntity().getId()).isPresent());

        String uri = URL_PREFIX + "/{id}";
        this.mockMvc.perform(delete(uri, dataInitializer.getPaymentEntity().getId()))
                .andExpect(status().isOk());

        assertFalse(paymentRepository.findById(dataInitializer.getPaymentEntity().getId()).isPresent());
    }


}
