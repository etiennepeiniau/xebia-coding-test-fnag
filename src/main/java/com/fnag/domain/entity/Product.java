package com.fnag.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * A {@code Product} has a reference which is unique, a price and a description.
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String reference;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String description;

    public Product() {
    }

    public Product(String reference, BigDecimal price, String description) {
        this.reference = reference;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return reference.equals(product.reference);

    }

    @Override
    public int hashCode() {
        return reference.hashCode();
    }


}
