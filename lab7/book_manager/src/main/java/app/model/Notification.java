package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "notifications")
@Entity
public class Notification extends IdentifiableVersionedEntity {

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "reader_login", nullable = false)
    private String reader;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public Notification(String message, String reader ) {
        this.message = message;
        this.reader = reader;
    }

    public Notification() {
    }
}
