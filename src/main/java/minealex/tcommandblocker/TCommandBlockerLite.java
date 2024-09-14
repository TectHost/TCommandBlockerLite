package minealex.tcommandblocker;

import commands.Commands;
import listener.PlayerCommandListener;
import listener.TabListener;
import managers.ConfigManager;
import managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import utils.*;

import java.util.Objects;

public class TCommandBlockerLite extends JavaPlugin {
    private TranslateColors translateColors;
    private ConfigManager configManager;
    private MessagesManager messagesManager;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "TCB" + ChatColor.GRAY + "ᴸᴵᵀᴱ] " + ChatColor.GREEN + "Starting TCBᴸᴵᵀᴱ...");

        registerConfigFiles();
        initializeManagers();
        registerListeners();
        registerCommands();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "TCB" + ChatColor.GRAY + "ᴸᴵᵀᴱ] " + ChatColor.GREEN + "TCBᴸᴵᵀᴱ started.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "TCB" + ChatColor.GRAY + "ᴸᴵᵀᴱ] " + ChatColor.RED + "TCBᴸᴵᵀᴱ stopped");
    }

    public void registerListeners() {
        new PlayerCommandListener(this);
        getServer().getPluginManager().registerEvents(new TabListener(this), this);
    }

    public void registerConfigFiles() {
        configManager = new ConfigManager(this);
        messagesManager = new MessagesManager(this);
    }

    public void initializeManagers() {
        translateColors = new TranslateColors();
    }

    public void registerCommands() {
        Objects.requireNonNull(this.getCommand("tcb")).setExecutor(new Commands(this));
    }

    // ------------------------------------------------------------------------------

    public TranslateColors getTranslateColors() { return translateColors; }
    public ConfigManager getConfigManager() { return configManager;}
    public MessagesManager getMessagesManager() { return messagesManager;}
}
