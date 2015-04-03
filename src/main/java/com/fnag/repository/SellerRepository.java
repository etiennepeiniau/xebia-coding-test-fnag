package com.fnag.repository;

import com.fnag.domain.entity.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository of {@link com.fnag.domain.entity.Seller}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Repository
@Transactional(readOnly = true)
public interface SellerRepository extends CrudRepository<Seller, String> {

    /**
     * Count {@link com.fnag.domain.entity.Seller} by name
     *
     * @param name the {@link com.fnag.domain.entity.Seller} name
     * @return the number of {@link com.fnag.domain.entity.Seller} matching the name (0 or 1)
     */
    Long countByName(String name);

    /**
     * Find {@link com.fnag.domain.entity.Seller} by name
     *
     * @param name the {@link com.fnag.domain.entity.Seller} name
     * @return a {@link com.fnag.domain.entity.Seller} matching the name or {@code null} if nothing matches
     */
    Seller findByName(String name);

}

