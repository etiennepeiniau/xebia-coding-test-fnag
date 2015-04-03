package com.fnag;

import com.fnag.batch.reader.ResultReader;
import com.fnag.domain.top.TopSale;
import com.fnag.domain.top.TopSeller;
import com.fnag.service.GlobalResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * {@code FnagCommandLineRunner} orchestrates {@link com.fnag.batch.reader.ResultReader} and {@link com.fnag.service.GlobalResultService}
 * to find the top sales and the top sellers.
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@Component
@Profile("runner")
public class FnagCommandLineRunner implements CommandLineRunner {

    public static final Logger logger = LoggerFactory.getLogger(FnagCommandLineRunner.class);

    @Autowired
    private GlobalResultService globalResultService;

    @Autowired
    private ResultReader resultReader;

    @Value("${resultPath}")
    private String path;

    public void run(String... args) throws Exception {
        // read the file
        List<String> lines = Files.readAllLines(Paths.get(path));
        resultReader.read(lines);
        // print the result
        List<TopSale> topSales = globalResultService.findTopSales();
        topSales.forEach((topSale) -> logger.info(topSale.toString()));
        List<TopSeller> topSellers = globalResultService.findTopSellers();
        topSellers.forEach((topSeller) -> logger.info(topSeller.toString()));
    }

}
