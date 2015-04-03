package com.fnag.service;

import com.fnag.domain.top.TopSale;
import com.fnag.domain.top.TopSeller;

import java.util.List;

/**
 * {@code GlobalResultService} is used to find the top sales and the top sellers
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
public interface GlobalResultService {

    /**
     * Find the top sales
     *
     * @return a {@link java.util.List} of {@link com.fnag.domain.top.TopSale}
     */
    List<TopSale> findTopSales();

    /**
     * Find the top sellers
     *
     * @return a {@link java.util.List} of {@link com.fnag.domain.top.TopSeller}
     */
    List<TopSeller> findTopSellers();

}
