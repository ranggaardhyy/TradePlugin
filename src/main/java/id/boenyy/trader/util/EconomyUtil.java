package id.boenyy.trader.util;

import net.milkbowl.vault.economy.Economy;
import id.boenyy.trader.TraderPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EconomyUtil {

    private static Economy econ = null;

    static {
        if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            econ = TraderPlugin.getInstance().getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        }
    }

    public static void deposit(Player player, double amount) {
        if (econ != null) {
            econ.depositPlayer(player, amount);
        }
    }

    public static void withdraw(Player player, double amount) {
        if (econ != null) {
            econ.withdrawPlayer(player, amount);
        }
    }

    public static boolean has(Player player, double amount) {
        return econ != null && econ.has(player, amount);
    }
}
