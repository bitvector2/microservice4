package org.bitvector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final PostService postService;

    @Autowired
    public Controller(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public List<Post> home() {
        return postService.getAll();
    }

}
