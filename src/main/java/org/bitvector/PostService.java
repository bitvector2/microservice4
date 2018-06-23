package org.bitvector;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Component
public class PostService {
    private String url = "http://jsonplaceholder.typicode.com/posts"; // FIXME: hardcoded for brevity
    private final RestTemplate restTemplate = new RestTemplate();

    List<Post> getAll() {
        Post[] posts = restTemplate.getForObject(url, Post[].class);
        return Arrays.asList(posts);
    }

    Post get(String id) {
        return restTemplate.getForObject(url + "/" + id, Post.class);
    }

    Post update(String id, Post post) {
        return post;
    }

    HashMap<String, Integer> meta() {
        // {
        //	 "posts": 100,
        //	 "users": 10
        // }

        HashMap<String, Integer> metaInfo = new HashMap<>();

        HashMap<Integer, Integer> postsByUser = new HashMap<>();

        Post[] posts = restTemplate.getForObject(url, Post[].class);

        Arrays.asList(posts).forEach(post -> {
            Integer totalCount = metaInfo.get("posts");
            if (totalCount == null) {
                totalCount = 1;
            } else {
                totalCount += 1;
            }
            metaInfo.put("posts", totalCount);

            Integer count = postsByUser.get(post.getUserId());
            if (count == null) {
                count = 1;
            } else {
                count += 1;
            }
            postsByUser.put(post.getUserId(), count);
        });

        metaInfo.put("users", postsByUser.size());

        return metaInfo;
    }

}
