package org.bitvector;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Component
public class PostService {

    private final RestTemplate restTemplate = new RestTemplate();

    List<Post> getAll() {
        String url = "http://jsonplaceholder.typicode.com/posts";
        ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity(url, Post[].class);

        return Arrays.asList(responseEntity.getBody());
    }

}
