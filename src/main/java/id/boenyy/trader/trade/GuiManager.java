package id.boenyy.trader.trade;

import id.boenyy.trader.TraderPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class GuiManager {

    private final TraderPlugin plugin;
    private final GuiConfigManager guiConfig;

    public GuiManager(TraderPlugin plugin) {
        this.plugin = plugin;
        this.guiConfig = new GuiConfigManager(plugin);
    }

    public void openSellGui(Player player, String category, int npcId) {
        Inventory gui = Bukkit.createInventory(null, guiConfig.getSize(), ChatColor.translateAlternateColorCodes('&', guiConfig.getTitle()));

        Map<Integer, Map<String, Object>> items = guiConfig.getItems(category);
        for (Map.Entry<Integer, Map<String, Object>> entry : items.entrySet()) {
            int slot = entry.getKey();
            Map<String, Object> itemData = entry.getValue();

            String matName = (String) itemData.get("item");
            ItemStack item = new ItemStack(Material.valueOf(matName));
            ItemMeta meta = item.getItemMeta();

            String displayName = (String) itemData.get("display-name");
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));

            List<String> lore = (List<String>) itemData.get("lore");
            lore.replaceAll(line -> line.replace("%limit%", String.valueOf(plugin.getDataManager().getPlayerItemLimit(player.getUniqueId(), npcId, item.getType().name())))
                    .replace("%max%", String.valueOf(plugin.getConfig().getInt("sell-limit", 64))));
            meta.setLore(lore);

            item.setItemMeta(meta);
            gui.setItem(slot, item);
        }

        player.openInventory(gui);
    }

    public void openBuyGui(Player player, String category, int npcId) {
        openSellGui(player, category, npcId);
    }

    public void refreshPlayerGui(Player player, int npcId) {
        if (player.getOpenInventory() != null) {
            player.closeInventory();
        }
    }
}
