package com.fnag.batch.step;

import com.fnag.domain.entity.Product;
import com.fnag.domain.entity.Sale;
import com.fnag.domain.entity.Seller;
import com.fnag.repository.ProductRepository;
import com.fnag.repository.SaleRepository;
import com.fnag.repository.SellerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SaleStepTest {

    @InjectMocks
    private SaleStep saleStep;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SaleRepository saleRepository;

    @Captor
    private ArgumentCaptor<Sale> saleArgumentCaptor;

    @Test
    public void should_validate() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(sellerRepository.countByName("SELLER")).willReturn(1L);
        given(productRepository.countByReference("PRODUCT")).willReturn(1L);
        // then
        assertThat(saleStep.validate(tokens)).isTrue();
    }

    @Test
    public void should_not_validate_when_data_is_missing() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT"};
        // then
        assertThat(saleStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_not_validate_when_seller_is_not_found() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(sellerRepository.countByName("SELLER")).willReturn(0L);
        // then
        assertThat(saleStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_not_validate_when_product_is_not_found() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(sellerRepository.countByName("SELLER")).willReturn(1L);
        given(productRepository.countByReference("PRODUCT")).willReturn(0L);
        // then
        assertThat(saleStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_not_validate_when_the_quantity_is_not_a_number() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "QUANTITY"};
        given(sellerRepository.countByName("SELLER")).willReturn(1L);
        given(productRepository.countByReference("PRODUCT")).willReturn(1L);
        // then
        assertThat(saleStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_save_a_new_sale() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(sellerRepository.findByName("SELLER")).willReturn(any(Seller.class));
        given(productRepository.findByReference("PRODUCT")).willReturn(any(Product.class));
        // when
        saleStep.handle(tokens);
        // then
        verify(saleRepository).save(any(Sale.class));
    }

    @Test
    public void should_save_a_new_sale_with_returned_seller() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        Seller seller = new Seller();
        given(sellerRepository.findByName("SELLER")).willReturn(seller);
        given(productRepository.findByReference("PRODUCT")).willReturn(any(Product.class));
        // when
        saleStep.handle(tokens);
        // then
        verify(saleRepository).save(saleArgumentCaptor.capture());
        assertThat(saleArgumentCaptor.getValue().getSeller() == seller).isTrue(); // testing reference
    }

    @Test
    public void should_save_a_new_sale_with_returned_product() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(sellerRepository.findByName("SELLER")).willReturn(any(Seller.class));
        Product product = new Product();
        given(productRepository.findByReference("PRODUCT")).willReturn(product);
        // when
        saleStep.handle(tokens);
        // then
        verify(saleRepository).save(saleArgumentCaptor.capture());
        assertThat(saleArgumentCaptor.getValue().getProduct() == product).isTrue(); // testing reference
    }

    @Test
    public void should_save_a_new_sale_with_correct_quantity() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(sellerRepository.findByName("SELLER")).willReturn(any(Seller.class));
        given(productRepository.findByReference("PRODUCT")).willReturn(any(Product.class));
        // when
        saleStep.handle(tokens);
        // then
        verify(saleRepository).save(saleArgumentCaptor.capture());
        assertThat(saleArgumentCaptor.getValue().getQuantity()).isEqualTo(100);
    }

}
