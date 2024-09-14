package listener;

import minealex.tcommandblocker.TCommandBlockerLite;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerCommandListener implements Listener {

    private final TCommandBlockerLite pl;
    private final List<String> restrictedCommands;

    public PlayerCommandListener(@NotNull TCommandBlockerLite pl) {
        this.pl = pl;
        this.restrictedCommands = pl.getConfigManager().getBcmds();

        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void onPlayerCommand(@NotNull PlayerCommandPreprocessEvent e) {
        String command = e.getMessage().split(" ")[0].substring(1).toLowerCase();

        if (restrictedCommands.contains(command)) {
            Player p = e.getPlayer();
            if (!p.hasPermission("tcb.bypass.cmds") && !p.hasPermission("tcb.admin")) {
                e.setCancelled(true);
                String pref = pl.getMessagesManager().getPrefix();
                String m = pl.getMessagesManager().getNoPermission();
                p.sendMessage(pl.getTranslateColors().translateColors(p, pref + m));
            }
        }
    }
}
