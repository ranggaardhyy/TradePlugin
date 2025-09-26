package id.boenyy.trader.data;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerLimit {
    private final ConcurrentHashMap<UUID, ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>> limits = new ConcurrentHashMap<>();

    public int get(UUID player, int npcId, String item) {
        return limits.getOrDefault(player, new ConcurrentHashMap<>())
                .getOrDefault(npcId, new ConcurrentHashMap<>())
                .getOrDefault(item, 0);
    }

    public void add(UUID player, int npcId, String item, int amount) {
        limits.computeIfAbsent(player, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(npcId, k -> new ConcurrentHashMap<>())
                .merge(item, amount, Integer::sum);
    }

    public void resetAll() {
        limits.clear();
    }
}
