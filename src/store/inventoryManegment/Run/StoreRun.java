package store.inventoryManegment.Run;
import java.math.BigDecimal;
import java.util.Scanner;

import store.inventoryManegment.DAO.*;

public class StoreRun {

	public static void main(String[] args) {
		IteamRecordsDAOImpl  run = new IteamRecordsDAOImpl();
	
		System.out.println("--------------------Inventory Management------------------");
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if(input!=null && input.equalsIgnoreCase("exit")) {
				System.exit(0);
			} else {
				String[] cmd= input.split(" ");
				if(cmd.length>0) {
					try{
						if(cmd[0]!=null && cmd[0].equals("create")) {
							BigDecimal costPrice= new BigDecimal(cmd[2]);
							BigDecimal sellingPrice= new BigDecimal(cmd[3]);
							run.create(cmd[1], costPrice, sellingPrice);
						}
						else if(cmd[0]!=null && cmd[0].equals("updateBuy")) {
							Integer qt= Integer.valueOf(cmd[2]);
							run.updateBuy(cmd[1], qt);
						}
						else if(cmd[0]!=null && cmd[0].equals("updateSell")) {
							Integer qt= Integer.valueOf(cmd[2]);
							run.updateSell(cmd[1], qt);
						}
						else if(cmd[0]!=null && cmd[0].equals("delete")) {
							run.delete(cmd[1]);
						}
						else if(cmd[0]!=null && cmd[0].equals("report")) {
							run.report();
						}
						else if(cmd[0]!=null && cmd[0].equals("updateSellPrice")) {
							BigDecimal sellingPrice= new BigDecimal(cmd[2]);
							run.updateSellPrice(cmd[1], sellingPrice);
						}
						else {
							System.out.println("Please re-enter a valid command...");
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						//e.printStackTrace();
						System.out.println("Please re-enter a valid command...");
					}
				}
			}
		}
	}
}
