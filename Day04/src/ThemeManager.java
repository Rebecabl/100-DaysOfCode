package TaskManagerDark;

import javax.swing.*;

public class ThemeManager {
    private static boolean darkMode = false;

    public static void toggleTheme(JFrame frame) {
        darkMode = !darkMode;

        if (darkMode) {
            UIManager.put("Panel.background", java.awt.Color.DARK_GRAY);
            UIManager.put("Button.background", java.awt.Color.GRAY);
            UIManager.put("Button.foreground", java.awt.Color.WHITE);
            UIManager.put("Label.foreground", java.awt.Color.WHITE);
            UIManager.put("List.background", java.awt.Color.BLACK);
            UIManager.put("List.foreground", java.awt.Color.WHITE);
        } else {
            UIManager.put("Panel.background", java.awt.Color.LIGHT_GRAY);
            UIManager.put("Button.background", java.awt.Color.WHITE);
            UIManager.put("Button.foreground", java.awt.Color.BLACK);
            UIManager.put("Label.foreground", java.awt.Color.BLACK);
            UIManager.put("List.background", java.awt.Color.WHITE);
            UIManager.put("List.foreground", java.awt.Color.BLACK);
        }

        SwingUtilities.updateComponentTreeUI(frame);
    }
}
