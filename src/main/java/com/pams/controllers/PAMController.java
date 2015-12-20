package com.pams.controllers;

import com.pams.entities.*;
import com.pams.services.*;
import com.pams.utils.PasswordHash;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Created by MattBrown on 12/8/15.
 */
@RestController
public class PAMController {

    @Autowired
    UserRepository users;

    @Autowired
    ItemRepository clubs;

    @Autowired
    HatRepository hats;

    @Autowired
    ShirtRepository shirts;

    @Autowired
    BallRepository balls;

    @Autowired
    BagRepository bags;

    static int fakeNum = 1000;

    @PostConstruct
    public void loadData() throws FileNotFoundException { //(has add (tests), edit (tests), delete (tests)
        if (clubs.count() == 0){
            Scanner scanner = new Scanner(new File("golf.csv"));
            scanner.nextLine();
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String [] columns = line.split(",");
                Club c = new Club();
                c.serialNumber = Integer.valueOf(columns[0]);
                c.maker = columns[1];
                c.clubType = columns[2];
                c.year = Integer.valueOf(columns[3]);
                c.lieAngle = columns[4];
                c.isAuthentic = true;
                c.time = LocalDateTime.now().toString();
                clubs.save(c);
            }
        }
        if (hats.count() == 0){ //(has add, edit, delete) NO TESTS
            Scanner scanner = new Scanner(new File("hats.csv"));
            scanner.nextLine();
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String [] columns = line.split(",");
                Hat h = new Hat();
                h.maker = columns[0];
                h.fit = columns[1];
                h.color = columns[2];
                h.price = columns[3];
                h.time = LocalDateTime.now().toString();
                hats.save(h);
            }
        }
        if (shirts.count() == 0){ //(has add, edit, and delete) NO TESTS
            Scanner scanner = new Scanner(new File("shirts.csv"));
            scanner.nextLine();
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String [] columns = line.split(",");
                Shirt s = new Shirt();
                s.maker = columns[0];
                s.fit = columns[1];
                s.color = columns[2];
                s.price = columns[3];
                s.time = LocalDateTime.now().toString();
                shirts.save(s);
            }
        }
        if (balls.count() == 0){ //(has add, edit, and delete) NO TESTS
            Scanner scanner = new Scanner(new File("balls.csv"));
            scanner.nextLine();
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String [] columns = line.split(",");
                Ball b = new Ball();
                b.maker = columns[0];
                b.coating = columns[1];
                b.layers = columns[2];
                b.boxCount = columns[3];
                b.price = columns[4];
                b.time = LocalDateTime.now().toString();
                balls.save(b);
            }
        }
        if (bags.count() == 0){ //(has add, edit, and delete) NO TESTS
            Scanner scanner = new Scanner(new File("bags.csv"));
            scanner.nextLine();
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String [] columns = line.split(",");
                Bag bag = new Bag();
                bag.maker = columns[0];
                bag.stand = columns[1];
                bag.harness = columns[2];
                bag.teamName = columns[3];
                bag.schoolName = columns[4];
                bag.price = columns[5];
                bag.time = LocalDateTime.now().toString();
                bags.save(bag);
            }
        }

    }
    //Session Group
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        User tempUser = users.findOneByUsername(user.username);
        if (tempUser == null) {
            tempUser = new User();
            tempUser.username = user.username;
            tempUser.password = PasswordHash.createHash(user.password);
            tempUser.accessLevel = User.AccessLevel.ADMIN;
            users.save(tempUser);
        }
        else if (!PasswordHash.validatePassword(user.password, tempUser.password)){
                    throw new Exception ("Wrong password!");
        }
        session.setAttribute("username", user.username);
        return tempUser;
    }

    @RequestMapping("/logout")
    public void logout(
            HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
    }


    //User Group
    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public User addUser(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        User tempUser = users.findOneByUsername(user.username);
        if (tempUser == null){
            tempUser = new User();
            tempUser.username = user.username;
            tempUser.password = PasswordHash.createHash(user.password);
            tempUser.accessLevel = user.getAccessLevel();
            tempUser.companyName = user.companyName;
            tempUser.address = user.address;
            tempUser.city = user.city;
            tempUser.state = user.state;
            tempUser.zip = user.zip;
            tempUser.email = user.email;
            users.save(tempUser);
        }
        else if (!PasswordHash.validatePassword(user.password, tempUser.password)){
            throw new Exception ("Wrong password!");
        }
        session.setAttribute("username", user.username);
        return tempUser;
    }

    @RequestMapping(path = "/find-users" , method = RequestMethod.GET)
    public Iterable<User> findUsers(
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot find a user!");
        }
        return users.findAll();
    }

    @RequestMapping(path = "/edit-user", method = RequestMethod.PUT)
    public User editUser(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        users.save(user);

        return user;
    }

    @RequestMapping(path = "/delete-user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(
            @PathVariable("id") int id,
            HttpSession session
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot delete!");
        }
        users.delete(id);
    }


    //Club Group
    @RequestMapping(path ="/create-club", method = RequestMethod.POST)
    public Club addClub(
            @RequestBody Club club,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot create this club!");
        }
        club.time = LocalDateTime.now().toString();
        clubs.save(club);
        return club;
    }

    @RequestMapping(path = "/find-club/{serialNumber}" , method = RequestMethod.GET)
    public Club findClub(
            @PathVariable ("serialNumber") int serialNumber,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot find a club!");
        }
        if(!(clubs.findOneBySerialNumber(serialNumber) == null)){
            return clubs.findOneBySerialNumber(serialNumber);
        }
        else{
            Club jackClub = new Club(serialNumber, "Fake Make", "Fake Club Type", (fakeNum+1), "Fake Lie Angle", false, LocalDateTime.now().toString());
            clubs.save(jackClub);
            return jackClub;
        }
    }

    @RequestMapping(path = "/list-jacks", method = RequestMethod.POST)
    public Iterable<Club> listJacks(
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot list jacks!");
        }
        return clubs.findAllByIsAuthentic(false);
    }

    @RequestMapping(path = "/search-by-maker/{maker}", method = RequestMethod.GET)
    public Iterable<Club> searchByMaker(
            @PathVariable ("maker") String maker
    ) throws Exception{
        return clubs.findAllByMaker(maker);
    }

    @RequestMapping(path = "/search-by-clubType/{clubType}", method = RequestMethod.GET)
    public Iterable<Club> searchByClubType(
            @PathVariable ("clubType") String clubType
    ) throws Exception{
        return clubs.findAllByClubType(clubType);
    }

    @RequestMapping(path = "/search-by-year/{year}", method = RequestMethod.GET)
    public Iterable<Club> searchByYear(
            @PathVariable ("year") int year
    ) throws Exception{
        return clubs.findAllByYear(year);
    }

    @RequestMapping(path = "/search-by-lie-angle/{lieAngle}", method = RequestMethod.GET)
    public Iterable<Club> searchByLieAngle(
            @PathVariable ("lieAngle") String lieAngle
    ) throws Exception{
        return clubs.findAllByLieAngle(lieAngle);
    }

    @RequestMapping(path = "/edit-club", method = RequestMethod.POST)
    public void editClub(
            @RequestBody Club club,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot edit this club!");
        }
        clubs.save(club);
    }

    @RequestMapping(path = "/delete-club/{id}", method = RequestMethod.DELETE)
    public void deleteClub(
            @PathVariable ("id") int id,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot delete this club!");
        }
        clubs.delete(id);
    }


    //Hat Group
    @RequestMapping(path = "/create-hat", method = RequestMethod.POST)
    public Hat createHat(
            @RequestBody Hat hat,
            HttpSession session
    )throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot create a hat!");
        }
        hat.time = LocalDateTime.now().toString();
        hats.save(hat);
        return hat;
    }

    @RequestMapping(path = "/edit-hat", method = RequestMethod.POST)
    public void editHat(
            @RequestBody Hat hat,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot edit a hat!");
        }
        hats.save(hat);
    }

    @RequestMapping(path = "/delete-hat/{id}", method = RequestMethod.DELETE)
    public void deleteHat(
            @PathVariable("int") int id,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot delete a hat!");
        }
        hats.delete(id);
    }


    //Shirt Group
    @RequestMapping(path = "/create-shirt", method = RequestMethod.POST)
    public Shirt createShirt(
            @RequestBody Shirt shirt,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot create a shirt!");
        }
        shirt.time = LocalDateTime.now().toString();
        shirts.save(shirt);
        return shirt;
    }

    @RequestMapping(path = "/edit-shirt", method = RequestMethod.POST)
    public void editShirt(
            @RequestBody Shirt shirt,
            HttpSession session
    )throws Exception{
        if(session.getAttribute("username") == null){
            throw new Exception("You cannot edit this shirt!");
        }
        shirts.save(shirt);
    }

    @RequestMapping(path = "/delete-shirt/{id}", method = RequestMethod.DELETE)
    public void deleteShirt(
            @PathVariable("int") int id,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot delete this shirt");
        }
        shirts.delete(id);
    }


    //Ball Group
    @RequestMapping(path = "/create-ball", method = RequestMethod.POST)
    public Ball createBall(
            @RequestBody Ball ball,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot create a ball!");
        }
        ball.time = LocalDateTime.now().toString();
        balls.save(ball);
        return ball;
    }

    @RequestMapping(path = "edit-ball", method = RequestMethod.POST)
    public void editBall(
            @RequestBody Ball ball,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot edit this ball!");
        }
        balls.save(ball);
    }

    @RequestMapping(path = "/delete-ball/{id}", method = RequestMethod.DELETE)
    public void deleteBall(
            @PathVariable("int") int id,
            HttpSession session
    )throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot delete this ball!");
        }
        balls.delete(id);
    }


    //Bag Group
    @RequestMapping(path = "/create-bag", method = RequestMethod.POST)
    public Bag createBag(
            @RequestBody Bag bag,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot create a bag!");
        }
        bag.time = LocalDateTime.now().toString();
        bags.save(bag);
        return bag;
    }

    @RequestMapping(path = "/edit-bag", method = RequestMethod.POST)
    public void editBag(
            @RequestBody Bag bag,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot edit a bag!");
        }
        bags.save(bag);
    }

    @RequestMapping(path = "/delete-bag/{id}", method = RequestMethod.DELETE)
    public void deleteBag(
            @PathVariable("int") int id,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot delete this bag!");
        }
        bags.delete(id);
    }


    //Import File Group
    @RequestMapping(path = "/import-file", method = RequestMethod.POST)//Club-Upload
    public void importFile(
            HttpSession session,
            MultipartFile file
    ) throws Exception {
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot import!");
        }
        Scanner scanner = new Scanner (file.getInputStream());
        scanner.nextLine();
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Club c = new Club();
            c.serialNumber = Integer.valueOf(columns[0]);
            c.maker = columns[1];
            c.clubType = columns[2];
            c.year = Integer.valueOf(columns[3]);
            c.lieAngle = columns[4];
            c.isAuthentic = true;
            clubs.save(c);
        }
    }

    @RequestMapping(path = "/upload-hats", method = RequestMethod.POST)
    public void importHats(
            HttpSession session,
            MultipartFile file
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot import hats!");
            }
        Scanner scanner = new Scanner (file.getInputStream());
        scanner.nextLine();
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Hat hat = new Hat();
            hat.maker = columns[0];
            hat.fit = columns[1];
            hat.color = columns[2];
            hat.price = columns[3];
            hat.time = LocalDateTime.now().toString();
            hats.save(hat);
        }
    }

    @RequestMapping(path = "/upload-shirts", method = RequestMethod.POST)
    public void importShirt(
            HttpSession session,
            MultipartFile file
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot import shirts");
        }
        Scanner scanner = new Scanner (file.getInputStream());
        scanner.nextLine();
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Shirt shirt = new Shirt();
            shirt.maker = columns[0];
            shirt.fit = columns[1];
            shirt.color = columns[2];
            shirt.price = columns[3];
            shirt.time = LocalDateTime.now().toString();
            shirts.save(shirt);
        }
    }

    @RequestMapping(path = "/upload-balls", method = RequestMethod.POST)
    public void importBalls(
            HttpSession session,
            MultipartFile file
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot import balls!");
        }
        Scanner scanner = new Scanner (file.getInputStream());
        scanner.nextLine();
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Ball ball = new Ball();
            ball.maker = columns[0];
            ball.coating = columns[1];
            ball.layers = columns[2];
            ball.boxCount = columns[3];
            ball.price = columns[4];
            ball.time = LocalDateTime.now().toString();
            balls.save(ball);
        }
    }

    @RequestMapping(path = "/upload-bags", method = RequestMethod.POST)
    public void importBags(
            HttpSession session,
            MultipartFile file
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception("You cannot import bags!");
        }
        Scanner scanner = new Scanner (file.getInputStream());
        scanner.nextLine();
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Bag bag = new Bag();
            bag.maker = columns[0];
            bag.stand = columns[1];
            bag.harness = columns[2];
            bag.teamName = columns[3];
            bag.schoolName = columns[4];
            bag.price = columns[5];
            bag.time = LocalDateTime.now().toString();
            bags.save(bag);
        }
    }
}

/*@RequestMapping(path = "/edit-inventory", method = RequestMethod.POST)
    public void editInventory(
            @RequestBody Club club,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot edit!");
        }
        clubs.save(club);
    }

    @RequestMapping(path = "/delete-inventory/{id}", method = RequestMethod.DELETE)
    public void deleteInventory(
            @PathVariable("id") int id,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot delete!");
        }
        clubs.delete(id);
    }*/

  /*@RequestMapping(path = "/search-by-time/{time}", method = RequestMethod.GET)
    public Iterable<Club> searchByTime(
            @PathVariable ("time") LocalDateTime time
    ) throws Exception{
        return clubs.findAllByTime(time);
    }*/