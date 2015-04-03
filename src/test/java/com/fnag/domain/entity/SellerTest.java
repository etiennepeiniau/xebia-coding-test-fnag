package com.fnag.domain.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SellerTest {

    @Test
    public void should_sellers_with_same_name_be_equal() throws Exception {
        // given
        Seller seller = new Seller();
        seller.setName("NAME");
        Seller sameNameSeller = new Seller();
        sameNameSeller.setName("NAME");
        // then
        assertThat(seller).isEqualTo(sameNameSeller);
    }

    @Test
    public void should_sellers_with_different_names_not_be_equal() throws Exception {
        // given
        Seller seller = new Seller();
        seller.setName("NAME");
        Seller anotherNameSeller = new Seller();
        anotherNameSeller.setName("ANOTHER_NAME");
        // then
        assertThat(seller).isNotEqualTo(anotherNameSeller);
    }

    @Test
    public void should_sellers_with_same_name_have_same_hash_code() throws Exception {
        // given
        Seller seller = new Seller();
        seller.setName("NAME");
        Seller sameNameSeller = new Seller();
        sameNameSeller.setName("NAME");
        // then
        assertThat(seller.hashCode()).isEqualTo(sameNameSeller.hashCode());
    }

    @Test
    public void should_sellers_with_different_names_have_different_hash_codes() throws Exception {
        // given
        Seller seller = new Seller();
        seller.setName("NAME");
        Seller anotherNameSeller = new Seller();
        anotherNameSeller.setName("ANOTHER_NAME");
        // then
        assertThat(seller.hashCode()).isNotEqualTo(anotherNameSeller.hashCode());
    }

}
