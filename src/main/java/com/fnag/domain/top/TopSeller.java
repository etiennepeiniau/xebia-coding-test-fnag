package com.fnag.domain.top;

import com.fnag.domain.entity.Seller;

import java.math.BigDecimal;

/**
 * A {@code TopSeller} is the most profitable {@link com.fnag.domain.entity.Seller}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public class TopSeller {

    public static final String PREFIX = "TOPSELLER";
    public static final String SEPARATOR = "|";

    private Seller seller;
    private BigDecimal total;

    public TopSeller(Seller seller, BigDecimal total) {
        this.seller = seller;
        this.total = total;
    }

    public Seller getSeller() {
        return seller;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String toString() {
        return PREFIX + SEPARATOR + seller.getShop().getName() + SEPARATOR + seller.getName() + SEPARATOR + total;
    }

}
