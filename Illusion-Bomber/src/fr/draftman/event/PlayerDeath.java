package fr.draftman.event;

import java.util.Arrays;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.draftman.Bomber;
import fr.draftman.Utils.GameUtils;

public class PlayerDeath implements Listener {
	@EventHandler
    public void onDeathPlayer(PlayerDeathEvent e) {
        Player p = e.getEntity();
        
        String message = e.getDeathMessage();
        Player ep = e.getEntity().getPlayer();
        if(message.contains("died")){
            SpaceItems spi = new SpaceItems();
            if(spi.raison.get(ep.getName()) == 1){
              e.setDeathMessage(ep.getName()+" a été explosé par une bombe lancé par "+ spi.hnkill.get(ep.getName()));
            }
            
            if(spi.raison.get(ep.getName()) == 2){
              e.setDeathMessage(ep.getName()+" a été explosé par une flèche lancé par "+ spi.hmkill.get(ep.getName()));
            }
        }else if(message.contains("shot") || message.contains("killed") || message.contains("pummeled") || message.contains("died")){
        	SpaceItems spi = new SpaceItems();
        	e.setDeathMessage(ep.getName()+" a été explosé par une flèche lancé par "+ spi.hmkill.get(ep.getName()));
        }
        
        Bomber.players.remove((Object)p);
        
        p.getInventory().clear();
        p.getEquipment().clear();
        p.setGameMode(GameMode.SPECTATOR);
        
        ItemStack hub = new ItemStack(Material.BED);
		ItemMeta hubM = hub.getItemMeta();
		hubM.setDisplayName("§6Hub");
		hubM.setLore(Arrays.asList(" ",
				"§7Retourner au hub du server... !",
				" "));
		hub.setItemMeta(hubM);
		
		ItemStack rejouer = new ItemStack(Material.NETHER_STAR);
		ItemMeta rejouerM = hub.getItemMeta();
		rejouerM.setDisplayName("§6Rejouer !");
		rejouerM.setLore(Arrays.asList(" ",
				"§7Clic ici pour rejouer a ce mini-jeux... !",
				" "));
		rejouer.setItemMeta(rejouerM);
		
		p.getInventory().setItem(4, rejouer);
		p.getInventory().setItem(8, hub);
        if (GameUtils.isWinner()) {
           Bomber.end();
        }
        
        
    }
}
