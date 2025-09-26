package id.boenyy.trader.data;

import id.boenyy.trader.TraderPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager {

    private final TraderPlugin plugin;
    private File limitsFile;
    private FileConfiguration limitsConfig;

    private File npcsFile;
    private FileConfiguration npcsConfig;

    public DataManager(TraderPlugin plugin) {
        this.plugin = plugin;
        loadFiles();
    }

    public void loadFiles() {
        limitsFile = new File(plugin.getDataFolder(), "limits.yml");
        if (!limitsFile.exists()) {
            plugin.saveResource("data/limits.yml", false);
        }
        limitsConfig = YamlConfiguration.loadConfiguration(limitsFile);

        npcsFile = new File(plugin.getDataFolder(), "npcs.yml");
        if (!npcsFile.exists()) {
            plugin.saveResource("data/npcs.yml", false);
        }
        npcsConfig = YamlConfiguration.loadConfiguration(npcsFile);
    }

    public void reloadAll() {
        loadFiles();
    }

    public int getPlayerItemLimit(UUID player, int npcId, String item) {
        return limitsConfig.getInt(player.toString() + "." + npcId + "." + item, 0);
    }

    public void addPlayerItemLimit(UUID player, int npcId, String item, int amount) {
        String path = player.toString() + "." + npcId + "." + item;
        limitsConfig.set(path, getPlayerItemLimit(player, npcId, item) + amount);
        saveLimits();
    }

    public void resetAllLimits() {
        for (String playerKey : limitsConfig.getKeys(false)) {
            limitsConfig.set(playerKey, null);
        }
        saveLimits();
    }

    public FileConfiguration getNpcsConfig() {
        return npcsConfig;
    }

    public void saveLimits() {
        try {
            limitsConfig.save(limitsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNpcs() {
        try {
            npcsConfig.save(npcsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
