package de.xnonymous.vanish.listener;

import de.xnonymous.vanish.Vanish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (Vanish.getInstance().getVanishRegistry().find(event.getPlayer()) != null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Vanish.getInstance().getPrefix() + "Du befindest dich im Vanish Modus.");
        }
    }

}
