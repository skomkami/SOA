package app.model;

import javax.persistence.*;

@Table(name = "notifications")
@Entity
public class Notification extends IdentifiableVersionedEntity {

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Notification(String message, Reader reader ) {
        this.message = message;
        this.reader = reader;
    }

    public Notification() {
    }
}
