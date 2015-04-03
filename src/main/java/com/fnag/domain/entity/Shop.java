package com.fnag.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A {@code Shop} is identified by a unique name
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Entity
public class Shop{

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return name.equals(shop.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
