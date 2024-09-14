package utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TranslateColors {

    public String translateColors(Player player, String message) {
        message = PlaceholderAPI.setPlaceholders(player, message);
        message = TranslateHexColorCodes.translateHexColorCodes("&#", "", message);
        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }
}
