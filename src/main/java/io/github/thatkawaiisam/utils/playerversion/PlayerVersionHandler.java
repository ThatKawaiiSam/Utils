package io.github.thatkawaiisam.utils.playerversion;

import io.github.thatkawaiisam.utils.playerversion.impl.PlayerVersion1_7Impl;
import io.github.thatkawaiisam.utils.playerversion.impl.PlayerVersionProtocolLibImpl;
import io.github.thatkawaiisam.utils.playerversion.impl.PlayerVersionProtocolSupportImpl;
import io.github.thatkawaiisam.utils.playerversion.impl.PlayerVersionViaVersionImpl;
import io.github.thatkawaiisam.utils.serverversion.ServerVersionHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class PlayerVersionHandler {

    public static IPlayerVersion version;

    public PlayerVersionHandler() {
        /* Plugin Manager */
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();

        /* 1.7 Protocol */
        if (ServerVersionHandler.serverVersionName.contains("1_7")){
            version = new PlayerVersion1_7Impl();
            return;
        }

        /* ProtocolSupport */
        if (pluginManager.getPlugin("ProtocolSupport") != null
                || pluginManager.getPlugin("CuckSupport") != null) {
            version = new PlayerVersionProtocolSupportImpl();
            return;
        }

        /* ViaVersion */
        if (pluginManager.getPlugin("ViaVersion") != null) {
            version = new PlayerVersionViaVersionImpl();
            return;
        }

        /* ProtocolLib */
        if (pluginManager.getPlugin("ProtocolLib") != null) {
            version = new PlayerVersionProtocolLibImpl();
            return;
        }
    }
}
