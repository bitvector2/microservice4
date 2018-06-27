package org.bitvector.sb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public List<Post> getAllSorted(@RequestParam("sortKey") Optional<String> sortKey) {
        if (sortKey.isPresent())
            return postService.getAll(Optional.of(sortKey.toString()));
        else
            return postService.getAll(Optional.empty());
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

    @RequestMapping(value = "/posts/meta", method = RequestMethod.GET)
    public HashMap<String, Integer> meta() {
        return postService.meta();
    }

}
