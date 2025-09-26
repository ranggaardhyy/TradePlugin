package id.boenyy.trader;

import id.boenyy.trader.commands.TraderCommand;
import id.boenyy.trader.data.DataManager;
import id.boenyy.trader.npc.NpcListener;
import id.boenyy.trader.npc.NpcManager;
import id.boenyy.trader.trade.DailyRotation;
import id.boenyy.trader.trade.GuiManager;
import id.boenyy.trader.trade.TradeManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TraderPlugin extends JavaPlugin {

    private static TraderPlugin instance;
    private NpcManager npcManager;
    private TradeManager tradeManager;
    private DataManager dataManager;
    private DailyRotation dailyRotation;
    private GuiManager guiManager ;

    @Override
    public void onEnable() {
        instance = this;

        if(!getDataFolder().exists()) getDataFolder().mkdirs();

        saveDefaultConfig();
        dataManager = new DataManager(this);
        npcManager = new NpcManager(this);
        tradeManager = new TradeManager(this);
        dailyRotation = new DailyRotation(this);
        guiManager = new GuiManager(this);


        getCommand("trader").setExecutor(new TraderCommand(this));
        getServer().getPluginManager().registerEvents(new NpcListener(this), this);

        dailyRotation.start();
        getLogger().info("TraderPlugin enabled!");
    }

    @Override
    public void onDisable() {

        if (dataManager != null) {
            dataManager.saveLimits();
        }
        getLogger().info("TraderPlugin disabled!");
    }

    public static TraderPlugin getInstance() {
        return instance;
    }

    public NpcManager getNpcManager() {
        return npcManager;
    }

    public TradeManager getTradeManager() {
        return tradeManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }

    public DailyRotation getDailyRotation() {
        return dailyRotation;
    }
}
