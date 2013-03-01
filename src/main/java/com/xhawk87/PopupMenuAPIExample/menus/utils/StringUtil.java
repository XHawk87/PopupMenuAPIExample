/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhawk87.PopupMenuAPIExample.menus.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * StringUtil
 *
 * @author XHawk87
 */
public class StringUtil {

    public static List<String> wrapWords(String text, int lineLength) {
        String[] intendedLines = text.split("\\n");
        ArrayList<String> lines = new ArrayList<>();
        for (String intendedLine : intendedLines) {
            String[] words = intendedLine.split(" ");
            StringBuilder buffer = new StringBuilder();

            for (String word : words) {
                if (word.length() >= lineLength) {
                    if (buffer.length() != 0) {
                        lines.add(buffer.toString());
                    }
                    lines.add(word);
                    buffer = new StringBuilder();
                    continue;
                }
                if (buffer.length() + word.length() >= lineLength) {
                    lines.add(buffer.toString());
                    buffer = new StringBuilder();
                }
                if (buffer.length() != 0) {
                    buffer.append(' ');
                }
                buffer.append(word);
            }
            lines.add(buffer.toString());
        }

        return lines;
    }
}
