package com.fnag.batch.step;

import com.fnag.domain.entity.Product;
import com.fnag.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * {@code ProductStep} reads the tokens of a product line and save the new {@code Product} into {@link com.fnag.repository.ProductRepository}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Step
public class ProductStep implements LineStep {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean validate(String[] tokens) {
        if (tokens.length >= 3) {
            try {
                new BigDecimal(tokens[1]);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void handle(String[] tokens) {
        String reference = tokens[0];
        if (productRepository.countByReference(reference) == 0) {
            BigDecimal price = new BigDecimal(tokens[1]);
            String description = tokens[2];
            productRepository.save(new Product(reference, price, description));
        }
    }

}
