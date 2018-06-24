package org.bitvector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getMetaShouldReturnData() {
        String url = "http://127.0.0.1:" + this.port + "/posts/meta";
        assertThat(this.restTemplate.getForObject(url, String.class)).contains("users");
    }

    @Test
    public void patchPostShouldReturnData() {
        String url = "http://127.0.0.1:" + this.port + "/posts/4";
        Post post = new Post();
        post.setTitle("1800Flowers");
        post.setBody("1800Flowers");
        assertThat(this.restTemplate.patchForObject(url, post, String.class)).contains("1800Flowers");
    }
}
