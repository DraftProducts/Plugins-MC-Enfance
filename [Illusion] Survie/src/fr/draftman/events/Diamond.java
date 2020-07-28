package fr.draftman.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Diamond implements Listener {
	public void onBreak(BlockBreakEvent event){
		Block b = event.getBlock();
        if (b.getType().equals(Material.DIAMOND_ORE)){
            ItemStack CBlock = new ItemStack(Material.DIAMOND, 2,(byte)1);
            b.getDrops().clear();
            b.getDrops().add(CBlock);
        }
	}
}
