package id.boenyy.trader.npc;

import id.boenyy.trader.TraderPlugin;
import id.boenyy.trader.trade.GuiManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class NpcListener implements Listener {

    private final TraderPlugin plugin;

    public NpcListener(TraderPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNpcClick(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        int npcId = e.getRightClicked().getEntityId(); // gunakan entityId sebagai npcId
        String category = plugin.getNpcManager().getCategory(npcId);
        if (category == null) return;

        if (player.isSneaking()) {
            // Left-click = sell
            plugin.getTradeManager().openSellGui(player, category, npcId);
        } else {
            // Right-click = buy
            plugin.getTradeManager().openBuyGui(player, category, npcId);
        }
        e.setCancelled(true);
    }
}
