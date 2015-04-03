package com.fnag.repository;

import com.fnag.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository of {@link com.fnag.domain.entity.Product}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * Count {@link com.fnag.domain.entity.Product} by reference
     *
     * @param reference the {@link com.fnag.domain.entity.Product} reference
     * @return the number of {@link com.fnag.domain.entity.Product} matching the reference (0 or 1)
     */
    Long countByReference(String reference);

    /**
     * Find {@link com.fnag.domain.entity.Product} by reference
     *
     * @param reference the {@link com.fnag.domain.entity.Product} reference
     * @return a {@link com.fnag.domain.entity.Product} matching the reference or {@code null} if nothing matches
     */
    Product findByReference(String reference);

}
