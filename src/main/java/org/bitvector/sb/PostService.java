package org.bitvector.sb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    private String url = "http://jsonplaceholder.typicode.com/posts";
    private RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("unused")
    String getUrl() {
        return url;
    }

    @SuppressWarnings("unused")
    void setUrl(String url) {
        this.url = url;
    }

    List<Post> getAll() {
        Post[] posts = restTemplate.getForObject(url, Post[].class);
        return Arrays.asList(posts);
    }

    Post get(String id) {
        return restTemplate.getForObject(url + "/" + id, Post.class);
    }

    Post update(String id, Post post) {
        // Don't actually upstream the data just round trip it (return the new Post object)
        logger.warn("Ignoring call to update object: " + id);
        return post;
    }

    HashMap<String, Integer> meta() {

        HashMap<String, Integer> counters = new HashMap<>();

        HashMap<Integer, Integer> countsByUser = new HashMap<>();

        Post[] posts = restTemplate.getForObject(url, Post[].class);

        Arrays.asList(posts).forEach(post -> {
            Integer newPostCount = counters.get("posts");
            if (newPostCount == null) {
                newPostCount = 1;
            } else {
                newPostCount += 1;
            }
            counters.put("posts", newPostCount);

            Integer newCountByUser = countsByUser.get(post.getUserId());
            if (newCountByUser == null) {
                newCountByUser = 1;
            } else {
                newCountByUser += 1;
            }
            countsByUser.put(post.getUserId(), newCountByUser);
        });

        counters.put("users", countsByUser.size());

        return counters;
    }

}
