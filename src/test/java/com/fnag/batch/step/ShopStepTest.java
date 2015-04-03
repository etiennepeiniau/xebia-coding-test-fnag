package com.fnag.batch.step;

import com.fnag.domain.entity.Shop;
import com.fnag.repository.ShopRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShopStepTest {

    @InjectMocks
    private ShopStep shopStep;

    @Mock
    private ShopRepository shopRepository;

    @Test
    public void should_validate() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        // then
        assertThat(shopStep.validate(tokens)).isTrue();
    }

    @Test
    public void should_not_validate_when_data_is_missing() {
        // given
        String[] tokens = {};
        // then
        assertThat(shopStep.validate(tokens)).isFalse();
    }

    @Test
    public void should_save_a_new_shop() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(shopRepository.countByName("SHOP")).willReturn(0L);
        // when
        shopStep.handle(tokens);
        // then
        verify(shopRepository).countByName("SHOP");
        verify(shopRepository).save(new Shop("SHOP"));
    }

    @Test
    public void should_not_save_an_existing_shop() {
        // given
        String[] tokens = {"SHOP", "SELLER", "PRODUCT", "100"};
        given(shopRepository.countByName("SHOP")).willReturn(1L);
        // when
        shopStep.handle(tokens);
        // then
        verify(shopRepository).countByName("SHOP");
        verify(shopRepository, never()).save(any(Shop.class));
    }

}
