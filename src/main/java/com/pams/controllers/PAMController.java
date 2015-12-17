package com.pams.controllers;

import com.pams.entities.Club;
import com.pams.services.ItemRepository;
import com.pams.entities.User;
import com.pams.services.UserRepository;
import com.pams.utils.PasswordHash;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
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

    static int fakeNum = 1000;

    @PostConstruct
    public void loadData() throws FileNotFoundException {
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
                clubs.save(c);
            }
        }
    }

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
    )throws Exception{
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

    @RequestMapping(path ="/create-club", method = RequestMethod.POST)
    public Club addClub(
            @RequestBody Club club,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot create this club!");
        }
        clubs.save(club);
        return club;
    }

    @RequestMapping(path = "/find-club/{serialNumber}" , method = RequestMethod.GET)
    public Club findClub(
            @PathVariable ("serialNumber") int serialNumber
    )throws Exception{
        if(!(clubs.findOneBySerialNumber(serialNumber) == null)){
            return clubs.findOneBySerialNumber(serialNumber);
        }
        else{
            Club jackClub = new Club(serialNumber, "Fake Make", "Fake Club Type", (fakeNum+1), "Fake Lie Angle", false);
            clubs.save(jackClub);
            return jackClub;
        }
    }


    @RequestMapping(path = "/list-jacks", method = RequestMethod.GET)
    public Iterable<Club> listJacks()
            throws Exception{
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

    @RequestMapping(path = "/import-file", method = RequestMethod.POST)
    public void importFile(
            HttpSession session,
            MultipartFile file
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot import!");
        }
        Scanner scanner = new Scanner (file.getInputStream());
        scanner.nextLine();
        while (scanner.hasNext()) {
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
    
    @RequestMapping(path = "/edit-inventory", method = RequestMethod.POST)
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
    }

    @RequestMapping("/logout")
    public void logout(
            HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
    }
}

/* @RequestMapping(path = "/create-retailer", method = RequestMethod.POST)
    public void addRetailerUser(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        User tempUser = users.findOneByUsername(user.username);
        if (tempUser == null){
            users.save(user);
        }
        session.setAttribute("username", user.username);
    }

    @RequestMapping(path = "create-joe", method = RequestMethod.POST)
    public void addJoeUser(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        User tempUser = users.findOneByUsername(user.username);
        if (tempUser == null){
            users.save(user);
        }
    }
    */

    /*@RequestMapping("/delete-retailer")
    public void deleteRetailerUser(
            HttpSession session,
            int id
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot delete!");
        }
        users.delete(id);
    }

    @RequestMapping("/delete-joe")
    public void deleteJoeUser(
            HttpSession session,
            int id
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot delete!");
        }
        users.delete(id);
    }*/

/*@RequestMapping(path = "/edit-retailer/{id}", method = RequestMethod.POST)
    public void editRetailerUser(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        users.save(user);
    }

    @RequestMapping(path = "/edit-joe/{id}", method = RequestMethod.POST)
    public void editJoeUser(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        users.save(user);
    }*/
     /* @RequestMapping(path = "/upload-club-inventory", method = RequestMethod.POST)
    public void uploadClubsInventory(
            @RequestBody Club club,
            HttpSession session,
            MultipartFile file
    )throws Exception{
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        File f = File.createTempFile("file", file.getOriginalFilename(), new File ("public"));
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        Club itemFile = new Club();
        itemFile.fileName = f.getName();
        clubs.save(club);
    }*/

    /*@RequestMapping(path = "/create-balls-inventory", method = RequestMethod.POST)
    public void addBallsInventory(
            @RequestBody Balls balls,
            HttpSession session,
            MultipartFile file
    )throws Exception{

    }*/

/*@PostConstruct
    public void loadData(){
        String fileContent = readFile("golf.csv");
        String[] lines = fileContent.split("\r");
        if (clubs.count() == 0){
            for (String line : lines){
                if (line == lines [0])
                    continue;
                String columns[] = line.split(",");
                Club club = new Club();
                club.serialNumber = Integer.valueOf(columns[0]);
                club.maker = columns[1];
                club.clubType = columns[2];
                club.year = Integer.valueOf(columns[3]);
                club.lieAngle = columns[4];
                club.isAuthentic = true;
                clubs.save(club);
            }

        }
    }
    static String readFile(String fileName){
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        }catch (Exception e){
            return null;
        }
    }
*/

 /*if(clubs.count() == 0){
            String fileContentItems = new String(file.getBytes());
            String [] lineItems = fileContentItems.split("\n");

            for (String linesItems : lineItems){
                if (linesItems == lineItems[0])
                    continue;
                String [] columns = linesItems.split(",");
                Club club = new Club();
                club.serialNumber = Integer.valueOf(columns[0]);
                club.maker = columns[1];
                club.clubType = columns[2];
                club.year = Integer.valueOf(columns[3]);
                club.color = columns[4];
                club.isAuthentic = true;
                clubs.save(club);
            }
        }*/