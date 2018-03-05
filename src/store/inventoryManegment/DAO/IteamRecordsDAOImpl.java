package store.inventoryManegment.DAO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class IteamRecordsDAOImpl implements IteamReordsDAO {
	Map<String, Map<String,BigDecimal>> itemsMap;
	BigDecimal profit=null;
	
	public IteamRecordsDAOImpl() {
	if(itemsMap==null)
		itemsMap= new TreeMap<String,Map<String,BigDecimal>>();
	profit= new BigDecimal("0");
}
	
	@Override
	public void create(String itemName, BigDecimal costPrice, BigDecimal sellingPrice) {
		// TODO Auto-generated method stub
		Map<String,BigDecimal> itemDtls= new HashMap<String,BigDecimal>();
		itemDtls.put("CP", costPrice);
		itemDtls.put("SP", sellingPrice);
		itemDtls.put("QT", BigDecimal.ZERO);
		itemsMap.put(itemName, itemDtls);
	}

	@Override
	public void delete(String itemName) {
		// TODO Auto-generated method stub
		if(itemsMap!=null)
			itemsMap.remove(itemName);
	}

	@Override
	public void updateBuy(String itemName, Integer quantity) {
		// TODO Auto-generated method stub
		Map<String,BigDecimal> itemDtls= itemsMap.get(itemName);
		if(itemDtls!=null) {
			BigDecimal qt= itemDtls.get("QT");
			qt= qt.add(BigDecimal.valueOf(quantity));
			itemDtls.put("QT", qt);
		} else {
			System.out.println(itemName+" does not exist in Inventory...");
			System.out.println("Please re-enter a valid item...");
		}
	}

	@Override
	public void updateSell(String itemName, Integer quantity) {
		// TODO Auto-generated method stub
		Map<String,BigDecimal> itemDtls= itemsMap.get(itemName);
		if(itemDtls!=null) {
			BigDecimal qt= itemDtls.get("QT");
			if(qt.compareTo(BigDecimal.ZERO)==0) {
				System.out.println("No "+itemName+"(s) available in inventory...");
				System.out.println("Please re-enter a valid command...");
				return;
			} 
			
			BigDecimal remqt= qt.subtract(BigDecimal.valueOf(quantity));
			
			if(remqt.compareTo(BigDecimal.ZERO)==-1) {
				System.out.println("Only "+qt.intValue()+" "+itemName+"(s) available in inventory...");
				System.out.println("Please re-enter a valid quantity...");
				return;
			} else {
				itemDtls.put("QT", remqt);
				calcProfit(itemDtls, quantity);
			}
		} else {
			System.out.println("Item does not exist in Inventory...");
			System.out.println("Please re-enter a valid item...");
		}
	}
	
@Override
	public void updateSellPrice(String itemName, BigDecimal sellingPrice) {
		// TODO Auto-generated method stub
	Map<String,BigDecimal> itemDtls= itemsMap.get(itemName);
	if(itemDtls!=null) {
		itemDtls.put("SP",sellingPrice);
	}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		System.out.println("Item name        Bought At        Sold At        AvailableQty     Value");
		System.out.println("----------       ----------       --------       ------------     ------ ");
		System.out.println();
		BigDecimal totVal= new BigDecimal("0");
		Iterator<String> it= itemsMap.keySet().iterator();
		while(it.hasNext()){
			String itemName= it.next();
			Map dtls= (HashMap) itemsMap.get(itemName);
			BigDecimal costPrice= (BigDecimal) dtls.get("CP");
			BigDecimal sellingPrice= (BigDecimal) dtls.get("SP");
			BigDecimal quantity= (BigDecimal) dtls.get("QT");
			BigDecimal val= quantity.multiply(costPrice);
			totVal= totVal.add(val);
			System.out.print(itemName);
			System.out.print("           ");
			System.out.print(dtls.get("CP"));
			System.out.print("           ");
			System.out.print(dtls.get("SP"));
			System.out.print("           ");
			System.out.print(dtls.get("QT"));
			System.out.print("           ");
			System.out.println(quantity.multiply(costPrice));
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Total value                                                  "+totVal);
		
		System.out.println("Profit since previous report: "+this.profit);
		
		this.profit= new BigDecimal("0");
	}

	@Override
	public void calcProfit(Map<String, BigDecimal> itemDtls, Integer qt) {
		// TODO Auto-generated method stub
		BigDecimal costPrice= itemDtls.get("CP");
		BigDecimal sellingPrice= itemDtls.get("SP");
		BigDecimal thisProfit= sellingPrice.subtract(costPrice).multiply(BigDecimal.valueOf(qt));		
		this.profit= this.profit.add(thisProfit);
	}

}
