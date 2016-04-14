import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;


/**
 * Created by Rene on 14.04.2016.
 */


public class mainClass implements Runnable{
    Stock stock = YahooFinance.get("^FTSE");
    BigDecimal oldprice = BigDecimal.valueOf(0.0);

    public mainClass() throws IOException {
    }

    public static void main(String args[]) throws IOException {
        new Thread(new mainClass()).start();
    }

    public void run() {
        while(true) {
            BigDecimal price = stock.getQuote().getPrice();

            if(price != oldprice){
                System.out.println(price);
            }

            oldprice = price;
        }
    }
}
