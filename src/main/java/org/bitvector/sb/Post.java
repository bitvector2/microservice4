package org.bitvector.sb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    @SuppressWarnings("WeakerAccess")
    public Post() {
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public Integer getUserId() {
        return userId;
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @SuppressWarnings("unused, WeakerAccess")
    public Integer getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(Integer id) {
        this.id = id;
    }

    @SuppressWarnings("unused, WeakerAccess")
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused, WeakerAccess")
    public void setTitle(String title) {
        this.title = title;
    }

    @SuppressWarnings("unused, WeakerAccess")
    public String getBody() {
        return body;
    }

    @SuppressWarnings("unused, WeakerAccess")
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(userId, post.userId) &&
                Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(body, post.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, id, title, body);
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
