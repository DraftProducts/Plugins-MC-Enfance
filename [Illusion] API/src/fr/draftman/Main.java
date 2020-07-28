package fr.draftman;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		getCommand("admin").setExecutor(new AdminCommand());
		
	}
	
	public static List<String> getServeursByType(String type) {
		
		List<String> serveurs = new ArrayList<>();
		
		Path dossiers = Paths.get("/home/"+type);
		
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(dossiers);
			Iterator<Path> iterator = stream.iterator();
			
			while(iterator.hasNext()) {
				
				Path p = iterator.next();
				serveurs.add(""+p.getFileName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return serveurs;
	}

}
