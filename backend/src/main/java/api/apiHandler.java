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
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import implement.TrashNode;
import implement.User;
import implement.DBHandler;

import java.util.List;@RestController
public class APIHandler {

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
    public DBObject updateUserCoordinate(@PathVariable(value="id") String id,
                            @RequestParam(value="x") String x, @RequestParam(value="y") String y) {
        DBHandler db = new DBHandler();
        try{
            return db.findUserandUpdate(Long.parseLong(id),Double.parseDouble(x),Double.parseDouble(y));
        }catch(Exception e){
            return new BasicDBObject("message", e.toString());
        }
    }

    @RequestMapping(value="/user/create", method = RequestMethod.POST)
    public BasicDBObject createUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        DBHandler db = new DBHandler();
        BasicDBObject newUser = new BasicDBObject();

        newUser.put("id", db.generateID());
        newUser.put("username", username);
        newUser.put("password", password);
        newUser.put("points", 0);

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
