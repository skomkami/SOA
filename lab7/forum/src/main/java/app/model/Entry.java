package app.model;

import javax.persistence.*;

@Entity
@Table(name = "entries")
public class Entry extends IdentifiableVersionedEntity {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "theme")
    private Theme theme;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
