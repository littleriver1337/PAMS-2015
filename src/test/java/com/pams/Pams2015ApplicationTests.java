package com.pams;

import com.pams.services.ItemRepository;
import com.pams.services.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Pams2015Application.class)
@WebAppConfiguration
public class Pams2015ApplicationTests {
	@Autowired
	UserRepository userRepo;

	@Autowired
	ItemRepository itemRepo;

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext wap;

	@Before
	public void before() {
		userRepo.deleteAll();
		itemRepo.deleteAll();
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void loginTest() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
						.param("username", "TestUser")
						.param("password", "password")
						.param("accessLevel" , "1")
						.param("email" , "pams@pams.com")
		);
		assertTrue(userRepo.count() == 1);
	}

	@Test
	public void importFileTest()throws Exception{
		MockMultipartFile testFile = new MockMultipartFile("file", "test_items.csv", "text/csv", "test csv".getBytes());

		mockMvc.perform(
				MockMvcRequestBuilders.fileUpload("/import-file")
						.file(testFile)
		);
		assertTrue(itemRepo.count() > 0);
	}
}
