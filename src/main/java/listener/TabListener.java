package listener;

import minealex.tcommandblocker.TCommandBlockerLite;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class TabListener implements Listener {

    private final TCommandBlockerLite plugin;

    public TabListener(@NotNull TCommandBlockerLite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerCommandSend(@NotNull PlayerCommandSendEvent event) {
        Player p = event.getPlayer();

        List<String> remove = Arrays.asList("ver", "version", "about", "help", "?");

        if (!p.hasPermission("tcb.bypass.tab") && !p.hasPermission("tcb.admin")) {
            if (plugin.getConfigManager().isTab()) {
                event.getCommands().removeIf(command -> command.contains(":"));
                event.getCommands().removeAll(remove);
            }
        }
    }
}
