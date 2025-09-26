package id.boenyy.trader.npc;

import id.boenyy.trader.TraderPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class NpcManager {
    private final TraderPlugin plugin;
    private final Map<Integer, String> npcCategory = new HashMap<>();

    public NpcManager(TraderPlugin plugin) {
        this.plugin = plugin;
        loadNpcs();
    }

    public void loadNpcs() {
        FileConfiguration config = plugin.getDataManager().getNpcsConfig();
        npcCategory.clear();
        for (String key : config.getKeys(false)) {
            npcCategory.put(Integer.parseInt(key), config.getString(key));
        }
    }

    public void bindNpc(int npcId, String category) {
        npcCategory.put(npcId, category);
        plugin.getDataManager().getNpcsConfig().set(String.valueOf(npcId), category);
        plugin.getDataManager().saveNpcs();
    }

    public String getCategory(int npcId) {
        return npcCategory.get(npcId);
    }
}
