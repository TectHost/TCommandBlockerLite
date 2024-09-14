package commands;

import minealex.tcommandblocker.TCommandBlockerLite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Commands implements CommandExecutor, TabCompleter {
    private final TCommandBlockerLite plugin;

    public Commands(@NotNull TCommandBlockerLite plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("tcb")).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand("tcb")).setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (sender.hasPermission("tcb.admin") || sender.hasPermission("tcb.admin.command.version") || sender.hasPermission("tcb.admin.command.reload")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("tcb.admin") || sender.hasPermission("tcb.admin.command.reload")) {
                        plugin.getConfigManager().reloadConfig();
                        plugin.getMessagesManager().reloadConfig();
                        String message = plugin.getMessagesManager().getReload();
                        String prefix = plugin.getMessagesManager().getPrefix();
                        sender.sendMessage(plugin.getTranslateColors().translateColors(null, prefix + message));
                    } else {
                        String message = plugin.getMessagesManager().getNoPermission();
                        String prefix = plugin.getMessagesManager().getPrefix();
                        sender.sendMessage(plugin.getTranslateColors().translateColors(null, prefix + message));
                    }
                } else if (args[0].equalsIgnoreCase("version")) {
                    if (sender.hasPermission("tcb.admin") || sender.hasPermission("tcb.admin.command.version")) {
                        String version = plugin.getDescription().getVersion();
                        String message = plugin.getMessagesManager().getVersion().replace("%version%", version);
                        String prefix = plugin.getMessagesManager().getPrefix();
                        sender.sendMessage(plugin.getTranslateColors().translateColors(null, prefix + message));
                    } else {
                        String message = plugin.getMessagesManager().getNoPermission();
                        String prefix = plugin.getMessagesManager().getPrefix();
                        sender.sendMessage(plugin.getTranslateColors().translateColors(null, prefix + message));
                    }
                }
            }
        } else {
            String message = plugin.getMessagesManager().getNoPermission();
            String prefix = plugin.getMessagesManager().getPrefix();
            sender.sendMessage(plugin.getTranslateColors().translateColors(null, prefix + message));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String @NotNull [] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("tcb.admin.command.reload") || sender.hasPermission("tcb.admin")) {
                completions.add("reload");
            }
            if (sender.hasPermission("tcb.admin.command.version") || sender.hasPermission("tcb.admin")) {
                completions.add("version");
            }
        }

        return completions;
    }
}
