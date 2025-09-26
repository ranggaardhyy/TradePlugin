package id.boenyy.trader.trade;

import id.boenyy.trader.TraderPlugin;
import org.bukkit.Bukkit;

import java.util.concurrent.TimeUnit;

public class DailyRotation {

    private final TraderPlugin plugin;

    public DailyRotation(TraderPlugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        long intervalMinutes = plugin.getConfig().getLong("reset-interval-minutes", 24);
        long intervalTicks = intervalMinutes * 60 * 20L;
        Bukkit.getScheduler().runTaskTimer(plugin, this::resetAll, intervalTicks, intervalTicks);
    }

    private void resetAll() {
        plugin.getDataManager().resetAllLimits();
        // Optionally rotate items here if you implement randomization
        plugin.getLogger().info("Trader daily rotation executed!");
    }
}
