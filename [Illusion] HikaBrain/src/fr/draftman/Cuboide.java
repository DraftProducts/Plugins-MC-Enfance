package fr.draftman;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Cuboide {
	
	private int maxX;
    private int maxY;
    private int maxZ;

    private int minX;
    private int minY;
    private int minZ;

    public Cuboide(Location l, Location l2) {

            if (l.getBlockX() > l2.getBlockX()) {
                    maxX = l.getBlockX();
                    minX = l2.getBlockX();
            } else {
                    maxX = l2.getBlockX();
                    minX = l.getBlockX();
            }
            if (l.getBlockY() > l2.getBlockY()) {
                    maxY = l.getBlockY();
                    minY = l2.getBlockY();
            } else {
                    maxY = l2.getBlockY();
                    minY = l.getBlockY();
            }
            if (l.getBlockZ() > l2.getBlockZ()) {
                    maxZ = l.getBlockZ();
                    minZ = l2.getBlockZ();
            } else {
                    maxZ = l2.getBlockZ();
                    minZ = l.getBlockZ();
            }

    }

    public boolean isInCube(Block b) {
            Block bl = b.getLocation().getBlock();
            if ((bl.getX() <= maxX) && (bl.getX() >= minX)
                            && (bl.getY() <= maxY) && (bl.getY() >= minY)
                            && (bl.getZ() <= maxZ) && (bl.getZ() >= minZ))
            {
                    return true;
            }
            return false;
    }
}