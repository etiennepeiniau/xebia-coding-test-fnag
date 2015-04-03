package com.fnag.domain.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleTest {

    @Test
    public void should_sales_with_same_id_be_equal() throws Exception {
        // given
        Sale sale = new Sale();
        sale.setId(1L);
        Sale sameIdSale = new Sale();
        sameIdSale.setId(1L);
        // then
        assertThat(sale).isEqualTo(sameIdSale);
    }

    @Test
    public void should_sales_with_different_ids_not_be_equal() throws Exception {
        // given
        Sale sale = new Sale();
        sale.setId(1L);
        Sale anotherIdSale = new Sale();
        anotherIdSale.setId(2L);
        // then
        assertThat(sale).isNotEqualTo(anotherIdSale);
    }

    @Test
    public void should_sales_with_same_id_have_same_hash_code() throws Exception {
        // given
        Sale sale = new Sale();
        sale.setId(1L);
        Sale sameIdSale = new Sale();
        sameIdSale.setId(1L);
        // then
        assertThat(sale.hashCode()).isEqualTo(sameIdSale.hashCode());
    }

    @Test
    public void should_sales_with_different_ids_have_different_hash_codes() throws Exception {
        // given
        Sale sale = new Sale();
        sale.setId(1L);
        Sale anotherIdSale = new Sale();
        anotherIdSale.setId(2L);
        // then
        assertThat(sale.hashCode()).isNotEqualTo(anotherIdSale.hashCode());
    }


}
