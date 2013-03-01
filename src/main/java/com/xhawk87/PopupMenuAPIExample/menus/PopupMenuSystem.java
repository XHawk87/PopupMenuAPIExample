/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhawk87.PopupMenuAPIExample.menus;

import com.xhawk87.PopupMenuAPI.MenuCloseBehaviour;
import com.xhawk87.PopupMenuAPI.MenuItem;
import com.xhawk87.PopupMenuAPI.PopupMenu;
import com.xhawk87.PopupMenuAPI.PopupMenuAPI;
import com.xhawk87.PopupMenuAPIExample.PlayerClass;
import com.xhawk87.PopupMenuAPIExample.PopupMenuAPIExample;
import com.xhawk87.PopupMenuAPIExample.menus.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * PopupMenuSystem
 *
 * @author XHawk87
 */
public class PopupMenuSystem extends MenuSystem {

    private PopupMenu selectClassMenu;

    public PopupMenuSystem(PopupMenuAPIExample plugin) {
        super(plugin);

        // Create a menu item for each PlayerClass
        PlayerClass[] playerClasses = PlayerClass.values();
        selectClassMenu = PopupMenuAPI.createMenu("Select your class", (int) Math.ceil(playerClasses.length / 9.0));
        for (int i = 0; i < playerClasses.length; i++) {
            final PlayerClass playerClass = playerClasses[i];
            MenuItem menuItem = new MenuItem(playerClass.toString(), playerClass.getIcon()) {

                @Override
                public void onClick(Player player) {
                    // On selecting, set the player class for the player
                    classSelected(player, playerClass);
                    // and close the menu
                    getMenu().closeMenu(player);
                }
            };
            menuItem.setDescriptions(StringUtil.wrapWords(playerClass.getDescription(), 40));
            selectClassMenu.addMenuItem(menuItem, i);
        }
        
        selectClassMenu.setMenuCloseBehaviour(new MenuCloseBehaviour() {

            @Override
            public void onClose(final Player player) {
                // If player's didn't choose, send them the menu again
                if (getPlugin().getPlayerClass(player) == null) {
                    // Make sure its after this menu has closed
                    Bukkit.getScheduler().runTask(getPlugin(), new Runnable() {

                        @Override
                        public void run() {
                            selectClassMenu(player);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void selectClassMenu(Player player) {
        selectClassMenu.openMenu(player);
    }
}
