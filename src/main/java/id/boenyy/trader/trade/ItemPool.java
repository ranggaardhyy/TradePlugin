package id.boenyy.trader.trade;

import id.boenyy.trader.TraderPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemPool {

    public static ItemStack getItem(String name, int amount) {
        Material mat = Material.valueOf(name);
        ItemStack item = new ItemStack(mat, amount);
        return item;
    }
}
