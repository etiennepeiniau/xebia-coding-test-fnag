package com.fnag.domain.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void should_products_with_same_reference_be_equal() throws Exception {
        // given
        Product product = new Product();
        product.setReference("REFERENCE");
        Product sameReferenceProduct = new Product();
        sameReferenceProduct.setReference("REFERENCE");
        // then
        assertThat(product).isEqualTo(sameReferenceProduct);
    }

    @Test
    public void should_products_with_different_references_not_be_equal() throws Exception {
        // given
        Product product = new Product();
        product.setReference("REFERENCE");
        Product anotherReferenceProduct = new Product();
        anotherReferenceProduct.setReference("ANOTHER_REFERENCE");
        // then
        assertThat(product).isNotEqualTo(anotherReferenceProduct);
    }

    @Test
    public void should_products_with_same_reference_have_same_hash_code() throws Exception {
        // given
        Product product = new Product();
        product.setReference("REFERENCE");
        Product sameReferenceProduct = new Product();
        sameReferenceProduct.setReference("REFERENCE");
        // then
        assertThat(product.hashCode()).isEqualTo(sameReferenceProduct.hashCode());
    }

    @Test
    public void should_products_with_different_references_have_different_hash_codes() throws Exception {
        // given
        Product product = new Product();
        product.setReference("REFERENCE");
        Product anotherReferenceProduct = new Product();
        anotherReferenceProduct.setReference("ANOTHER_REFERENCE");
        // then
        assertThat(product.hashCode()).isNotEqualTo(anotherReferenceProduct.hashCode());
    }

}
