package de.xnonymous.vanish.vanish;

import de.xnonymous.vanish.Vanish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@Getter
@Setter
@AllArgsConstructor
public class VanishPlayer {

    private Player player;

    public void handle() {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.hidePlayer(Vanish.getInstance(), player);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutPlayerInfo);
        }
    }

    public void remove() {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.showPlayer(Vanish.getInstance(), player);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutPlayerInfo);
        }
    }
}
