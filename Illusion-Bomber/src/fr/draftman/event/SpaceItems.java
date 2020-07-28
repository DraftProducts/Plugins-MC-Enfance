package fr.draftman.event;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.draftman.Bomber;

public class SpaceItems implements Listener {

  public HashMap<String, Integer> raison = new HashMap<String, Integer>();
	public HashMap<String, String> hnkill = new HashMap<String, String>();
	
	@EventHandler
    public void onGrenadeLaunch(PlayerInteractEvent e){
       
        Player joueur = e.getPlayer();
        ItemStack it = e.getItem();
       
        if(it != null && it.getType() == Material.SLIME_BALL){
            joueur.getInventory().removeItem(new ItemStack(Material.SLIME_BALL, 1));
            joueur.updateInventory();
           
            final Item ball = joueur.getWorld().dropItem(joueur.getLocation(), new ItemStack(Material.SLIME_BALL, 1));
            ball.setVelocity(joueur.getEyeLocation().getDirection());
           
            Bukkit.getScheduler().runTaskLater(Bomber.getInstance(), new Runnable(){
 
                @Override
                public void run() {
                	Location loc = ball.getLocation();
                    
                    loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 5f, false, false);
                    ball.getWorld().playEffect(ball.getLocation(), Effect.SMOKE, 10);
                   
                    for(Entity entities : ball.getNearbyEntities(5.0, 5.0, 5.0)){
                        if(entities instanceof Player){
                            Player victim = (Player) entities;
                            hnkill.put(victim.getName(), joueur.getName());
                            raison.put(victim.getName(), 1);
                            
                            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 5));
                        }
                    }
                   
                    ball.remove();
                }
               
            },30);
           
           
           
        }
       
    }
	
	public HashMap<String, String> hmkill = new HashMap<String, String>();
	  
	@EventHandler
	public void onArrowHit(ProjectileHitEvent event){
		
		Projectile arrow = event.getEntity();
		
		Player shooter = (Player) event.getEntity().getShooter();
   
		Location loc = arrow.getLocation();
        
        loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 5f, false, false);
        arrow.getWorld().playEffect(arrow.getLocation(), Effect.SMOKE, 10);
       
        for(Entity entities : arrow.getNearbyEntities(5.0, 5.0, 5.0)){
            if(entities instanceof Player){
                
                Player victim = (Player) entities;
                hmkill.put(victim.getName(), shooter.getName());
                raison.put(victim.getName(), 2);
                victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 2));
                victim.damage(2);
            }
        }
       
        arrow.remove();
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
	Block b = e.getBlock();
	Material bt = e.getBlock().getType();
		if(bt == Material.DRAGON_EGG){
			for(double particle =  0; particle < b.getY()+30; particle+=0.6) {
				b.getWorld().playEffect(new Location(b.getWorld(), b.getX(), particle, b.getZ()), Effect.MAGIC_CRIT, 0); 
			}
		}
	}
}