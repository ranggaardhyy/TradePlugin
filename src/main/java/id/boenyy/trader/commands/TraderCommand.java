package id.boenyy.trader.commands;

import id.boenyy.trader.TraderPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TraderCommand implements CommandExecutor {

    private final TraderPlugin plugin;

    public TraderCommand(TraderPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("trader.admin")) {
            sender.sendMessage("§cYou don't have permission!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§e/trader bind <npcId> <category>");
            sender.sendMessage("§e/trader reload");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "bind":
                if (args.length < 3) {
                    sender.sendMessage("§cUsage: /trader bind <npcId> <category>");
                    return true;
                }
                int npcId = Integer.parseInt(args[1]);
                String category = args[2];
                plugin.getNpcManager().bindNpc(npcId, category);
                sender.sendMessage("§aNPC " + npcId + " bound to category " + category);
                break;
            case "reload":
                plugin.reloadConfig();
                plugin.getDataManager().reloadAll();
                sender.sendMessage("§aTrader plugin configs reloaded!");
                break;
            default:
                sender.sendMessage("§cUnknown command!");
        }
        return true;
    }
}
