package de.xnonymous.vanish.listener;

import de.xnonymous.vanish.Vanish;
import de.xnonymous.vanish.vanish.VanishPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        VanishPlayer vanishPlayer = Vanish.getInstance().getVanishRegistry().find(event.getPlayer());
        if (vanishPlayer != null) {
            Vanish.getInstance().getVanishRegistry().remove(vanishPlayer);
        }
    }

}
