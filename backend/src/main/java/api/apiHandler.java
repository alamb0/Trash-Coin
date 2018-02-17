package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.bson.Document;

import implement.TrashNode;
import implement.User;
import implement.DBHandler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class apiHandler {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/trashNode/{id}")
    public TrashNode TrashNode(@PathParam(value="id") String id) {
        return new TrashNode(1, new ArrayList<Integer>(Arrays.asList(1, 2)), 15);
    }


    @RequestMapping("/user/{id}")
    public User User(@PathParam(value="id") String id) {
        return new User(2, new ArrayList<Integer>(Arrays.asList(1, 2)), 3);
    }

    @RequestMapping(value="/user/post/{id}", method = RequestMethod.POST)
    public User postUser(@PathParam(value="id") String id) {
        User user = new User(2, new ArrayList<Integer>(Arrays.asList(1, 2)), 3);
        DBHandler db = new DBHandler();

        ObjectMapper mapper = null;
        Document doc = null;
        String converted = "";
        try{
            mapper = new ObjectMapper();
            converted = mapper.writeValueAsString(user);
            doc = Document.parse(converted);
        }catch(Exception e){
            return null;
        }

        db.getDBcollection(db.USER_COLLECTION).insertOne(doc);
        return user;
    }
}
