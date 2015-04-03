package com.fnag.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A {@code Seller} is identified by a unique name and works in a {@link com.fnag.domain.entity.Shop}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Entity
public class Seller {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Shop shop;

    public Seller() {
    }

    public Seller(String name, Shop shop) {
        this.name = name;
        this.shop = shop;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return name.equals(seller.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
