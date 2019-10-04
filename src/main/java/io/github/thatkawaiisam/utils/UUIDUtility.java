package io.github.thatkawaiisam.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UUIDUtility {

    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static CompletableFuture<UUIDPair> getUUID(String player) {
        return CompletableFuture.supplyAsync(() -> {
            UUIDPair uuidPair = new UUIDPair();
            if (player == null) {
                uuidPair.setUuid(null);
                uuidPair.setName(null);
                uuidPair.setLocation(UUIDLocation.OFFLINE);
            } else if (Bukkit.getPlayer(player) != null && Bukkit.getPlayer(player).isOnline()) {
                uuidPair.setUuid(Bukkit.getPlayer(player).getUniqueId());
                uuidPair.setName(Bukkit.getPlayer(player).getName());
                uuidPair.setLocation(UUIDLocation.ONLINE);
            } else if (Bukkit.getOfflinePlayer(player) != null) {
                uuidPair.setUuid(Bukkit.getOfflinePlayer(player).getUniqueId());
                uuidPair.setName(Bukkit.getOfflinePlayer(player).getName());
                uuidPair.setLocation(Bukkit.getOfflinePlayer(player).hasPlayedBefore() ? UUIDLocation.OFFLINE : UUIDLocation.NEVER_PLAYED);
            } else {
                uuidPair.setUuid(null);
                uuidPair.setName(null);
                uuidPair.setLocation(UUIDLocation.OFFLINE);
            }
            return uuidPair;
        }, executorService);
    }

    public void something() {
        getUUID("ThatKawaiiSam").whenComplete(((uuidPair, throwable) -> {
            System.out.println(uuidPair.getUuid());
        }));
    }

    public static CompletableFuture<UUIDPair> getName(UUID uuid) {
        UUIDPair uuidPair = new UUIDPair();
        return CompletableFuture.supplyAsync(() -> {
            if (Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline()) {
                uuidPair.setUuid(Bukkit.getPlayer(uuid).getUniqueId());
                uuidPair.setName(Bukkit.getPlayer(uuid).getName());
                uuidPair.setLocation(UUIDLocation.ONLINE);
            } else if (Bukkit.getOfflinePlayer(uuid) != null) {
                uuidPair.setUuid(Bukkit.getOfflinePlayer(uuid).getUniqueId());
                uuidPair.setName(Bukkit.getOfflinePlayer(uuid).getName());
                uuidPair.setLocation(Bukkit.getOfflinePlayer(uuid).hasPlayedBefore() ? UUIDLocation.OFFLINE : UUIDLocation.NEVER_PLAYED);
            } else {
                uuidPair.setUuid(null);
                uuidPair.setName(null);
                uuidPair.setLocation(UUIDLocation.OFFLINE);
            }
            return uuidPair;
        }, executorService);
    }


    public enum UUIDLocation {

        ONLINE, OFFLINE, NEVER_PLAYED

    }

    @Getter @Setter
    public static class UUIDPair {

        private UUID uuid;
        private String name;
        private UUIDLocation location;

    }

}
