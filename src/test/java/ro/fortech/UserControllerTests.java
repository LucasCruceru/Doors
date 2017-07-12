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
import ro.fortech.entities.User;
import ro.fortech.repositories.UserRepository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserControllerTests {


	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	String username = "lucas";


	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<User> users = new ArrayList<>();

	private User user;


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

		this.userRepository.deleteAllInBatch();


		this.users.add(userRepository.save(new User(username, "password")));
		this.users.add(userRepository.save(new User("flavius", "password")));


	}


	@Test
	public void readUsersTest() throws Exception {

		mockMvc.perform(get("/users/getAll"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("[0]id",is(this.users.get(0).getId().intValue())))
				.andExpect(jsonPath("[0].username", is("password")))
				.andExpect(jsonPath("[1].id",is(this.users.get(1).getId().intValue())))
				.andExpect(jsonPath("[1].username", is("password")));


	}

	@Test
	public void readSingleUserTest() throws Exception {

		System.out.println(json(this.users.get(0)));
		mockMvc.perform(get("/users/"
				+this.users.get(0).getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("id",is(this.users.get(0).getId().intValue())))
				.andExpect(jsonPath("username", is("password")));
	}



	@Test
	public void deleteSingleUserTest() throws Exception {
        System.out.println(json(this.users));
        mockMvc.perform(delete("/users/"
				+ this.users.get(0).getId())
                .contentType(contentType))
                .andExpect(status().isOk());
	}

	@Test
	public void createUserTest() throws Exception {
		String userJson = json(new User(
				"anastasia", "password"));

		this.mockMvc.perform(post("/users")
				.contentType(contentType)
				.content(userJson))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateTest() throws Exception {

		User temp = new User("Bogdan", "password");

		this.users.get(0).updateUser(temp);

		String userJson = json(this.users.get(0));

		this.mockMvc.perform(put("/users/" +
				this.users.get(0).getId())
				.contentType(contentType)
				.content(userJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("username", is("password")));
	}

	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}