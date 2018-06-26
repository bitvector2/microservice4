package org.bitvector.sb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService {

    private RestTemplate restTemplate;

    @Value("${post_service_url}")
    private String url;

    public PostService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @SuppressWarnings("unused")
    String getUrl() {
        return url;
    }

    @SuppressWarnings("unused")
    void setUrl(String url) {
        this.url = url;
    }

    List<Post> getAll() {
        Post[] arr = restTemplate.getForObject(url, Post[].class);
        return new ArrayList<>(Arrays.asList(arr));
    }

    Post get(String id) {
        return restTemplate.getForObject(url + "/" + id, Post.class);
    }

    Post update(String id, Post post) {
        return restTemplate.patchForObject(url + "/" + id, post, Post.class);
    }

    HashMap<String, Integer> meta() {
        HashMap<String, Integer> counters = new HashMap<>();
        HashMap<Integer, Integer> countsByUser = new HashMap<>();

        List<Post> posts = this.getAll();

        posts.forEach(post -> {
            Integer newCountByUser = countsByUser.get(post.getUserId());
            if (newCountByUser == null) {
                newCountByUser = 1;
            } else {
                newCountByUser += 1;
            }
            countsByUser.put(post.getUserId(), newCountByUser);
        });

        counters.put("posts", posts.size());
        counters.put("users", countsByUser.size());

        return counters;
    }

}
