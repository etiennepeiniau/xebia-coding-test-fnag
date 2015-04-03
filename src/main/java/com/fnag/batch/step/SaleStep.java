package com.fnag.batch.step;

import com.fnag.domain.entity.Product;
import com.fnag.domain.entity.Sale;
import com.fnag.domain.entity.Seller;
import com.fnag.repository.ProductRepository;
import com.fnag.repository.SaleRepository;
import com.fnag.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@code SaleStep} reads the tokens of a sale line and save the new {@code Sale} into {@link com.fnag.repository.SaleRepository}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Step
public class SaleStep implements LineStep {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean validate(String[] tokens) {
        if (tokens.length < 4) {
            return false;
        } else if (sellerRepository.countByName(tokens[1]) == 0) {
            return false;
        } else if (productRepository.countByReference(tokens[2]) == 0) {
            return false;
        } else {
            try {
                Integer.valueOf(tokens[3]);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
    }

    @Override
    public void handle(String[] tokens) {
        Seller seller = sellerRepository.findByName(tokens[1]);
        Product product = productRepository.findByReference(tokens[2]);
        Integer quantity = Integer.valueOf(tokens[3]);
        saleRepository.save(new Sale(seller, product, quantity));
    }

}
