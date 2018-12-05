package io.github.thatkawaiisam.utils.playerversion.impl;

import io.github.thatkawaiisam.utils.playerversion.IPlayerVersion;
import io.github.thatkawaiisam.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;
import us.myles.ViaVersion.api.Via;

public class PlayerVersionViaVersionImpl implements IPlayerVersion {

    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(
                Via.getAPI().getPlayerVersion(player)
        );
    }
}
