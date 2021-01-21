package de.xnonymous.vanish.vanish;

import de.xnonymous.vanish.Vanish;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@Getter
@Setter
public class VanishRegistry {

    private ArrayList<VanishPlayer> vanishes = new ArrayList<>();

    public void add(Player player) {
        VanishPlayer vanishPlayer;
        vanishes.add(vanishPlayer = new VanishPlayer(player));
        vanishPlayer.handle();
        player.sendMessage(Vanish.getInstance().getPrefix() + "Du bist nun Unsichtbar.");
        Bukkit.getOnlinePlayers().forEach(player1 -> player1.sendMessage(Vanish.getInstance().getLeave().replaceAll("%player%", player.getName())));
    }

    public void remove(VanishPlayer vanishPlayer) {
        vanishPlayer.remove();
        vanishes.remove(vanishPlayer);
        if (vanishPlayer.getPlayer().isOnline()) {
            vanishPlayer.getPlayer().sendMessage(Vanish.getInstance().getPrefix() + "Du bist nun nicht mehr Unsichtbar");
            Bukkit.getOnlinePlayers().forEach(player1 -> player1.sendMessage(Vanish.getInstance().getJoin().replaceAll("%player%", vanishPlayer.getPlayer().getName())));
        }
    }

    public VanishPlayer find(Player player) {
        return vanishes.stream().filter(vanishPlayer -> vanishPlayer.getPlayer().getUniqueId().toString().equals(player.getUniqueId().toString())).findFirst().orElse(null);
    }

}
