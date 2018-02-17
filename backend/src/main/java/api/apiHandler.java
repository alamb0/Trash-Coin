package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import implement.TrashNode;
import implement.User;

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
}
