package de.weichert.demo.multitenancy.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jannik on 02.07.15.
 */
@Entity
public class Foo {

    @Id
    long id;

    String name;

    public Foo() {
    }

    public Foo(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
