/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhawk87.PopupMenuAPIExample;

import com.xhawk87.PopupMenuAPIExample.menus.MenuSystem;
import com.xhawk87.PopupMenuAPIExample.menus.PopupMenuSystem;
import com.xhawk87.PopupMenuAPIExample.menus.TextMenuSystem;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * PopupMenuAPIExample
 *
 * @author XHawk87
 */
public class PopupMenuAPIExample extends JavaPlugin implements Listener {

    private MenuSystem menus;
    private HashMap<String, PlayerClass> playerClasses = new HashMap<>();

    @Override
    public void onEnable() {
        try {
            Class.forName("com.xhawk87.PopupMenuAPI.PopupMenuAPI");
            menus = new PopupMenuSystem(this);
        } catch (ClassNotFoundException ex) {
            getLogger().info("PopupMenuAPI not found, using text-based menus instead");
            menus = new TextMenuSystem(this);
        }
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        // If the player does not already have a class...
        if (!playerClasses.containsKey(player.getName())) {
            // Wait until they are properly settled into the game and give them the player class selection menu
            Bukkit.getScheduler().runTask(this, new Runnable() {

                @Override
                public void run() {
                    menus.selectClassMenu(player);
                }
            });
        }
    }

    public PlayerClass getPlayerClass(Player player) {
        return playerClasses.get(player.getName());
    }

    public void setPlayerClass(final Player player, final PlayerClass playerClass) {
        // Stop the hackers! Make sure they haven't already got a class before giving them the items again
        if (!playerClasses.containsKey(player.getName())) {
            playerClasses.put(player.getName(), playerClass);
            // Make sure the menu is closed before trying to change the player's inventory
            Bukkit.getScheduler().runTask(this, new Runnable() {

                @Override
                public void run() {
                    playerClass.addStartingItems(player);
                    player.updateInventory();
                }
            });

            player.sendMessage("You are now a " + playerClass.toString());
        }
    }
}
