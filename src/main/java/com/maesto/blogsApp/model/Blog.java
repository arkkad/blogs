package com.maesto.blogsApp.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "created")
    private String createdDate;

    public Blog() {
    }

    public Blog(Long id, String text, String createdDate) {
        this.id = id;
        this.text = text;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id) &&
                Objects.equals(text, blog.text) &&
                Objects.equals(createdDate, blog.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, createdDate);
    }

    public static class Builder {
        private Long id;
        private String text;
        private String createdDate;

        public Builder() {
        }

        public Blog build() {
            Blog blog = new Blog();
            blog.id = id;
            blog.text = text;
            blog.createdDate = createdDate;
            return blog;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withDate(String ldt) {
            this.createdDate = ldt;
            return this;
        }
    }
}
