package com.fnag.service;

import com.fnag.domain.top.TopSale;
import com.fnag.domain.top.TopSeller;
import com.fnag.repository.SaleRepository;
import org.springframework.context.annotation.Profile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * SqlGlobalResultService find the top sales and top sellers using SQL
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Profile("sql")
public class SqlGlobalResultService implements GlobalResultService {

    private SaleRepository saleRepository;

    public SqlGlobalResultService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<TopSale> findTopSales() {
        throw new NotImplementedException();
    }

    @Override
    public List<TopSeller> findTopSellers() {
        throw new NotImplementedException();
    }

}
