package fr.draftman.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import fr.draftman.Illusion;

public class Spawner implements Listener {
	
	@EventHandler
	public void onSpawnerBreak(BlockBreakEvent event){
		Block b = event.getBlock();
		
		Player p = event.getPlayer();
		
		ItemStack tool = p.getInventory().getItemInHand();
		
		if(b.getType() != Material.MOB_SPAWNER){
			return;
		}
		if(tool.getType() != Material.DIAMOND_PICKAXE){
			return;
		}
		if(tool.getDurability() < 20){
			p.sendMessage(Illusion.getInstance().getGamePrefix()+"§cTu n'as pas assez de Durabilité sur ta Pioche !");
			return;
		}
		MaterialData bd = b.getState().getData();
		ItemStack bs = new ItemStack(Material.MOB_SPAWNER);
		bs.setData(bd);
		p.getInventory().addItem(bs);
		tool.setDurability((short) (tool.getDurability() - 20));
		
	}
}
