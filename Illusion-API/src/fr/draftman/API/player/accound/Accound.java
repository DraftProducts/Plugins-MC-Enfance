package fr.draftman.API.player.accound;

import java.util.UUID;

public class Accound {
	
	public UUID uuid;
	public Currency currency;
	
	public Accound(UUID uuid, Currency currency){
		this.uuid = uuid;
		this.currency = currency; 
		
	}
	
	public Float getMoney(){
		float money = 0.0f;
		
		return money;
	}
	
	public void setMoney(float money){
		float newMoney = getMoney() + money;
	}
}
