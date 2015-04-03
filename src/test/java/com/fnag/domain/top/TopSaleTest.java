package com.fnag.domain.top;

import com.fnag.domain.entity.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TopSaleTest {

    @Test
    public void should_have_correctly_formatted_to_string() {
        // given
        Product product = new Product();
        product.setReference("REFERENCE");
        product.setDescription("DESCRIPTION");
        TopSale topSale = new TopSale(product, 1L);
        // then
        assertThat(topSale.toString()).isEqualTo(TopSale.PREFIX +
                TopSale.SEPARATOR + product.getReference() +
                TopSale.SEPARATOR + product.getDescription() +
                TopSale.SEPARATOR + 1L);
    }

}
