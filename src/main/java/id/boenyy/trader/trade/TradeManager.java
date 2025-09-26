package id.boenyy.trader.trade;

import id.boenyy.trader.TraderPlugin;
import id.boenyy.trader.data.DataManager;
import id.boenyy.trader.util.EconomyUtil;
import org.bukkit.entity.Player;

public class TradeManager {

    private final TraderPlugin plugin;
    private final GuiManager guiManager;
    private final DataManager dataManager;

    public TradeManager(TraderPlugin plugin) {
        this.plugin = plugin;
        this.guiManager = new GuiManager(plugin);
        this.dataManager = plugin.getDataManager();
    }

    public void openSellGui(Player player, String category, int npcId) {
        guiManager.openSellGui(player, category, npcId);
    }

    public void openBuyGui(Player player, String category, int npcId) {
        guiManager.openBuyGui(player, category, npcId);
    }

    public void sellItem(Player player, int npcId, String item, int amount, double price) {
        dataManager.addPlayerItemLimit(player.getUniqueId(), npcId, item, amount);
        EconomyUtil.deposit(player, price * amount);
        guiManager.refreshPlayerGui(player, npcId);
    }

    public void buyItem(Player player, int npcId, String item, int amount, double price) {
        if (EconomyUtil.has(player, price * amount)) {
            EconomyUtil.withdraw(player, price * amount);
            player.getInventory().addItem(ItemPool.getItem(item, amount));
            guiManager.refreshPlayerGui(player, npcId);
        }
    }
}
