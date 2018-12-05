package io.github.thatkawaiisam.utils.playerversion.impl;

import io.github.thatkawaiisam.utils.playerversion.IPlayerVersion;
import io.github.thatkawaiisam.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolSupportAPI;

public class PlayerVersionProtocolSupportImpl implements IPlayerVersion {

    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(
                ProtocolSupportAPI.getProtocolVersion(player).getId()
        );
    }
}
