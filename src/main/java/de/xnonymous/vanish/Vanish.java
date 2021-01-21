package de.xnonymous.vanish;

import com.sun.corba.se.spi.ior.IdentifiableFactoryFinder;
import de.xnonymous.vanish.commands.SeeVanishCommand;
import de.xnonymous.vanish.commands.VanishCommand;
import de.xnonymous.vanish.config.ConfigRegistry;
import de.xnonymous.vanish.config.impl.DefaultConfig;
import de.xnonymous.vanish.listener.PlayerChatListener;
import de.xnonymous.vanish.listener.PlayerJoinListener;
import de.xnonymous.vanish.listener.PlayerLeaveListener;
import de.xnonymous.vanish.vanish.VanishPlayer;
import de.xnonymous.vanish.vanish.VanishRegistry;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Vanish extends JavaPlugin {

    @Getter
    private static Vanish instance;

    private VanishRegistry vanishRegistry;
    private ConfigRegistry configRegistry;

    private DefaultConfig defaultConfig;

    private String prefix;
    private String join;
    private String leave;

    @Override
    public void onEnable() {
        instance = this;

        configRegistry = new ConfigRegistry();
        vanishRegistry = new VanishRegistry();

        registerConfigs();
        registerListener();
        registerCommands();
    }

    @Override
    public void onDisable() {
        vanishRegistry.getVanishes().forEach(VanishPlayer::remove);
    }

    private void registerConfigs() {
        configRegistry.register(defaultConfig = new DefaultConfig());
        defaultConfig.getCfg().addDefault("Prefix", "&b[Vanish] &a»");
        defaultConfig.getCfg().addDefault("FakeLeave", "&e%player% left the game");
        defaultConfig.getCfg().addDefault("FakeJoin", "&e%player% joined the game");
        defaultConfig.getCfg().options().copyDefaults(true);
        defaultConfig.save();

        prefix = defaultConfig.getCfg().getString("Prefix");
        prefix = prefix.replaceAll("&", "§");
        prefix += " ";

        join = defaultConfig.getCfg().getString("FakeJoin");
        join = join.replaceAll("&", "§");
        join += " ";

        leave = defaultConfig.getCfg().getString("FakeLeave");
        leave = leave.replaceAll("&", "§");
        leave += " ";
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerChatListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerLeaveListener(), this);
    }

    private void registerCommands() {
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("seevanish").setExecutor(new SeeVanishCommand());
    }
}
