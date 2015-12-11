package com.pams.controllers;

import com.pams.entities.Item;
import com.pams.services.ItemRepository;
import com.pams.entities.User;
import com.pams.services.UserRepository;
import com.pams.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

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

    @RequestMapping(path = "/login/", method = RequestMethod.POST)
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
            users.save(tempUser);
        }
        else if (!PasswordHash.validatePassword(user.password, tempUser.password)){
            throw new Exception ("Wrong password!");
        }
        session.setAttribute("username", user.username);
        return tempUser;
    }

    @RequestMapping(path = "/create-inventory", method = RequestMethod.POST)
    public void addInventory(
            @RequestBody Item item,
            HttpSession session,
            MultipartFile file
    )throws Exception{
        String username = (String) session.getAttribute("username");
        if (username == null){
            throw new Exception("Not logged in!");
        }
        File f = File.createTempFile("file", file.getOriginalFilename(), new File ("public"));
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        Item itemFile = new Item();
        itemFile.fileName = f.getName();
        items.save(item);
    }


    @RequestMapping(path = "/edit-user/{id}", method = RequestMethod.POST)
    public void editUser(
            @RequestBody User user,
            HttpSession session
    ) throws Exception {
        if (session.getAttribute("username") == null) {
            throw new Exception("You cannot edit!");
        }
        users.save(user);
    }
    @RequestMapping(path = "/edit-inventory/{id}", method = RequestMethod.POST)
    public void editInventory(
            @RequestBody Item item,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot edit!");
        }
        items.save(item);
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
    @RequestMapping(path = "/delete-inventory/{id}", method = RequestMethod.DELETE)
    public void deleteInventory(
            @PathVariable("id") int id,
            HttpSession session
    )throws Exception{
        if (session.getAttribute("username") == null){
            throw new Exception ("You cannot delete!");
        }
        items.delete(id);
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
}
