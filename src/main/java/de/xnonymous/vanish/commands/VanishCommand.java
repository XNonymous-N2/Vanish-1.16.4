package de.xnonymous.vanish.commands;

import de.xnonymous.vanish.Vanish;
import de.xnonymous.vanish.vanish.VanishPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Du musst ein Spieler sein um diesen Befehl auszuführen");
            return false;
        }
        if (args.length == 0) {
            Player player = ((Player) commandSender);
            work(player);
            return true;
        }
        if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                commandSender.sendMessage(Vanish.getInstance().getPrefix() + "Dieser Spieler ist offline");
                return false;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (work(player)) {
                commandSender.sendMessage(Vanish.getInstance().getPrefix() + player.getName() + " ist nun Unsichtbar");
            } else {
                commandSender.sendMessage(Vanish.getInstance().getPrefix() + player.getName() + " ist nun Sichtbar");
            }
            return true;

        }

        commandSender.sendMessage(Vanish.getInstance().getPrefix() + "Bitte bentuze /vanish <player>");

        return false;
    }


    private boolean work(Player player) {
        VanishPlayer vanishPlayer = Vanish.getInstance().getVanishRegistry().find(player);

        if (vanishPlayer == null) {
            Vanish.getInstance().getVanishRegistry().add(player);
            return true;
        } else {
            Vanish.getInstance().getVanishRegistry().remove(vanishPlayer);
            return false;
        }
    }
}
