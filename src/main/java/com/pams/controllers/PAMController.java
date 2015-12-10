package com.pams.controllers;

import com.pams.entities.Item;
import com.pams.services.ItemRepository;
import com.pams.utils.PasswordHash;
import com.pams.entities.User;
import com.pams.services.UserRepository;
import com.sun.tools.javac.jvm.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by MattBrown on 12/8/15.
 */
@RestController
public class PAMController {

    @Autowired
    UserRepository users;

    @Autowired
    ItemRepository items;

    /*@PostConstruct
    public void loadData(){
        String fileContent = readFile("items.csv");
        String[] lines = fileContent.split("\n");
        if (items.count() == 0){

        }
    }*/
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

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(
            @RequestBody User user,
             HttpSession session
    ) throws Exception {
       User tempUser = users.findOneByUsername(user.username);
        if (tempUser == null) {
            users.save(user);
        } else if (!PasswordHash.validatePassword(user.password, tempUser.password)) {
            throw new Exception("Wrong password!");
        }
        session.setAttribute("username", user.username);
    }

    @RequestMapping(path = "/create-company", method = RequestMethod.POST)
    public void addCompanyUser(
            HttpSession session,
            User user
    ) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You cant create a user!");
        }
        User tempUser = users.findOneByUsername(username);
        if (user == null) {
            user = new User();
            user.username = companyUsername;
            user.password = companyPassword;
            user.accessLevel = User.AccessLevel.COMPANY_USER;
            user.companyName = companyName;
            user.email = email;
            users.save(user);
        }
        return user;
    }

    @RequestMapping("/edit-company")
    public User editCompanyUser(
            HttpSession session,
            int id
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        User user = users.findOne(id);
        users.save(user);
        return user;
    }

    @RequestMapping("/delete-company")
    public void deleteCompanyUser(
            HttpSession session,
            int id
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot delete!");
        }
        users.delete(id);
    }

    @RequestMapping("/create-retailer")
    public User retailerUser(
            HttpSession session,
            String retailerUsername,
            String retailerPassword,
            String email,
            String companyName,
            String address,
            String city,
            String state,
            int zip
    ) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You cant create a user!");
        }
        User user = users.findOneByUsername(username);
        if (user == null) {
            user = new User();
            user.username = retailerUsername;
            user.password = retailerPassword;
            user.accessLevel = User.AccessLevel.RETAILER_USER;
            user.email = email;
            user.companyName = companyName;
            user.address = address;
            user.city = city;
            user.state = state;
            user.zip = zip;
            users.save(user);
        }
        return user;
    }

    @RequestMapping("/edit-retailer")
    public User editRetailerUser(
            HttpSession session,
            int id
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        User user = users.findOne(id);
        users.save(user);
        return user;
    }

    @RequestMapping("/delete-retailer")
    public void deleteRetailerUser(
            HttpSession session,
            int id
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot delete!");
        }
        users.delete(id);
    }

    @RequestMapping("create-joe")
    public User joeUser(
            HttpSession session,
            String joeUsername,
            String joePassword
    ) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You cant create a user!");
        }
        User user = users.findOneByUsername(username);
        if (user == null) {
            user = new User();
            user.username = joeUsername;
            user.password = joePassword;
            user.accessLevel = User.AccessLevel.JOE_USER;
            users.save(user);
        }
        return user;
    }

    @RequestMapping("/edit-joe")
    public User editJoeUser(
            HttpSession session,
            int id
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        User user = users.findOne(id);
        users.save(user);
        return user;
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
    }
//    @RequestMapping("/import-file")
//    public void importFile(HttpSession session, MultipartFile file) throws IOException {
//        if(items.count() == 0){
//            String fileContentItems = new String(file.getBytes());
//            String [] lineItems = fileContentItems.split("\n");
//
//            for (String linesItems : lineItems){
//                if (linesItems == lineItems[0])
//                    continue;
//                String [] columns = linesItems.split(",");
//                Item item = new Item();
//                item.serialNumber = columns[0];
//                item.productModel = columns [1];
//                item.companyUser = columns [2];
//                items.save(item);
//            }
//        }
//    }
}
