import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;


/**
 * Created by Rene on 14.04.2016.
 */


public class mainClass implements Runnable{
    Stock stock;

    public mainClass() throws IOException {
        stock = YahooFinance.get("^NYA");
    }

    public static void main(String args[]) throws IOException {
        new Thread(new mainClass()).start();
    }

    public void run() {
        while(true) {
            try {
                synchronized (this){
                    this.wait(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BigDecimal price = null;
            try {
                price = stock.getQuote(true).getPrice();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(price + "|" + stock.getQuote().getChange() + "(" + stock.getQuote().getChangeInPercent() + "%" + ")");
            System.out.println("-----------------------------");
        }
    }
}
