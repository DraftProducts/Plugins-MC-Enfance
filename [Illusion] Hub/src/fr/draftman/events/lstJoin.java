package fr.draftman.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class lstJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		String pn = e.getPlayer().getName();

		giveItems(p);
		
		PlayerUtils.sendTitle(p, "§8Illusion", "§3Serveur Mini-Jeux");
        PlayerUtils.sendHeaderAndFooter(p, "§6Illusion NetWork\n", "\n§4illusion.fr\nts.illusion.fr\nplay.illusion.fr");
        PlayerUtils.sendActionBar(p, "§9Bienvenu "+ pn +" sur Illusion");
		
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(new Location(p.getWorld(), 308.5, 78, 5068.5, 0, 0));
		p.setFoodLevel(19);
		
		p.setMaxHealth(20);
		p.setHealth(20);
        
	}
	
	public static void giveItems(Player p) {
		
		Inventory inv = p.getInventory();
		
		inv.clear();
		
		ItemStack Compass = new ItemStack(Material.COMPASS);
		ItemMeta CompassM = Compass.getItemMeta();
		CompassM.setDisplayName("§8Jeux");
		Compass.setItemMeta(CompassM);
	
		ItemStack Boutique = new ItemStack(Material.GOLD_INGOT);
		ItemMeta BoutiqueM = Boutique.getItemMeta();
		BoutiqueM.setDisplayName("§eBoutique");
		Boutique.setItemMeta(BoutiqueM);
		
		ItemStack Spawn = new ItemStack(Material.BED);
		ItemMeta SpawnM = Spawn.getItemMeta();
		SpawnM.setDisplayName("§6Spawn");
		Spawn.setItemMeta(SpawnM);
		
		ItemStack Amis = new ItemStack(Material.RAW_FISH, 1, (byte) 3);
		ItemMeta AmisM = Amis.getItemMeta();
		AmisM.setDisplayName("§6Amis");
		Amis.setItemMeta(AmisM);
		
		ItemStack Jump = new ItemStack(Material.FEATHER);
		ItemMeta JumpM = Jump.getItemMeta();
		JumpM.setDisplayName("§6Jump");
		Jump.setItemMeta(JumpM);
		
		ItemStack Préférences = new ItemStack(Material.REDSTONE_COMPARATOR);
		ItemMeta PréférencesM = Préférences.getItemMeta();
		PréférencesM.setDisplayName("§6Préférences");
		Préférences.setItemMeta(PréférencesM);
		
		ItemStack Staff = new ItemStack(Material.FIREBALL);
		ItemMeta StaffM = Staff.getItemMeta();
		StaffM.setDisplayName("§6Staff");
		Staff.setItemMeta(StaffM);
		
		ItemStack Build = new ItemStack(Material.FIREBALL);
		ItemMeta BuildM = Staff.getItemMeta();
		BuildM.setDisplayName("§6Build");
		Build.setItemMeta(BuildM);
	 
		p.getInventory().clear();
		p.getInventory().setItem(0, Compass);
		p.getInventory().setItem(8, Boutique);
		p.getInventory().setItem(12, Jump);
		p.getInventory().setItem(13, Spawn);
		p.getInventory().setItem(14, Préférences);
		p.getInventory().setItem(22, Amis);
		
		if(p.getPlayer().hasPermission("perm.staff") || p.isOp()) {
			inv.setItem(9, Staff);
		}
		
		if(p.getPlayer().hasPermission("perm.build") || p.isOp()) {
			inv.setItem(17, Build);
		}
		
		
		
		p.updateInventory();
	}
	public static void giveItemsBuild(Player p) {
		
		Inventory invbuild = p.getInventory();
		
		invbuild.clear();
		
		ItemStack WorldEdit = new ItemStack(Material.FEATHER);
		ItemMeta WorldEditM = WorldEdit.getItemMeta();
		WorldEditM.setDisplayName("§6Build");
		WorldEdit.setItemMeta(WorldEditM);
		
		ItemStack Creep = new ItemStack(Material.SULPHUR);
		
		ItemStack Stone = new ItemStack(Material.STONE);
		
		
		ItemStack Arrow = new ItemStack(Material.ARROW);
		
		ItemStack Jump = new ItemStack(Material.FEATHER);
		ItemMeta JumpM = Jump.getItemMeta();
		JumpM.setDisplayName("§6Jump");
		Jump.setItemMeta(JumpM);
		
		ItemStack Spawn = new ItemStack(Material.BED);
		ItemMeta SpawnM = Spawn.getItemMeta();
		SpawnM.setDisplayName("§6Spawn");
		Spawn.setItemMeta(SpawnM);
		
		ItemStack Amis = new ItemStack(Material.RAW_FISH, 1, (byte) 3);
		ItemMeta AmisM = Amis.getItemMeta();
		AmisM.setDisplayName("§6Amis");
		Amis.setItemMeta(AmisM);
		
		ItemStack Préférences = new ItemStack(Material.REDSTONE_COMPARATOR);
		ItemMeta PréférencesM = Préférences.getItemMeta();
		PréférencesM.setDisplayName("§6Préférences");
		Préférences.setItemMeta(PréférencesM);
	 
		p.getInventory().clear();
		p.getInventory().setItem(0, WorldEdit);
		p.getInventory().setItem(1, Creep);
		p.getInventory().setItem(3, Stone);
		p.getInventory().setItem(2, Arrow);
		p.getInventory().setItem(12, Jump);
		p.getInventory().setItem(13, Spawn);
		p.getInventory().setItem(14, Préférences);
		p.getInventory().setItem(22, Amis);
		p.updateInventory();
	}
	
	
}
