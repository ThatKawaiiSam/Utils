package io.github.thatkawaiisam.utils;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

public final class WorldUtility {

    /**
     * Copies a world from a directory to a new directory.
     */
    public static void copyWorld(String oldDirectory, String newDirectory, String name) {
        try {
            File dest = new File("./" + newDirectory + "/" + name);
            File source = new File("./" + oldDirectory + "/");
            FileUtils.copyDirectory(source, dest);
            Bukkit.createWorld(new WorldCreator(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a world from a directory.
     */
    public static void deleteWorld(String directory, String world) {
        Bukkit.unloadWorld(world, true);
        File dir = new File("./" + directory + world);
        try {
            FileUtils.deleteDirectory(dir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
