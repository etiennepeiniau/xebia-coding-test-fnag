package com.fnag.service;

import com.fnag.domain.entity.Product;
import com.fnag.domain.entity.Sale;
import com.fnag.domain.entity.Seller;
import com.fnag.domain.entity.Shop;
import com.fnag.domain.top.TopSale;
import com.fnag.domain.top.TopSeller;
import com.fnag.repository.SaleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class StreamGlobalResultServiceTest {

    // Products
    private static final Product PRODUCT = new Product("REFERENCE", BigDecimal.ONE, "DESCRIPTION");
    private static final Product FIRST_PRODUCT = new Product("FIRST_REFERENCE", BigDecimal.ONE, "DESCRIPTION");
    private static final Product SECOND_PRODUCT = new Product("SECOND_REFERENCE", BigDecimal.ONE, "DESCRIPTION");
    private static final Product THIRD_PRODUCT = new Product("THIRD_REFERENCE", BigDecimal.ONE, "DESCRIPTION");
    // Sellers
    private final static Seller SELLER = new Seller("SELLER", new Shop("SHOP"));
    private final static Seller FIRST_SELLER = new Seller("FIRST_SELLER", new Shop("FIRST_SHOP"));
    private final static Seller SECOND_SELLER = new Seller("SECOND_SELLER", new Shop("SECOND_SHOP"));
    private final static Seller THIRD_SELLER = new Seller("THIRD_SELLER", new Shop("THIRD_SHOP"));

    @InjectMocks
    private StreamGlobalResultService streamGlobalResultService;
    @Mock
    private SaleRepository saleRepository;

    @Test
    public void should_return_no_top_sale_when_no_sale_are_recorded() {
        // given
        given(saleRepository.findAll()).willReturn(new ArrayList<>());
        // when
        List<TopSale> topSales = streamGlobalResultService.findTopSales();
        // then
        assertThat(topSales).hasSize(0);
    }

    @Test
    public void should_return_the_unique_top_sale_if_ony_one_sale_is_recorded() {
        // given
        Sale sale = new Sale();
        sale.setProduct(PRODUCT);
        sale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(sale));
        // when
        List<TopSale> topSales = streamGlobalResultService.findTopSales();
        // then
        assertThat(topSales).hasSize(1)
                .extracting("product")
                .extracting("reference")
                .containsExactly("REFERENCE");
    }

    @Test
    public void should_return_the_top_sale_between_two_sales() {
        // given
        Sale firstSale = new Sale();
        firstSale.setProduct(FIRST_PRODUCT);
        firstSale.setQuantity(5);
        Sale secondSale = new Sale();
        secondSale.setProduct(SECOND_PRODUCT);
        secondSale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale));
        // when
        List<TopSale> topSales = streamGlobalResultService.findTopSales();
        // then
        assertThat(topSales).hasSize(1)
                .extracting("product")
                .extracting("reference")
                .containsExactly("SECOND_REFERENCE");
    }

    @Test
    public void should_return_two_top_sales_for_two_sales_with_same_quantity() {
        // given
        Sale firstSale = new Sale();
        firstSale.setProduct(FIRST_PRODUCT);
        firstSale.setQuantity(10);
        Sale secondSale = new Sale();
        secondSale.setProduct(SECOND_PRODUCT);
        secondSale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale));
        // when
        List<TopSale> topSales = streamGlobalResultService.findTopSales();
        // then
        assertThat(topSales).hasSize(2)
                .extracting("product")
                .extracting("reference")
                .containsExactly("FIRST_REFERENCE", "SECOND_REFERENCE");
    }

    @Test
    public void should_return_the_sum_of_two_sales_with_same_product_reference_as_the_top_sale() {
        // given
        Sale firstSale = new Sale();
        firstSale.setProduct(PRODUCT);
        firstSale.setQuantity(5);
        Sale secondSale = new Sale();
        secondSale.setProduct(PRODUCT);
        secondSale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale));
        // when
        List<TopSale> topSales = streamGlobalResultService.findTopSales();
        // then
        assertThat(topSales).hasSize(1)
                .extracting("count")
                .containsExactly(15L);
    }

    @Test
    public void should_return_the_top_sales_in_a_complex_scenario() {
        // given
        Sale firstSale = new Sale();
        firstSale.setProduct(FIRST_PRODUCT);
        firstSale.setQuantity(5);
        Sale secondSale = new Sale();
        secondSale.setProduct(SECOND_PRODUCT);
        secondSale.setQuantity(10);
        Sale thirdSale = new Sale();
        thirdSale.setProduct(FIRST_PRODUCT);
        thirdSale.setQuantity(10);
        Sale fourthSale = new Sale();
        fourthSale.setProduct(THIRD_PRODUCT);
        fourthSale.setQuantity(15);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale, thirdSale, fourthSale));
        // when
        List<TopSale> topSales = streamGlobalResultService.findTopSales();
        // then
        assertThat(topSales).hasSize(2)
                .extracting("product")
                .extracting("reference")
                .containsExactly("FIRST_REFERENCE", "THIRD_REFERENCE");
    }

    @Test
    public void should_return_no_top_seller_when_no_sale_are_recorded() {
        // given
        given(saleRepository.findAll()).willReturn(new ArrayList<>());
        // when
        List<TopSeller> topSellers = streamGlobalResultService.findTopSellers();
        // then
        assertThat(topSellers).hasSize(0);
    }

    @Test
    public void should_return_the_unique_top_seller_if_ony_one_sale_is_recorded() {
        // given
        Sale sale = new Sale();
        sale.setSeller(SELLER);
        sale.setProduct(PRODUCT);
        sale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(sale));
        // when
        List<TopSeller> topSellers = streamGlobalResultService.findTopSellers();
        // then
        assertThat(topSellers).hasSize(1)
                .extracting("seller")
                .extracting("name")
                .containsExactly("SELLER");
    }

    @Test
    public void should_return_the_top_seller_between_two_sales() {
        // given
        Sale firstSale = new Sale();
        firstSale.setSeller(new Seller("FIRST_SELLER", new Shop("FIRST_SHOP")));
        firstSale.setProduct(FIRST_PRODUCT);
        firstSale.setQuantity(5);
        Sale secondSale = new Sale();
        secondSale.setSeller(SECOND_SELLER);
        secondSale.setProduct(SECOND_PRODUCT);
        secondSale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale));
        // when
        List<TopSeller> topSellers = streamGlobalResultService.findTopSellers();
        // then
        assertThat(topSellers).hasSize(1)
                .extracting("seller")
                .extracting("name")
                .containsExactly("SECOND_SELLER");
    }

    @Test
    public void should_return_two_top_sellers_for_two_sales_with_same_total() {
        // given
        Sale firstSale = new Sale();
        firstSale.setSeller(FIRST_SELLER);
        firstSale.setProduct(FIRST_PRODUCT);
        firstSale.setQuantity(10);
        Sale secondSale = new Sale();
        secondSale.setSeller(SECOND_SELLER);
        secondSale.setProduct(SECOND_PRODUCT);
        secondSale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale));
        // when
        List<TopSeller> topSellers = streamGlobalResultService.findTopSellers();
        // then
        assertThat(topSellers).hasSize(2)
                .extracting("seller")
                .extracting("name")
                .containsExactly("SECOND_SELLER", "FIRST_SELLER");
    }

    @Test
    public void should_return_the_sum_of_two_sale_with_same_product_reference_as_the_top_seller() {
        // given
        Sale firstSale = new Sale();
        firstSale.setSeller(SELLER);
        firstSale.setProduct(PRODUCT);
        firstSale.setQuantity(5);
        Sale secondSale = new Sale();
        secondSale.setSeller(SELLER);
        secondSale.setProduct(PRODUCT);
        secondSale.setQuantity(10);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale));
        // when
        List<TopSeller> topSellers = streamGlobalResultService.findTopSellers();
        // then
        assertThat(topSellers).hasSize(1)
                .extracting("total")
                .containsExactly(new BigDecimal(15));
    }

    @Test
    public void should_return_the_top_sellers_in_a_complex_scenario() {
        // given
        Sale firstSale = new Sale();
        firstSale.setSeller(FIRST_SELLER);
        firstSale.setProduct(FIRST_PRODUCT);
        firstSale.setQuantity(5);
        Sale secondSale = new Sale();
        secondSale.setSeller(SECOND_SELLER);
        secondSale.setProduct(SECOND_PRODUCT);
        secondSale.setQuantity(10);
        Sale thirdSale = new Sale();
        thirdSale.setSeller(FIRST_SELLER);
        thirdSale.setProduct(FIRST_PRODUCT);
        thirdSale.setQuantity(10);
        Sale fourthSale = new Sale();
        fourthSale.setSeller(THIRD_SELLER);
        fourthSale.setProduct(THIRD_PRODUCT);
        fourthSale.setQuantity(15);
        given(saleRepository.findAll()).willReturn(Arrays.asList(firstSale, secondSale, thirdSale, fourthSale));
        // when
        List<TopSeller> topSellers = streamGlobalResultService.findTopSellers();
        // then
        assertThat(topSellers).hasSize(2)
                .extracting("seller")
                .extracting("name")
                .containsExactly("FIRST_SELLER", "THIRD_SELLER");
    }

}
