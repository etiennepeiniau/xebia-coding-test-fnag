package com.fnag.domain.top;

import com.fnag.domain.entity.Product;

/**
 * A {@code TopSale} is the most sold {@link com.fnag.domain.entity.Product}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public class TopSale {

    public static final String PREFIX = "TOPSALE";
    public static final String SEPARATOR = "|";

    private Product product;
    private Long count;

    public TopSale(Product product, Long count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public Long getCount() {
        return count;
    }

    public String toString() {
        return PREFIX + SEPARATOR + product.getReference() + SEPARATOR + product.getDescription() + SEPARATOR + count;
    }

}
