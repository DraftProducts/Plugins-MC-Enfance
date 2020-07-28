package fr.draftman.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class menuPrincipal {
	public static void menuPrincipalInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*5, "§8Jeux");
		
		inv.clear();
		
		//ItemStack Survie = new ItemStack(Material.GRASS);
		//ItemMeta SurvieM = Survie.getItemMeta();
		//SurvieM.setDisplayName("§6Survie");
		//SurvieM.setLore(Arrays.asList(" ",
		//		"§7Une Survie basé sur l'économie",
		//		"§7Construit toi ta base sans avoir peur",
		//		"§7de te la faire détruire",
		//		"§7Construit ta boutique pour devenir riche !",
		//		"§e ",
		//		"§k!!!§2Clic gauche pour rejondre !§k!!!",
		//		" "));
		//Survie.setItemMeta(SurvieM);
		
		ItemStack BedWars = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta BedWarsM = BedWars.getItemMeta();
		BedWarsM.setDisplayName("§6BedWars");
		BedWarsM.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires au dessus du vide",
				"§7Essaye de détrurire le lit de l'équipe adverse",
				"§7en étant le plus habile possible.",
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		BedWars.setItemMeta(BedWarsM);
		
		ItemStack HikaBrain = new ItemStack(Material.SANDSTONE, 1, (byte) 2);
		ItemMeta HikaBrainM = HikaBrain.getItemMeta();
		HikaBrainM.setDisplayName("§6HikaBrain");
		HikaBrainM.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires au dessus du vide",
				"§7Sur une map d'entrainement pour le MLG Block",
				"§7et le Brain.",
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		HikaBrain.setItemMeta(HikaBrainM);
		
		ItemStack Practice = new ItemStack(Material.CHEST);
		ItemMeta PracticeM = Practice.getItemMeta();
		PracticeM.setDisplayName("§6Practice");
		PracticeM.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires dans des combats",
				"§7avec des kits farfelus ou Pvp tout simplement ", 
				"§7avec tes amis en 1v1 ou en Team !", 
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		Practice.setItemMeta(PracticeM);
		
		ItemStack Rush = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta RushM = Rush.getItemMeta();
		RushM.setDisplayName("§6Rush §4(BIENTOT)");
		RushM.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires au dessus du vide",
				"§7Essaye de détrurire le lit de l'équipe adverse",
				"§7en étant le plus habile possible.",
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		Rush.setItemMeta(RushM);
		
		ItemStack Tower = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
		ItemMeta TowerM = Tower.getItemMeta();
		TowerM.setDisplayName("§6Tower §4(BIENTOT)");
		TowerM.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires en hauteur vertiginineuse",
				"§7Fait attention a ne pas tomber", 
				"§7Vas marquer des points dans leur nexus.", 
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		Tower.setItemMeta(TowerM);
		
		ItemStack Bomber = new ItemStack(Material.BRICK);
		ItemMeta BomberM = Bomber.getItemMeta();
		BomberM.setDisplayName("§6Bomber §4(BIENTOT)");
		BomberM.setLore(Arrays.asList(" ",
				"§7Fait exploser les autres joueurs avec ton arc",
				"§7soit amuses toi a tester des bombes toutes aussi", 
				"§7spécial les unes que les autre !", 
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		Bomber.setItemMeta(BomberM);
		
		ItemStack BuildBattle = new ItemStack(Material.BRICK);
		ItemMeta BuildBattleM = BuildBattle.getItemMeta();
		BuildBattleM.setDisplayName("§6BuildBattle §4(BIENTOT)");
		BuildBattleM.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires en construisant",
				"§7le mieux possible et décide si ils sont",
				"§7dignent de gagner ou non.",
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		BuildBattle.setItemMeta(BuildBattleM);
		
		ItemStack Evolution = new ItemStack(Material.NETHER_STAR);
		ItemMeta EvolutionM = Evolution.getItemMeta();
		EvolutionM.setDisplayName("§6Evolution §4(BIENTOT)");
		EvolutionM.setLore(Arrays.asList(" ",
				"§7Affronte tes adversaires dans une arÃ¨ne",
				"§7et améliore ton stuff pour être le meilleur", 
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		Evolution.setItemMeta(EvolutionM);
		
		ItemStack JumpLeague = new ItemStack(Material.FEATHER);
		ItemMeta JumpLeagueM = JumpLeague.getItemMeta();
		JumpLeagueM.setDisplayName("§6JumpLeague §4(BIENTOT)");
		JumpLeagueM.setLore(Arrays.asList(" ",
				"§7Surmontes les différents niveaux de jumps",
				"§7Récupère ton steuf dans des Coffres", 
				"§7Avant de devoir Combattre de redoutables énemis !", 
				"§e ",
				"§k!!!§2Clic gauche pour jouer !§k!!!",
				" "));
		JumpLeague.setItemMeta(JumpLeagueM);
		
		inv.setItem(10, BedWars);
		inv.setItem(12, HikaBrain);
		inv.setItem(14, Practice);
		inv.setItem(14, Rush);
		inv.setItem(16, Bomber);
		inv.setItem(28, BuildBattle);
		inv.setItem(30, Evolution);
		inv.setItem(32, JumpLeague);
		
		p.openInventory(inv);
	}
}
