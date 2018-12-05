package io.github.thatkawaiisam.utils.playerversion.impl;

import io.github.thatkawaiisam.utils.playerversion.IPlayerVersion;
import io.github.thatkawaiisam.utils.playerversion.PlayerVersion;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerVersion1_7Impl implements IPlayerVersion {

    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(
                ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion()
        );
    }
}
