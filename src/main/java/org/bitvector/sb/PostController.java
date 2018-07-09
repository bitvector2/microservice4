package org.bitvector.sb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<Post> getAll(@RequestParam("sortKey") String sortKey) {
        return postService.getAll(sortKey);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public Post get(@PathVariable("id") String id) {
        return postService.get(id);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PATCH)
    public Post update(@PathVariable("id") String id, @RequestBody Post postPatch) {
        Post post = postService.get(id);

        if (postPatch.getTitle() != null) {
            post.setTitle(postPatch.getTitle());
        }
        if (postPatch.getBody() != null) {
            post.setBody(postPatch.getBody());
        }

        return postService.update(id, post);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public Post add(@RequestBody Post postPatch) {
        Post post = new Post();

        if (postPatch.getUserId() != null) {
            post.setUserId(postPatch.getUserId());
        }
        if (postPatch.getTitle() != null) {
            post.setTitle(postPatch.getTitle());
        }
        if (postPatch.getBody() != null) {
            post.setBody(postPatch.getBody());
        }

        return postService.add(post);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        postService.delete(id);
    }

    @RequestMapping(value = "/posts/meta", method = RequestMethod.GET)
    public HashMap<String, Long> meta() {
        return postService.meta();
    }

}
