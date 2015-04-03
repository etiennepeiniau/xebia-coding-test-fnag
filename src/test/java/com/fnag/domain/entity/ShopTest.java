package com.fnag.domain.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest {

    @Test
    public void should_shops_with_same_name_be_equal() throws Exception {
        // given
        Shop shop = new Shop();
        shop.setName("NAME");
        Shop sameNameShop = new Shop();
        sameNameShop.setName("NAME");
        // then
        assertThat(shop).isEqualTo(sameNameShop);
    }

    @Test
    public void should_shops_with_different_names_not_be_equal() throws Exception {
        // given
        Shop shop = new Shop();
        shop.setName("NAME");
        Shop anotherNameShop = new Shop();
        anotherNameShop.setName("ANOTHER_NAME");
        // then
        assertThat(shop).isNotEqualTo(anotherNameShop);
    }

    @Test
    public void should_shops_with_same_name_have_same_hash_code() throws Exception {
        // given
        Shop shop = new Shop();
        shop.setName("NAME");
        Shop sameNameShop = new Shop();
        sameNameShop.setName("NAME");
        // then
        assertThat(shop.hashCode()).isEqualTo(sameNameShop.hashCode());
    }

    @Test
    public void should_shops_with_different_names_have_different_hash_codes() throws Exception {
        // given
        Shop shop = new Shop();
        shop.setName("NAME");
        Shop anotherNameShop = new Shop();
        anotherNameShop.setName("ANOTHER_NAME");
        // then
        assertThat(shop.hashCode()).isNotEqualTo(anotherNameShop.hashCode());
    }

}
