package org.bitvector.sb;

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
    public int testPort;

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Test
    public void getMetaShouldReturnLiveData() {
        // full round trip with live data
        String url = "http://127.0.0.1:" + testPort + "/posts/meta";
        assertThat(testRestTemplate.getForObject(url, String.class)).contains("users");
    }

    @Test
    public void patchPostShouldReturnLiveData() {
        // full round trip with live data
        String url = "http://127.0.0.1:" + testPort + "/posts/4";
        Post post = new Post();
        post.setTitle("1800Flowers");
        post.setBody("1800Flowers");
        assertThat(testRestTemplate.patchForObject(url, post, String.class)).contains("1800Flowers");
    }

}
