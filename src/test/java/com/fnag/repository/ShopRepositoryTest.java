package com.fnag.repository;

import com.fnag.FnagResultAnalyser;
import com.fnag.domain.entity.Shop;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FnagResultAnalyser.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @Before
    public void save_shops() {
        shopRepository.save(new Shop("SHOP"));
    }

    @Test
    public void should_find_by_name() {
        // when
        Shop shop = shopRepository.findByName("SHOP");
        // then
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(shop).isNotNull();
        soft.assertThat(shop.getName()).isNotNull().isEqualTo("SHOP");
        soft.assertAll();
    }

    @Test
    public void should_not_find_by_name() {
        // when
        Shop shop = shopRepository.findByName("NOT_SHOP");
        // then
        assertThat(shop).isNull();
    }

    @Test
    public void should_count_by_name() {
        // when
        long count = shopRepository.countByName("SHOP");
        // then
        assertThat(count).isEqualTo(1L);
    }

    @Test
    public void should_not_count_by_name() {
        // when
        long count = shopRepository.countByName("NOT_SHOP");
        // then
        assertThat(count).isEqualTo(0L);
    }

    @After
    public void delete_all() {
        shopRepository.deleteAll();
    }

}
