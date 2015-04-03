package com.fnag.config;

import com.fnag.batch.reader.DefaultResultReader;
import com.fnag.batch.reader.ResultReader;
import com.fnag.batch.reader.LineReader;
import com.fnag.batch.reader.SteppedLineReader;
import com.fnag.batch.step.ProductStep;
import com.fnag.batch.step.SaleStep;
import com.fnag.batch.step.SellerStep;
import com.fnag.batch.step.ShopStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BatchConfig is the Spring configuration for the batch parts of the application
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Configuration
public class BatchConfig {

    @Bean
    LineReader productLineReader(ProductStep productStep) {
        return new SteppedLineReader(productStep);
    }

    @Bean
    LineReader saleLineReader(ShopStep shopStep, SellerStep sellerStep, SaleStep saleStep) {
        return new SteppedLineReader(shopStep, sellerStep, saleStep);
    }

    @Bean
    ResultReader resultLoader(LineReader productLineReader, LineReader saleLineReader) {
        return new DefaultResultReader(productLineReader, saleLineReader);
    }

}
