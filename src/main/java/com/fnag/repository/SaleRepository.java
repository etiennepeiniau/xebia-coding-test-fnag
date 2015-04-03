package com.fnag.repository;

import com.fnag.domain.entity.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository of {@link com.fnag.domain.entity.Sale}
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Repository
@Transactional(readOnly = true)
public interface SaleRepository extends CrudRepository<Sale, Long> {
}
