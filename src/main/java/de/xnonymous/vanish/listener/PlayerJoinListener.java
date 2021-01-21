package de.xnonymous.vanish.listener;

import de.xnonymous.vanish.Vanish;
import de.xnonymous.vanish.vanish.VanishPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Vanish.getInstance().getVanishRegistry().getVanishes().forEach(VanishPlayer::handle);
    }

}
