package ro.fortech;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ro.fortech.entities.Door;
import ro.fortech.entities.User;
import ro.fortech.repositories.DoorRepository;
import ro.fortech.repositories.UserRepository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ActionControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    String username = "lucas";


    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<Door> doors = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private User user;

    @Autowired
    private DoorRepository doorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.doorRepository.deleteAllInBatch();
        this.userRepository.deleteAllInBatch();


        this.users.add(userRepository.save(new User(username, "password")));


        this.doors.add(doorRepository.save(new Door("GarageDoor", true)));
        this.doors.add(doorRepository.save(new Door("FrontDoor", true)));



    }



    @Test
    public void openTest() throws Exception {

        mockMvc.perform(get("/" + username + "/open/"
                + this.doors.get(0).getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].closed", is(false)));
    }

    @Test
    public void closeTest() throws Exception {
        mockMvc.perform(get("/" + username + "/close/"
                + this.doors.get(0).getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].closed", is(true)));
    }
            @Test
	public void seeOptions() throws Exception {

		mockMvc.perform(get("/" + username))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("[0].id",is(this.doors.get(0).getId().intValue())))
				.andExpect(jsonPath("[0].name", is("GarageDoor")))
				.andExpect(jsonPath("[0].closed", is(true)))
				.andExpect(jsonPath("[1].id",is(this.doors.get(1).getId().intValue())))
				.andExpect(jsonPath("[1].name", is("FrontDoor")))
				.andExpect(jsonPath("[1].closed", is(true)));

	}



    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
