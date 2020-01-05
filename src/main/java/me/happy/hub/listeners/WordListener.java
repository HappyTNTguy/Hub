package me.happy.hub.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class WordListener implements Listener {

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if (!(e.getEntity() instanceof Player)) { // don't know if I gotta do this but better safe than sorry
            e.setCancelled(true);
        }
    }
}
