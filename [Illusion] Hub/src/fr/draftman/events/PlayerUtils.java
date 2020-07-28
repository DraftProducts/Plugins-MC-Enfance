package fr.draftman.events;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;
 
public class PlayerUtils
{
       public static void sendTitle(Player p, String title, String subtitle)
        {
                PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
                IChatBaseComponent titleJSON = ChatSerializer.a("{'text':'" + title + "'}");
                IChatBaseComponent subtitleJSON = ChatSerializer.a("{'text':'" + subtitle + "'}");
                PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleJSON);
                PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleJSON);
                connection.sendPacket(titlePacket);
                connection.sendPacket(subtitlePacket);
        }
       
        public static void sendActionBar(Player p, String msg)
        {
                IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + msg + "\"}");
                PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoc);
        }
       
        public static void sendHeaderAndFooter(Player p, String head, String foot)
        {
                PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
                IChatBaseComponent header = ChatSerializer.a("{'color':'" + "', 'text':'" + head + "'}");
                IChatBaseComponent footer = ChatSerializer.a("{'color':'" + "', 'text':'" + foot + "'}");
                PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
                try
                {
                        Field headerField = packet.getClass().getDeclaredField("a");
                        headerField.setAccessible(true);
                        headerField.set(packet, header);
                        headerField.setAccessible(!headerField.isAccessible());
                       
 
                        Field footerField = packet.getClass().getDeclaredField("b");
                        footerField.setAccessible(true);
                        footerField.set(packet, footer);
                        footerField.setAccessible(!footerField.isAccessible());
                }
                catch(Exception e){e.printStackTrace();}
                connection.sendPacket(packet);
        }
}
