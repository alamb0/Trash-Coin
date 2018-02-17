package api;

import java.util.concurrent.atomic.AtomicLong;
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
    public TrashNode TrashNode(@RequestParam(value="id") String id) {
        return new TrashNode(id, coordinate, weight);
    }
}
