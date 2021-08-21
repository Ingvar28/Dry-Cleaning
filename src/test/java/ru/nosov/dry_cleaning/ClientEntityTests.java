package ru.nosov.dry_cleaning;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.repositories.ClientRepository;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class ClientEntityTests {
    MockMvc mockMvc;


    @Resource
    private ClientRepository clientRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");


    @Before

    public void setUp() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation))
                        .build();
    }

    @Test
    public void givenClient_whenSave_thenGetOk() {
        ClientEntity client = new ClientEntity("John","Weak");
        clientRepository.save(client);

        ClientEntity client2 = clientRepository.getById(1L);
        assertEquals("John", client.getFirstName());
    }

}
