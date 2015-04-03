package com.fnag.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A {@code Sale} of a quantity of a {@link com.fnag.domain.entity.Product} is performed by {@link com.fnag.domain.entity.Seller}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Entity
public class Sale {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Seller seller;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private Product product;
    @Column(nullable = false)
    private long quantity;

    public Sale() {
    }

    public Sale(Seller seller, Product product, Integer quantity) {
        this.seller = seller;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id.equals(sale.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
