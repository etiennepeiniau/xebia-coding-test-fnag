package com.fnag.batch.step;

import com.fnag.domain.entity.Shop;
import com.fnag.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@code ShopStep} reads the tokens of a sale line and save the new {@code Shop} into {@link com.fnag.repository.ShopRepository}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Step
public class ShopStep implements LineStep {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public boolean validate(String[] tokens) {
        return tokens.length > 0;
    }

    @Override
    public void handle(String[] tokens) {
        String name = tokens[0];
        if (shopRepository.countByName(name) == 0) {
            shopRepository.save(new Shop(name));
        }
    }

}
