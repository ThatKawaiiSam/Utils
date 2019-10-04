package io.github.thatkawaiisam.utils;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public final class WorldUtility {

    /**
     * Copies a world from a directory to a new directory, and loads it.
     */
    public static World copyWorldWithLoading(String oldDirectory, String newDirectory, String name) {
        try {
            File dest = new File("./" + newDirectory + "/" + name);
            File source = new File("./" + oldDirectory + "/");
            FileUtils.copyDirectory(source, dest);
            return Bukkit.createWorld(new WorldCreator(name));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void copyWorld(String oldDirectory, String newDirectory, String name) {
        try {
            File dest = new File("./" + newDirectory + "/" + name);
            File source = new File("./" + oldDirectory + "/");
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a world from a directory.
     */
    public static void deleteWorld(String directory, String world) {
        Bukkit.unloadWorld(world, false);
        File dir = new File("./" + directory + world);
        try {
            FileUtils.deleteDirectory(dir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void clearMobs(String world) {
        for (Entity e : Bukkit.getWorld(world).getEntities()){
            if (!(e instanceof Player)) {
                e.remove();
            }
        }
    }

}
