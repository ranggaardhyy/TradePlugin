package id.boenyy.trader.trade;

import id.boenyy.trader.TraderPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiConfigManager {

    private final TraderPlugin plugin;
    private final FileConfiguration guiConfig;

    public GuiConfigManager(TraderPlugin plugin) {
        this.plugin = plugin;

        // Load gui.yml dari resources
        File guiFile = new File(plugin.getDataFolder(), "gui.yml");
        if (!guiFile.exists()) {
            plugin.saveResource("gui.yml", false);
        }
        this.guiConfig = YamlConfiguration.loadConfiguration(guiFile);
    }

    public String getTitle() {
        return guiConfig.getString("title", "&6Trader");
    }

    public int getSize() {
        return guiConfig.getInt("size", 27);
    }

    /**
     * Mengembalikan Map slot -> item info dari GUI
     */
    public Map<Integer, Map<String, Object>> getItems(String category) {
        Map<Integer, Map<String, Object>> itemsMap = new HashMap<>();
        if (guiConfig.getConfigurationSection("slots") == null) return itemsMap;

        guiConfig.getConfigurationSection("slots").getKeys(false).forEach(key -> {
            int slot = Integer.parseInt(key);
            Map<String, Object> map = new HashMap<>();
            map.put("item", guiConfig.getString("slots." + key + ".item"));
            map.put("display-name", guiConfig.getString("slots." + key + ".display-name"));
            map.put("lore", guiConfig.getStringList("slots." + key + ".lore"));
            map.put("price", guiConfig.getInt("slots." + key + ".price", 1));
            map.put("category", guiConfig.getString("slots." + key + ".category", "farm"));
            // Filter by category
            if (map.get("category").equals(category)) {
                itemsMap.put(slot, map);
            }
        });

        return itemsMap;
    }
}
