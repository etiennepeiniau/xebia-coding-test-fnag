package com.fnag.batch.step;

import com.fnag.domain.entity.Seller;
import com.fnag.domain.entity.Shop;
import com.fnag.repository.SellerRepository;
import com.fnag.repository.ShopRepository;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SellerStepTest {

    @InjectMocks
    private SellerStep sellerStep;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private ShopRepository shopRepository;

    @Captor
    private ArgumentCaptor<Seller> sellerArgumentCaptor;

    @Test
    public void should_validate() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(shopRepository.countByName("SHOP")).willReturn(1L);
        // then
        assertThat(sellerStep.validate(tokens)).isTrue();
    }

    @Test
    public void should_not_validate_when_data_is_missing() {
        // given
        String[] tokens = {"SHOP"};
        // then
        assertThat(sellerStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_not_validate_when_shop_is_not_found() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(shopRepository.countByName("SHOP")).willReturn(0L);
        // then
        assertThat(sellerStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_save_a_new_seller() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(shopRepository.findByName("SHOP")).willReturn(any(Shop.class));
        given(sellerRepository.countByName("SELLER")).willReturn(0L);
        // when
        sellerStep.handle(tokens);
        // then
        verify(sellerRepository).countByName("SELLER");
        verify(sellerRepository).save(new Seller("SELLER", new Shop()));
    }

    @Test
    public void should_save_a_new_seller_with_returned_shop() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        Shop shop = new Shop();
        given(shopRepository.findByName("SHOP")).willReturn(shop);
        given(sellerRepository.findByName("SELLER")).willReturn(any(Seller.class));
        // when
        sellerStep.handle(tokens);
        // then
        verify(sellerRepository).save(sellerArgumentCaptor.capture());
        assertThat(sellerArgumentCaptor.getValue().getShop() == shop).isTrue(); // testing reference
    }

    @Test
    public void should_not_save_an_existing_seller() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(sellerRepository.countByName("SELLER")).willReturn(1L);
        // when
        sellerStep.handle(tokens);
        // then
        verify(sellerRepository).countByName("SELLER");
        verify(sellerRepository, never()).save(any(Seller.class));
    }

}
