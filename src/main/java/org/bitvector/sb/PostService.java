package org.bitvector.sb;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PostService {

    private RestTemplate restTemplate;
    private HazelcastInstance hazelcastInstance;

    @Value("${post_service_url}")
    private String url;

    @Autowired
    public PostService(RestTemplateBuilder restTemplateBuilder, HazelcastInstance hazelcastInstance) {
        this.restTemplate = restTemplateBuilder.build();
        this.hazelcastInstance = hazelcastInstance;
    }

    @SuppressWarnings("unused")
    public String getUrl() {
        return url;
    }

    @SuppressWarnings("unused")
    public void setUrl(String url) {
        this.url = url;
    }

    @SuppressWarnings("WeakerAccess")
    public List<Post> getAll(String sortKey) {
        Post[] arr = restTemplate.getForObject(url, Post[].class);
        List<Post> posts = new ArrayList<>(Arrays.asList(arr));

        if (sortKey != null) {
            switch (sortKey) {
                case "id":
                    posts.sort(Comparator.comparing(Post::getId));
                    break;
                case "userId":
                    posts.sort(Comparator.comparing(Post::getUserId));
                    break;
                case "title":
                    posts.sort(Comparator.comparing(Post::getTitle));
                    break;
            }
        }

        return posts;
    }

    @CachePut(value = "posts", key = "#result.id")
    public Post get(String id) {
        return restTemplate.getForObject(url + "/" + id, Post.class);
    }

    @SuppressWarnings("WeakerAccess")
    @CachePut(value = "posts", key = "#result.id")
    public Post update(String id, Post post) {
        return restTemplate.patchForObject(url + "/" + id, post, Post.class);
    }

    @SuppressWarnings("WeakerAccess")
    public HashMap<String, Long> meta() {
        HashMap<String, Long> counters = new HashMap<>();
        HashMap<Integer, Integer> countsByUser = new HashMap<>();

        List<Post> posts = this.getAll(null);

        for (Post post : posts) {
            Integer newCountByUser = countsByUser.get(post.getUserId());
            if (newCountByUser == null) {
                newCountByUser = 1;
            } else {
                newCountByUser += 1;
            }
            countsByUser.put(post.getUserId(), newCountByUser);
        }

        Long numPosts = (long) posts.size();
        Long numUsers = (long) countsByUser.size();

        IMap<Object, Object> map = hazelcastInstance.getMap("posts");
        Long numCached = map.getLocalMapStats().getOwnedEntryCount();

        counters.put("posts", numPosts);
        counters.put("users", numUsers);
        counters.put("cached", numCached);

        return counters;
    }

}
