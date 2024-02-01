package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StockScraper {

    public static void main(String[] args) {
        final String URL = "https://www.money.pl/gielda/indeksy_gpw/";

        try {
            Document doc = Jsoup.connect(URL).get();

            Elements elements = doc.select("div .rt-tr-group");
            int i = 0;
            for (Element el : elements) {
                i++;
                String indexName = el.select("div > a").text();
                String indexClose = el.select("div:eq(1)").text();
                System.out.printf("%d \t%s \t%s \n", i, indexName, indexClose);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
