package com.pams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pams.entities.*;
import com.pams.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
	BagRepository bagRepo;

	@Autowired
	BallRepository ballRepo;

	@Autowired
	ItemRepository clubRepo;

	@Autowired
	HatRepository hatRepo;

	@Autowired
	PantRepository pantRepo;

	@Autowired
	ShirtRepository shirtRepo;

	@Autowired
	ShoeRepository shoeRepo;

	@Autowired
	UmbrellaRepository umbrellaRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@Before
	public void before() {
		bagRepo.deleteAll();
		ballRepo.deleteAll();
		clubRepo.deleteAll();
		hatRepo.deleteAll();
		pantRepo.deleteAll();
		shirtRepo.deleteAll();
		shoeRepo.deleteAll();
		umbrellaRepo.deleteAll();
		umbrellaRepo.deleteAll();
		userRepo.deleteAll();
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

	//Add Bag Test
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

	//Edit Bag Test
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

	//Delete Bag Test
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

	//Add Ball Test
	@Test
	public void addBallTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Ball ball = new Ball();
		ball.maker = "Titlest";
		ball.coating = "sync";
		ball.layers = "3";
		ball.boxCount = "12";
		ball.price = "143.00";

		String json = mapper.writeValueAsString(ball);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-ball")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "Test User")
		);
		assertTrue(ballRepo.count() == 1);
	}

	//Edit Ball Test
	@Test
	public void editTestBall()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Ball ball = new Ball();
		ball.maker = "Titlest";
		ball.coating = "syn";
		ball.layers = "3";
		ball.boxCount = "12";
		ball.price = "12.33";

		String json = mapper.writeValueAsString(ball);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-ball")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "Test User")
		);

		ObjectMapper mapper2 = new ObjectMapper();
		Ball ball2 = ballRepo.findOneByPrice(ball.price);
		ball2.maker = "Ping";
		ball2.coating = "syn";
		ball2.layers = "4";
		ball2.boxCount = "24";
		ball2.price = "14.33";

		String json2 = mapper2.writeValueAsString(ball2);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/edit-ball")
						.content(json2)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "Test User")
		);
		assertTrue(ballRepo.count() == 1);
	}

	//Delete Ball Test
	@Test
	public void deleteBallTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Ball ball = new Ball();
		ball.maker = "Titlest";
		ball.coating = "sync";
		ball.layers = "3";
		ball.boxCount = "12";
		ball.price = "143.00";
		String json = mapper.writeValueAsString(ball);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-ball")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "Test User")
		);
		ball = ballRepo.findAll().iterator().next();
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-ball/" + ball.id)
						.sessionAttr("username", "TestUser")
		);
		long count = ballRepo.count();
		assertTrue(count == 0);
	}

	//Add Club Test
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

	//Edit Club Test
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


	//Delete Club Test
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


	//Add Hat Test
	@Test
	public void addHatTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Hat hat = new Hat();
		hat.maker = "Test Maker";
		hat.fit = "Test Fit";
		hat.color = "Black";
		hat.price = "12.99";
		String json = mapper.writeValueAsString(hat);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-hat")
						.content(json)
						.header("Content-Type", "application/json")
						.sessionAttr("username", "TestUser")
		);
		assertTrue(hatRepo.count() == 1);
	}

	//Edit Hat Test
	@Test
	public void editTestHat()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Hat hat = new Hat();
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
		hat2.maker = "Test Maker Edit";
		hat2.fit = "Test Fit Edit";
		hat2.color = "Black Edit";
		hat2.price = "12.99 Edit";
		hat2.time = LocalDateTime.now().toString();

		ObjectMapper mapper2 = new ObjectMapper();

		String json2 = mapper2.writeValueAsString(hat2);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/edit-hat") //when this is set to put it does not work for some reason? Ask Zac.
						.content(json2)
						.header("Content-Type", "application/json") //what does this do specifically?  Ask Zac.
						.sessionAttr("username", "TestUser")
		);
		assertTrue(hatRepo.count() == 1);
	}

	//Delete Hat Test
	@Test
	public void deleteHatTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Hat hat = new Hat();
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
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-hat/" + hat.id)
						.sessionAttr("username", "TestUser")
		);
		long count = hatRepo.count();
		assertTrue(count == 0);
	}

	//Add Pant Test
	@Test
	public void addPantTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Pant pant = new Pant();
		pant.maker = "Titlest";
		pant.fit = "34";
		pant.pantSize = "23";
		pant.inseam = "12";
		pant.color = "Green";
		pant.price = "12.33";

		String json = mapper.writeValueAsString(pant);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-pant")
				.content(json)
				.header("Content-Type", "application/json")
				.sessionAttr("username", "Test User")
		);
		assertTrue(pantRepo.count() == 1);
	}

	//Edit Pant Test
	@Test
	public void editPantTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Pant pant = new Pant();
		pant.maker = "Titlest";
		pant.fit = "23";
		pant.pantSize = "24";
		pant.inseam = "12";
		pant.color = "Yellow";
		pant.price = "14.33";

		String json = mapper.writeValueAsString(pant);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-pant")
				.content(json)
				.header("Content-Type", "application/json")
				.sessionAttr("username", "Test User")
		);
		Pant pant2 = pantRepo.findOneByMaker(pant.maker);
		pant2.maker = "Ping";
		pant2.fit = "24";
		pant2.pantSize = "23";
		pant2.inseam = "12";
		pant2.color = "Green";
		pant2.price = "14.44";

		String json2 = mapper.writeValueAsString(pant2);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/edit-pant")
				.content(json2)
				.header("Content-Type", "application/json")
				.sessionAttr("username", "Test User")
		);
		assertTrue(pantRepo.count() == 1);
	}

	//Delete Pant Test
	@Test
	public void deletePantTest()
			throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Pant pant = new Pant();
		pant.maker = "Ping";
		pant.fit = "24";
		pant.pantSize = "12";
		pant.inseam = "13";
		pant.color = "Yellow";
		pant.price = "12.22";

		String json = mapper.writeValueAsString(pant);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-pant")
				.content(json)
				.header("Content-Type", "application/json")
				.sessionAttr("username", "Test User")
		);
		pant = pantRepo.findOneByMaker(pant.maker);
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-pant/" + pant.id)
				.sessionAttr("username", "Test User")
		);
		long count = pantRepo.count();
		assertTrue(count == 0);
	}

	//Add User Test
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

	//Edit User Test
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

	//Delete User Test
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
}
