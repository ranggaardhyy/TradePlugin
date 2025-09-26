package id.boenyy.trader.util;

import id.boenyy.trader.TraderPlugin;

public class ConfigUtil {

    public static int getSellLimit() {
        return TraderPlugin.getInstance().getConfig().getInt("sell-limit", 64);
    }

    public static long getResetIntervalMinutes() {
        return TraderPlugin.getInstance().getConfig().getLong("reset-interval-minutes", 24);
    }
}
