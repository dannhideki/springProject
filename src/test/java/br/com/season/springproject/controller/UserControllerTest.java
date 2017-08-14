package br.com.season.springproject.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.season.springproject.entity.User;
import br.com.season.springproject.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	private static String ENDPOINT = "/user";
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController controller;
	
	@Mock
	private UserService userService;

	@Before
	public void setUp(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/pages/");
        viewResolver.setSuffix(".jsp");
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setViewResolvers(viewResolver)
				.build();
	}
	
	@Test
	public void shouldSaveUser() throws Exception{
		User user = new User();
		user.setLastName("Teste");
		user.setUsername("teste");
		
		mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
				.content(toJson(user))).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	
	private byte[] toJson(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(obj);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
