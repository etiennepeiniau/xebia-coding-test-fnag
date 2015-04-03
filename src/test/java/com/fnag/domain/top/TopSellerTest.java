package com.fnag.domain.top;

import com.fnag.domain.entity.Seller;
import com.fnag.domain.entity.Shop;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TopSellerTest {

    @Test
    public void should_have_correctly_formatted_to_string() {
        // given
        Shop shop = new Shop();
        shop.setName("SHOP_NAME");
        Seller seller = new Seller();
        seller.setName("SELLER_NAME");
        seller.setShop(shop);
        TopSeller topSeller = new TopSeller(seller, BigDecimal.ONE);
        // then
        assertThat(TopSeller.PREFIX +
                TopSeller.SEPARATOR + shop.getName() +
                TopSeller.SEPARATOR + seller.getName() +
                TopSeller.SEPARATOR + BigDecimal.ONE);
    }

}
