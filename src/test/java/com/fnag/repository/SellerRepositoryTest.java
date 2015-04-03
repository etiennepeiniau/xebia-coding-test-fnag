package com.fnag.repository;

import com.fnag.FnagResultAnalyser;
import com.fnag.domain.entity.Seller;
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
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Before
    public void save_sellers() {
        Shop shop = new Shop("SHOP");
        shopRepository.save(shop);
        Seller seller = new Seller("SELLER", shop);
        sellerRepository.save(seller);
    }

    @Test
    public void should_find_by_name() {
        // when
        Seller seller = sellerRepository.findByName("SELLER");
        // then
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(seller).isNotNull();
        soft.assertThat(seller.getName()).isNotNull().isEqualTo("SELLER");
        soft.assertAll();
    }

    @Test
    public void should_not_find_by_name() {
        // when
        Seller seller = sellerRepository.findByName("NOT_SELLER");
        // then
        assertThat(seller).isNull();
    }

    @Test
    public void should_count_by_name() {
        // when
        long count = sellerRepository.countByName("SELLER");
        // then
        assertThat(count).isEqualTo(1L);
    }

    @Test
    public void should_not_count_by_name() {
        // when
        long count = sellerRepository.countByName("NOT_SELLER");
        // then
        assertThat(count).isEqualTo(0L);
    }

    @After
    public void delete_all() {
        sellerRepository.deleteAll();
        shopRepository.deleteAll();
    }

}
