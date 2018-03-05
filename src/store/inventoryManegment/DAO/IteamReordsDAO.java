package store.inventoryManegment.DAO;
import java.math.BigDecimal;
import java.util.Map;

import store.inventoryManegment.Models.*;

public interface IteamReordsDAO {
	
public void create(String itemName, BigDecimal costPrice, BigDecimal sellingPrice); 
public void delete(String itemName);
public void updateBuy(String itemName, Integer quantity);
public void updateSell(String itemName, Integer quantity);
public void updateSellPrice(String itemName, BigDecimal sellingPrice);
public void report();
public void calcProfit(Map<String, BigDecimal> itemDtls, Integer qt);
}
