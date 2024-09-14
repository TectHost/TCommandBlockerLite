package managers;

import minealex.tcommandblocker.TCommandBlockerLite;
import org.bukkit.configuration.file.FileConfiguration;

public class MessagesManager {

    private final ConfigFile configFile;
    private String prefix;
    private String noPermission;
    private String reload;
    private String version;

    public MessagesManager(TCommandBlockerLite plugin) {
        this.configFile = new ConfigFile("messages.yml", null, plugin);
        this.configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();

        prefix = config.getString("prefix");
        noPermission = config.getString("messages.no-perm");
        reload = config.getString("messages.reload");
        version = config.getString("messages.version");
    }

    public void reloadConfig() {
        configFile.reloadConfig();
        loadConfig();
    }

    public String getPrefix() {return prefix;}
    public String getNoPermission() {return noPermission;}
    public String getReload() {return reload;}
    public String getVersion() {return version;}
}
