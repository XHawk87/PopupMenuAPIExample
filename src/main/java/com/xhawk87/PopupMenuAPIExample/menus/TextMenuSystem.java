/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhawk87.PopupMenuAPIExample.menus;

import com.xhawk87.PopupMenuAPIExample.PlayerClass;
import com.xhawk87.PopupMenuAPIExample.PopupMenuAPIExample;
import org.bukkit.Bukkit;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

/**
 * TextMenuSystem
 *
 * @author XHawk87
 */
public class TextMenuSystem extends MenuSystem {

    public TextMenuSystem(PopupMenuAPIExample plugin) {
        super(plugin);
    }

    @Override
    public void selectClassMenu(final Player player) {
        player.sendMessage("Choose your class:");
        for (PlayerClass playerClass : PlayerClass.values()) {
            player.sendMessage("    " + playerClass);
        }
        player.beginConversation(new Conversation(getPlugin(), player, new StringPrompt() {

            @Override
            public String getPromptText(ConversationContext context) {
                return "Type the name of the class to select it";
            }

            @Override
            public Prompt acceptInput(ConversationContext context, String input) {
                try {
                    final PlayerClass playerClass = PlayerClass.valueOf(input.toUpperCase());
                    Bukkit.getScheduler().runTask(getPlugin(), new Runnable() {

                        @Override
                        public void run() {
                            classSelected(player, playerClass);
                        }
                    });
                    return Prompt.END_OF_CONVERSATION;
                } catch (IllegalArgumentException ex) {
                    final Prompt repeat = this;
                    return new MessagePrompt() {

                        @Override
                        protected Prompt getNextPrompt(ConversationContext context) {
                            return repeat;
                        }

                        @Override
                        public String getPromptText(ConversationContext context) {
                            return "That is not a valid player class";
                        }
                    };
                }
            }
        }));
    }
}
