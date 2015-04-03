package com.fnag.service;

import com.fnag.domain.entity.Sale;
import com.fnag.domain.top.TopSale;
import com.fnag.domain.top.TopSeller;
import com.fnag.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * StreamGlobalResultService find the top sales and top sellers using the Stream API of Java 8
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Service
@Profile("!sql")
public class StreamGlobalResultService implements GlobalResultService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<TopSale> findTopSales() {
        Map.Entry<Long, List<TopSale>> result = StreamSupport.stream(saleRepository.findAll().spliterator(), false)
                // group Product by reference and sum the quantity sold
                .collect(Collectors.groupingBy(Sale::getProduct, Collectors.summingLong(Sale::getQuantity)))
                        // add each results inside a TopSale
                .entrySet().stream().map(e -> new TopSale(e.getKey(), e.getValue()))
                        // group TopSale by count, store them inside a TreeMap to sort by count and collect them inside lists
                .collect(Collectors.groupingBy(TopSale::getCount, TreeMap::new, Collectors.toList()))
                        // return the last entry of the TreeMap which contains a list of TopSale
                .lastEntry();
        // return the result value if found
        if (result != null) {
            return result.getValue();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<TopSeller> findTopSellers() {
        Map.Entry<BigDecimal, List<TopSeller>> result = StreamSupport.stream(saleRepository.findAll().spliterator(), false)
                // group Seller by name and sum the total sold
                .collect(Collectors.groupingBy(Sale::getSeller, totalCollector()))
                        // add each results inside a TopSeller
                .entrySet().stream().map(e -> new TopSeller(e.getKey(), e.getValue()))
                        // group TopSeller by total, store them inside a TreeMap to sort by count and collect them inside lists
                .collect(Collectors.groupingBy(TopSeller::getTotal, TreeMap::new, Collectors.toList()))
                        // return the last entry of the TreeMap which contains a list of TopSeller
                .lastEntry();
        // return the result value if found
        if (result != null) {
            return result.getValue();
        } else {
            return new ArrayList<>();
        }
    }

    private static Collector<Sale, BigDecimal[], BigDecimal> totalCollector() {
        return Collector.of(
                // the accumulator supplier create an array of BigDecimal of size 1 containing BigDecimal.ZERO
                () -> {
                    BigDecimal[] a = new BigDecimal[1];
                    a[0] = BigDecimal.ZERO;
                    return a;
                },
                // the accumulator does the sum of the current total and the amount of the next sale (price * quantity)
                (BigDecimal[] a, Sale sale) -> {
                    a[0] = a[0].add(sale.getProduct().getPrice().multiply(new BigDecimal(sale.getQuantity())));
                },
                // the combiner is just a sum of two BigDecimal
                (a, b) -> {
                    a[0] = a[0].add(b[0]);
                    return a;
                },
                // the finisher extract the BigDecimal from the array
                a -> a[0]);
    }

}
