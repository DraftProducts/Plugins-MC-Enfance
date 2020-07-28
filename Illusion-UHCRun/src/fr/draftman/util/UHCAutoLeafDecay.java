package fr.draftman.util;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;

import fr.draftman.UHCRun;

public class UHCAutoLeafDecay implements Listener {

	@EventHandler
	public void breakWood(BlockBreakEvent e){
		//voir la video
	}
	
    @SuppressWarnings("deprecation")
    private void breakLeaf(World world, int x, int y, int z) {
        Block block = world.getBlockAt(x, y, z);
        byte data = block.getData();
 
        if ((data & 4) == 4) {
            return;
        }
 
        byte range = 4;
        byte max = 32;
        int[] blocks = new int[max * max * max];
        int off = range + 1;
        int mul = max * max;
        int div = max / 2;
 
        if (validChunk(world, x - off, y - off, z - off, x + off, y + off, z + off)) {
            int offX;
            int offY;
            int offZ;
            int type;
 
            for (offX = -range; offX <= range; offX++) {
                for (offY = -range; offY <= range; offY++) {
                    for (offZ = -range; offZ <= range; offZ++) {
                        Material mat = world.getBlockAt(x + offX, y + offY, z + offZ).getType();
                        if ((mat == Material.LEAVES || mat == Material.LEAVES_2))
                            type = Material.LEAVES.getId();
                        else if ((mat == Material.LOG || mat == Material.LOG_2))
                            type = Material.LOG.getId();
                        blocks[(offX + div) * mul + (offY + div) * max + offZ
                                + div] = ((mat == Material.LOG || mat == Material.LOG_2) ? 0
                                        : ((mat == Material.LEAVES || mat == Material.LEAVES_2) ? -2 : -1));
                    }
                }
            }
 
            for (offX = 1; offX <= 4; offX++) {
                for (offY = -range; offY <= range; offY++) {
                    for (offZ = -range; offZ <= range; offZ++) {
                        for (type = -range; type <= range; type++) {
                            if (blocks[(offY + div) * mul + (offZ + div) * max + type + div] == offX - 1) {
                                if (blocks[(offY + div - 1) * mul + (offZ + div) * max + type + div] == -2)
                                    blocks[(offY + div - 1) * mul + (offZ + div) * max + type + div] = offX;
 
                                if (blocks[(offY + div + 1) * mul + (offZ + div) * max + type + div] == -2)
                                    blocks[(offY + div + 1) * mul + (offZ + div) * max + type + div] = offX;
 
                                if (blocks[(offY + div) * mul + (offZ + div - 1) * max + type + div] == -2)
                                    blocks[(offY + div) * mul + (offZ + div - 1) * max + type + div] = offX;
 
                                if (blocks[(offY + div) * mul + (offZ + div + 1) * max + type + div] == -2)
                                    blocks[(offY + div) * mul + (offZ + div + 1) * max + type + div] = offX;
 
                                if (blocks[(offY + div) * mul + (offZ + div) * max + (type + div - 1)] == -2)
                                    blocks[(offY + div) * mul + (offZ + div) * max + (type + div - 1)] = offX;
 
                                if (blocks[(offY + div) * mul + (offZ + div) * max + type + div + 1] == -2)
                                    blocks[(offY + div) * mul + (offZ + div) * max + type + div + 1] = offX;
                            }
                        }
                    }
                }
            }
        }
 
        if (blocks[div * mul + div * max + div] < 0) {
            LeavesDecayEvent event = new LeavesDecayEvent(block);
            Bukkit.getServer().getPluginManager().callEvent(event);
 
            if (event.isCancelled()) {
                return;
            }
 
            block.breakNaturally();
 
            if (10 > new Random().nextInt(100)) {
                world.playEffect(block.getLocation(), Effect.STEP_SOUND, Material.LEAVES.getId());
            }
        }
    }
 
    public boolean validChunk(World world, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        if (maxY >= 0 && minY < world.getMaxHeight()) {
            minX >>= 4;
            minZ >>= 4;
            maxX >>= 4;
            maxZ >>= 4;
 
            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (!world.isChunkLoaded(x, z)) {
                        return false;
                    }
                }
            }
 
            return true;
        }
 
        return false;
    }
 
    @SuppressWarnings("unused")
	private void checkLeaves(Block block) {
        Location loc = block.getLocation();
        final World world = loc.getWorld();
        final int x = loc.getBlockX();
        final int y = loc.getBlockY();
        final int z = loc.getBlockZ();
        final int range = 4;
        final int off = range + 1;
 
        if (!validChunk(world, x - off, y - off, z - off, x + off, y + off, z + off)) {
            return;
        }
 
        Bukkit.getServer().getScheduler().runTask(UHCRun.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (int offX = -range; offX <= range; offX++) {
                    for (int offY = -range; offY <= range; offY++) {
                        for (int offZ = -range; offZ <= range; offZ++) {
                            if ((world.getBlockAt(x + offX, y + offY, z + offZ).getType() == Material.LEAVES
                                    || world.getBlockAt(x + offX, y + offY, z + offZ).getType() == Material.LEAVES_2)) {
                                breakLeaf(world, x + offX, y + offY, z + offZ);
                            }
                        }
                    }
                }
            }
        });
    }
	
}
