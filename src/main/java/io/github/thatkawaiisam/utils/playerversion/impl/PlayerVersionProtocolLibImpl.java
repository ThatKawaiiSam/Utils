package io.github.thatkawaiisam.utils.playerversion.impl;

import com.comphenix.protocol.ProtocolLibrary;
import io.github.thatkawaiisam.utils.playerversion.IPlayerVersion;
import io.github.thatkawaiisam.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;

public class PlayerVersionProtocolLibImpl implements IPlayerVersion {

    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(
                ProtocolLibrary.getProtocolManager().getProtocolVersion(player)
        );
    }
}
