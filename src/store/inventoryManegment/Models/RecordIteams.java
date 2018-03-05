package store.inventoryManegment.Models;

import java.math.BigDecimal;


public class RecordIteams {
	
private String itemName;
private int quantity;
private BigDecimal costPrice;
private BigDecimal sellingPrice;

public RecordIteams(String itemName, int quantity, BigDecimal costPrice, BigDecimal sellingPrice) {
	this.itemName = itemName;
	this.quantity = quantity;
	this.costPrice = costPrice;
	this.sellingPrice = sellingPrice;
}


public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public BigDecimal getCostPrice() {
	return costPrice;
}
public void setCostPrice(BigDecimal costPrice) {
	this.costPrice = costPrice;
}
public BigDecimal getSellingPrice() {
	return sellingPrice;
}
public void setSellingPrice(BigDecimal sellingPrice) {
	this.sellingPrice = sellingPrice;
}
}
