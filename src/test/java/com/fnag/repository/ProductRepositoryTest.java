package com.fnag.repository;

import com.fnag.FnagResultAnalyser;
import com.fnag.domain.entity.Product;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FnagResultAnalyser.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void save_products() {
        productRepository.save(new Product("PRODUCT", BigDecimal.ONE, "DESCRIPTION"));
    }

    @Test
    public void should_find_by_name() {
        // when
        Product product = productRepository.findByReference("PRODUCT");
        // then
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(product).isNotNull();
        soft.assertThat(product.getReference()).isNotNull().isEqualTo("PRODUCT");
        soft.assertAll();
    }

    @Test
    public void should_not_find_by_name() {
        // when
        Product product = productRepository.findByReference("NOT_PRODUCT");
        // then
        assertThat(product).isNull();
    }

    @Test
    public void should_count_by_name() {
        // when
        long count = productRepository.countByReference("PRODUCT");
        // then
        assertThat(count).isEqualTo(1L);
    }

    @Test
    public void should_not_count_by_name() {
        // when
        long count = productRepository.countByReference("NOT_PRODUCT");
        // then
        assertThat(count).isEqualTo(0L);
    }

    @After
    public void delete_all() {
        productRepository.deleteAll();
    }

}
