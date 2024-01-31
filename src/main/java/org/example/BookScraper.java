package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple tool to scrap the webpage in the search of books and their prices.
 *
 */
public class BookScraper
{
    public static void main( String[] args )
    {
        final String URL = "https://books.toscrape.com/";

        try {
            Document document = Jsoup.connect(URL).get();
            Elements books = document.select(".product_pod");

            System.out.println("\nPage: " + URL);
            System.out.println("===".repeat(30));
            for (Element book : books) {
                String title = book.select("h3 > a").text();
                String price = book.select(".price_color").text();

                System.out.println(title + " - " + price);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
