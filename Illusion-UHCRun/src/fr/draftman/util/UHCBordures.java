package fr.draftman.util;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class UHCBordures {

	public UHCBordures(){
		//LE CONSTRUCTEUR
	}
	
	//ON CREER UNE METHODE POUR RETRECIR LA BORDURE A CHAQUE SECONDE
	public void decreaseBorder(){
		
				//ON VA METTRE LES BORDURES DANS CHAQUE MONDE
				for(World ws : Bukkit.getWorlds()){
					
					WorldBorder wb = ws.getWorldBorder();
					//ON CHANGE LA TAILLE DE LA BORDURE DANS CHAQUE MONDE CHAQUE SECONDE
					wb.setSize(wb.getSize() - 1.0);
					
			    }
				
	}
	
	//ON SET LES BORDURES 1.8 PAR DEFAUT
	public void setBorder(double taille){
		
		//ON VA METTRE LES BORDURES DANS CHAQUE MONDE
		for(World ws : Bukkit.getWorlds()){
			
			WorldBorder wb = ws.getWorldBorder();
			//ON SET LE MILIEU DE LA BORDURE DANS CHAQUE MONDE
			wb.setCenter(0, 0);
			//ON SET LA TAILLE DE LA BORDURE DANS CHAQUE MONDE
			wb.setSize(taille);
			//ON INFORME LES JOUEURS QUI SONT A 10 BLOCS OU MOINS DE LA BORDURE QU ILS DOIVENT FAIRE ATTENTION
			wb.setWarningDistance(10);
			
		}
		
	}

	public static int getBorder() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}