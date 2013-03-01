/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhawk87.PopupMenuAPIExample.menus;

import com.xhawk87.PopupMenuAPIExample.PlayerClass;
import com.xhawk87.PopupMenuAPIExample.PopupMenuAPIExample;
import org.bukkit.entity.Player;

/**
 * PopupMenus
 *
 * @author XHawk87
 */
public abstract class MenuSystem {

    private PopupMenuAPIExample plugin;

    public MenuSystem(PopupMenuAPIExample plugin) {
        this.plugin = plugin;
    }

    public abstract void selectClassMenu(Player player);

    protected void classSelected(Player player, PlayerClass playerClass) {
        plugin.setPlayerClass(player, playerClass);
    }

    protected PopupMenuAPIExample getPlugin() {
        return plugin;
    }
}
