package de.xnonymous.vanish.commands;

import de.xnonymous.vanish.Vanish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SeeVanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!Vanish.getInstance().getVanishRegistry().getVanishes().isEmpty())
            commandSender.sendMessage(Vanish.getInstance().getPrefix() + Vanish.getInstance().getVanishRegistry().getVanishes().stream().map(vanishPlayer -> vanishPlayer.getPlayer().getName()).collect(Collectors.joining(" ,")));
        else
            commandSender.sendMessage(Vanish.getInstance().getPrefix() + "Niemand ist im Vanish!");

        return false;
    }
}
