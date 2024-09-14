package managers;

import minealex.tcommandblocker.TCommandBlockerLite;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;


public class ConfigManager {

    private final ConfigFile configFile;
    private List<String> bcmds;
    private boolean tab;

    public ConfigManager(TCommandBlockerLite plugin) {
        this.configFile = new ConfigFile("config.yml", null, plugin);
        this.configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();

        bcmds = config.getStringList("blocked-commands");
        tab = config.getBoolean("tab.enabled");
    }

    public void reloadConfig() {
        configFile.reloadConfig();
        loadConfig();
    }

    public List<String> getBcmds() {return bcmds;}
    public boolean isTab() {return tab;}
}
