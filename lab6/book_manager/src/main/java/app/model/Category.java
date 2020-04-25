package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table( name = "categories")
@Entity
public class Category extends IdentifiableVersionedEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public Category() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
