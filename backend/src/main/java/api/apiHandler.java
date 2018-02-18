package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import implement.TrashNode;
import implement.User;
import implement.DBHandler;


import static implement.utility.*;

import java.util.List;@RestController
public class apiHandler {

    @RequestMapping(value="/near/user", method = RequestMethod.POST)
    public String addPointToUser(@RequestParam(value="x") String x, @RequestParam(value="y") String y) {
        DBHandler db = new DBHandler();
        try{
            db.addPointToUser(Double.parseDouble(x),Double.parseDouble(y));
            return "SUCCESS";
        }catch(Exception e){
            return "FAILURE";
        }
    }

    @RequestMapping(value="/user/{id}/update", method = RequestMethod.POST)
    public String updateUserCoordinate(@PathVariable(value="id") String id,
                            @RequestParam(value="x") String x, @RequestParam(value="y") String y) {
        DBHandler db = new DBHandler();
        try{
            db.findUserandUpdate(Integer.parseInt(id),Double.parseDouble(x),Double.parseDouble(y));
            return "SUCCESS";
        }catch(Exception e){
            return "FAILURE";
        }

    }

    @RequestMapping(value="/user/create", method = RequestMethod.POST)
    public User createUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        User newUser = new User(username, password, 0, 0);
        DBHandler db = new DBHandler();
        db.addUser(newUser);
        return newUser;
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable(value="id") String id) {
        try{
            DBHandler db = new DBHandler();
            db.deleteUser(Long.valueOf(id).longValue());
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
