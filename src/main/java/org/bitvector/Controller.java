package org.bitvector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class Controller {

    private final PostService postService;

    @Autowired
    public Controller(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<Post> getAll() {
        return postService.getAll();
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public Post get(@PathVariable("id") String id) {
        return postService.get(id);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PATCH)
    public Post update(@PathVariable("id") String id, @RequestBody Post postPatch) {
        System.out.println(postPatch);

        Post post = postService.get(id);

        if (postPatch.getTitle() != null) {
            post.setTitle(postPatch.getTitle());
        }
        if (postPatch.getBody() != null) {
            post.setBody(postPatch.getBody());
        }

        return postService.update(id, post);
    }

    @RequestMapping(value = "/posts/meta", method = RequestMethod.GET)
    public HashMap<String, Integer> meta() {
        return postService.meta();
    }

}