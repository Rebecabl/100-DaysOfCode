package TaskSorter;

import javax.swing.*;

public class ThemeManager {
    private static boolean isDarkMode = false;

    public static void aplicarTema(JFrame frame) {
        if (isDarkMode) {
            UIManager.put("Panel.background", java.awt.Color.DARK_GRAY);
            UIManager.put("Button.background", java.awt.Color.GRAY);
            UIManager.put("Button.foreground", java.awt.Color.WHITE);
            UIManager.put("Label.foreground", java.awt.Color.WHITE);
        } else {
            UIManager.put("Panel.background", java.awt.Color.LIGHT_GRAY);
            UIManager.put("Button.background", java.awt.Color.WHITE);
            UIManager.put("Button.foreground", java.awt.Color.BLACK);
            UIManager.put("Label.foreground", java.awt.Color.BLACK);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public static void alternarTema(JFrame frame) {
        isDarkMode = !isDarkMode;
        aplicarTema(frame);
    }
}
