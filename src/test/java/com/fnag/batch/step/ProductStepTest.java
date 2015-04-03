package com.fnag.batch.step;

import com.fnag.domain.entity.Product;
import com.fnag.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductStepTest {

    @InjectMocks
    private ProductStep productStep;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void should_validate() {
        // given
        String[] tokens = {"REFERENCE", "10.5", "DESCRIPTION"};
        // then
        assertThat(productStep.validate(tokens)).isTrue();
    }

    @Test
    public void should_not_validate_when_data_is_missing() {
        // given
        String[] tokens = {"REFERENCE", "10.5"};
        // then
        assertThat(productStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_not_validate_when_the_product_price_is_not_a_number() {
        // given
        String[] tokens = {"REFERENCE", "PRICE", "DESCRIPTION"};
        // then
        assertThat(productStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_save_a_new_product() {
        // given
        String[] tokens = {"REFERENCE", "10.5", "DESCRIPTION"};
        given(productRepository.countByReference("REFERENCE")).willReturn(0L);
        // when
        productStep.handle(tokens);
        // then
        verify(productRepository).countByReference("REFERENCE");
        verify(productRepository).save(new Product("REFERENCE", new BigDecimal("10.5"), "DESCRIPTION"));
    }

    @Test
    public void should_not_save_an_existing_product() {
        // given
        String[] tokens = {"REFERENCE", "10.5", "DESCRIPTION"};
        given(productRepository.countByReference("REFERENCE")).willReturn(1L);
        // when
        productStep.handle(tokens);
        // then
        verify(productRepository).countByReference("REFERENCE");
        verify(productRepository, never()).save(any(Product.class));
    }

}
