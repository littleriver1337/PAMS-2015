package com.pams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pams.entities.Bag;
import com.pams.entities.Club;
import com.pams.entities.Hat;
import com.pams.entities.User;
import com.pams.services.BagRepository;
import com.pams.services.HatRepository;
import com.pams.services.ItemRepository;
import com.pams.services.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Pams2015Application.class)
@WebAppConfiguration
public class Pams2015ApplicationTests {
	@Autowired
	UserRepository userRepo;

	@Autowired
	ItemRepository clubRepo;

	@Autowired
	HatRepository hatRepo;

	@Autowired
	BagRepository bagRepo;


	MockMvc mockMvc;

	@Autowired
	WebApplicationContext wap;

	@Before
	public void before() {
		userRepo.deleteAll();
		clubRepo.deleteAll();
		hatRepo.deleteAll();
		bagRepo.deleteAll();
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void loginTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.username = "TestUser";
		user.password = "TestPassword";
		user.accessLevel = User.AccessLevel.ADMIN;
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
						.content(json)
						.header("Content-Type", "application/json")

		);
		assertTrue(userRepo.count() == 1);
	}

	@Test
	public void addUserTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.username = "TestUser";
		user.password = "TestPassword";
		user.accessLevel = User.AccessLevel.ADMIN;
		user.companyName = "Wells Fargo";
		user.address = "405 Jefferson Street";
		user.city = "Hamlet";
		user.state = "NC";
		user.zip = "28345";
		user.email = "littleriver1337@gmail.com";
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-user")
						.content(json)
						.header("Content-Type", "application/json")
		);
		assertTrue(userRepo.count() == 1);
	}

	@Test
	public void editTestUser()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.username = "TestUser";
		user.password = "TestPassword";
		user.accessLevel = User.AccessLevel.ADMIN;
		user.companyName = "Wells Fargo";
		user.address = "405 Jefferson Street";
		user.city = "Hamlet";
		user.state = "NC";
		user.zip = "28345";
		user.email = "littleriver1337@gmail.com";
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-user")
						.content(json)
						.header("Content-Type", "application/json")
		);
		ObjectMapper mapper2 = new ObjectMapper();
		User user2 = new User();
		user2.username = "NewUsername";
		user2.password = "NewPassword";
		user.accessLevel = User.AccessLevel.COMPANY_USER;
		user.companyName = "PAMS";
		user.address = "1112 Deerberry Road";
		user.city = "Hannahan";
		user.state = "SC";
		user.zip = "29401";
		user.email = "littleriver1337@gmail.com";
		String json2 = mapper2.writeValueAsString(user2);
		mockMvc.perform(
				MockMvcRequestBuilders.post("edit-user")
						.content(json2)
						.header("Content-Type", "application/json")
		);
		assertTrue(userRepo.count() == 1);
	}
	@Test
	public void deleteUserTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.username = "ThisUser";
		user.password = "ThisPassword";
		user.accessLevel = User.AccessLevel.ADMIN;
		user.companyName = "Wells Fargo";
		user.address = "405 Jefferson Street";
		user.city = "Hamlet";
		user.state = "NC";
		user.zip = "28345";
		user.email = "littleriver1337@gmail.com";

		String json = mapper.writeValueAsString(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-user")
						.content(json)
						.header("Content-Type", "application/json")
		);
		User user2 = userRepo.findOneByUsername(user.username);
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-user/" + user2.id)
						.sessionAttr("username", "TestUser")

		);
		assertTrue(userRepo.count() == 0);
	}
	@Test
	public void addClubTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Club club = new Club();
		club.serialNumber = 123456;
		club.maker = "TestMaker";
		club.clubType = "TestType";
		club.year = 1985;
		club.lieAngle = "Green";
		club.time = LocalDateTime.now().toString();

		String json = mapper.writeValueAsString(club);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-club")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);
		assertTrue(clubRepo.count() == 1);
	}
	@Test
	public void editTestClub()
		throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Club club = new Club();
		club.serialNumber = 111234;
		club.maker = "TestMaker";
		club.clubType = "TestType";
		club.year = 1985;
		club.lieAngle = "Green";
		club.time = LocalDateTime.now().toString();

		String json = mapper.writeValueAsString(club);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-club")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);

		Club club2 = clubRepo.findOneBySerialNumber(club.serialNumber);
		ObjectMapper mapper2 = new ObjectMapper();
		club2.serialNumber = 1123131;
		club2.maker = "TestEdit";
		club2.clubType = "TestEdited";
		club2.year = 1998;
		club2.lieAngle = "Red";
		club2.time = LocalDateTime.now().toString();

		String json2 = mapper2.writeValueAsString(club2);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/edit-club") //when this is set to put it does not work for some reason? Ask Zac.
						.content(json2)
						.header("Content-Type", "application/json") //what does this do specifically?  Ask Zac.
						.sessionAttr("username", "TestUser")
		);
		assertTrue(clubRepo.count() == 1);
	}
	@Test
	public void deleteClubTest()
		throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Club club = new Club();
		club.serialNumber = 1231231;
		club.maker = "TestMaker";
		club.clubType = "TestType";
		club.year = 1998;
		club.lieAngle = "Yellow";
		club.time = LocalDateTime.now().toString();

		String json = mapper.writeValueAsString(club);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-club")
				.content(json)
				.header("Content-Type", "application/json")
				.sessionAttr("username", "TestUser")
		);
		Club club2 = clubRepo.findOneBySerialNumber(club.serialNumber);
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-club/" + club2.id)
						.sessionAttr("username", "TestUser")
		);
		assertTrue(clubRepo.count() == 0);
	}



	/*
	Hat
	 */
	@Test
	public void addHatTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Hat hat = new Hat();
		//hat.id= 1;
		hat.maker = "Test Maker";
		hat.fit = "Test Fit";
		hat.color = "Black";
		hat.price = "12.99";
		//hat.time = LocalDateTime.now().toString();
		String json = mapper.writeValueAsString(hat);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-hat")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);
		assertTrue(hatRepo.count() == 1);
	}
	@Test
	public void editTestHat()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Hat hat = new Hat();
		hat.id = 1;
		hat.maker = "Test Maker";
		hat.fit = "Test Fit";
		hat.color = "Black";
		hat.price = "12.99";
		hat.time = LocalDateTime.now().toString();

		String json = mapper.writeValueAsString(hat);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-hat")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);

		Hat hat2 = hatRepo.findOneById(hat.id);
		hat.maker = "Test Maker Edit";
		hat.fit = "Test Fit Edit";
		hat.color = "Black Edit";
		hat.price = "12.99 Edit";
		hat.time = LocalDateTime.now().toString();

		ObjectMapper mapper2 = new ObjectMapper();

		String json2 = mapper.writeValueAsString(hat2);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/edit-hat") //when this is set to put it does not work for some reason? Ask Zac.
						.content(json2)
						.header("Content-Type", "application/json") //what does this do specifically?  Ask Zac.
						.sessionAttr("username", "TestUser")
		);
		assertTrue(hatRepo.count() == 1);
	}
	@Test
	public void deleteHatTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Hat hat = new Hat();
		//hat.id = 1;
		hat.maker = "Test Maker";
		hat.fit = "Test Fit";
		hat.color = "Black";
		hat.price = "12.99";
		hat.time = LocalDateTime.now().toString();
		String json = mapper.writeValueAsString(hat);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-hat")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);
		hat = hatRepo.findAll().iterator().next();
		//Hat hat2 = hatRepo.findOneById(hat.id);
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-hat/" + hat.id)
						.sessionAttr("username", "TestUser")
		);
		long count = hatRepo.count();
		assertTrue(count == 0);
	}

	/*
	   Bag
	 */

	@Test
	public void addBagTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Bag bag = new Bag();
		bag.maker = "Test Bag Maker";
		bag.stand = "Test Bag Stand";
		bag.harness = "Test Bag Stand";
		bag.price = "119.99";
		String json = mapper.writeValueAsString(bag);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-bag")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);
		assertTrue(bagRepo.count() == 1);
	}
	@Test
	public void editBagTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Bag bag = new Bag();
		bag.maker = "Test Bag Maker";
		bag.stand = "Test Bag Stand";
		bag.harness = "Test Bag Stand";
		bag.price = "119.99";
		String json = mapper.writeValueAsString(bag);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-bag")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);

		Bag bag2 = bagRepo.findAll().iterator().next();
		bag2.maker = "Edit Bag Maker";
		bag2.stand = "Edit Bag Stand";
		bag2.harness = "Edit Bag Stand";
		bag2.price = "109.99";

		ObjectMapper mapper2 = new ObjectMapper();

		String json2 = mapper2.writeValueAsString(bag2);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/edit-bag") //when this is set to put it does not work for some reason? Ask Zac.
						.content(json2)
						.header("Content-Type", "application/json") //what does this do specifically?  Ask Zac.
						.sessionAttr("username", "TestUser")
		);
		assertTrue(bagRepo.count() == 1);
	}
	@Test
	public void deleteBagTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Bag bag = new Bag();
		bag.id = 1;
		bag.maker = "Test Bag Maker";
		bag.stand = "Test Bag Stand";
		bag.harness = "Test Bag Stand";
		bag.price = "119.99";
		String json = mapper.writeValueAsString(bag);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-bag")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);


		Bag bag2 = bagRepo.findAll().iterator().next();
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-bag/" + bag2.id)
						.sessionAttr("username", "TestUser")
		);
		assertTrue(bagRepo.count() == 0);
	}







	/*@Test
	public void importFileTest()throws Exception{
		MockMultipartFile testFile = new MockMultipartFile("file", "test_items.csv", "text/csv", "test csv".getBytes());

		mockMvc.perform(
				MockMvcRequestBuilders.fileUpload("/import-file")
						.file(testFile)
		);
		assertTrue(itemRepo.count() > 0);
	}*/
}
