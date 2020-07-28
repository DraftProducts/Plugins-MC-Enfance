package fr.draftman.API.player.accound;

public class Currency {
	
	ILLUCREDIT(1000.0f, "IlluCoins");
	ILLUCOINS(0.0f, "IlluCrédits");
	
	public float balance;
	public String name;
	
	private Currency(float balance, String name){
		this.balance = balance;
		this.name = name;
		
	}
	
	public float getBalance(){
		return balance;
	}
	
	public String getName(){
		return name;
	}

}
