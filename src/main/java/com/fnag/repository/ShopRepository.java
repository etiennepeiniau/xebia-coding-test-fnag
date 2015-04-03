package com.fnag.repository;

import com.fnag.domain.entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository of {@link com.fnag.domain.entity.Shop}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Repository
@Transactional(readOnly = true)
public interface ShopRepository extends CrudRepository<Shop, String> {

    /**
     * Count {@link com.fnag.domain.entity.Shop} by name
     *
     * @param name the {@link com.fnag.domain.entity.Shop} name
     * @return the number of {@link com.fnag.domain.entity.Shop} matching the name (0 or 1)
     */
    Long countByName(String name);

    /**
     * Find {@link com.fnag.domain.entity.Shop} by name
     *
     * @param name the {@link com.fnag.domain.entity.Shop} name
     * @return a {@link com.fnag.domain.entity.Shop} matching the name or {@code null} if nothing matches
     */
    Shop findByName(String name);

}
