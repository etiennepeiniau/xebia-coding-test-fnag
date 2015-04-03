package com.fnag.batch.step;

import com.fnag.domain.entity.Seller;
import com.fnag.domain.entity.Shop;
import com.fnag.repository.SellerRepository;
import com.fnag.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@code SellerStep} reads the tokens of a sale line and save the new {@code Seller} into {@link com.fnag.repository.SellerRepository}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Step
public class SellerStep implements LineStep {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public boolean validate(String[] tokens) {
        if (tokens.length < 2) {
            return false;
        } else if (shopRepository.countByName(tokens[0]) == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void handle(String[] tokens) {
        String name = tokens[1];
        if (sellerRepository.countByName(name) == 0) {
            Shop shop = shopRepository.findByName(tokens[0]);
            sellerRepository.save(new Seller(name, shop));
        }
    }

}
