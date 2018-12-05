package io.github.thatkawaiisam.utils;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MapUtility {

    //TODO finish this utility
    public static void createNewMap(JavaPlugin javaPlugin, MapView mapView, MapView.Scale scale){
        /* Set the Scale */
        mapView.setScale(scale);
        /* Clear the existing renderers */
        mapView.getRenderers().clear();
        /* Add custom renderer */
        mapView.addRenderer(new CustomRender(javaPlugin));
    }

    public static class CustomRender extends MapRenderer {

        private JavaPlugin plugin;

        public CustomRender(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        @Override
        public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
            new BukkitRunnable(){
                @Override
                public void run() {
                    BufferedImage image = null; //should be initialized outside the render method
                    try {
                        image = ImageIO.read(new URL("http://www.newdesignfile.com/postpic/2015/02/mario-128x128-icon_245367.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mapCanvas.drawImage(0, 0, image);
                }
            }.runTaskAsynchronously(plugin);

        }
    }
}
