package me.happy.hub.listeners;

import me.happy.hub.inventory.ServerSelector;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderButtListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            if (player.getItemInHand().getType() == Material.ENDER_PEARL) {
                e.setCancelled(true);
                e.setUseItemInHand(Event.Result.DENY);
                player.setVelocity(player.getLocation().getDirection().multiply(3D));
            }
        }
    }
}
