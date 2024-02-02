package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StockScraper {

    public static void main(String[] args) {
        final String URL = "https://www.biznesradar.pl/gielda/akcje_gpw";

        try {
            Document doc = Jsoup.connect(URL).get();

            Elements items = doc.select(".qTableFull tr");

            int i = 0;
            for (Element item : items) {
                if (!skipElement(item)) {
                    i++;
                    String name = item.select("a").text();
                    String time = item.select(".q_ch_date").attr("datetime");
                    String close = item.select(".q_ch_act").text();
                    String closePct = item.select(".q_ch_per").text();
                    System.out.printf("%d %s %s %s %s\n", i, name, time, close, closePct);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean skipElement(Element element) {
        if (!element.select("th.d_col_shortName").isEmpty()) return true;
        if (!element.select(".ad").isEmpty()) return true;
        return false;
    }
}
