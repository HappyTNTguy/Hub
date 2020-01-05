package me.happy.hub.listeners;

import me.happy.hub.Hub;
import me.happy.hub.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        e.getPlayer().setHealth(20.0);
        e.getPlayer().setFoodLevel(20);
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setHelmet(new ItemBuilder(Material.AIR).toItemStack());
        e.getPlayer().getInventory().setChestplate(new ItemBuilder(Material.AIR).toItemStack());
        e.getPlayer().getInventory().setLeggings(new ItemBuilder(Material.AIR).toItemStack());
        e.getPlayer().getInventory().setBoots(new ItemBuilder(Material.AIR).toItemStack());
        e.getPlayer().updateInventory();
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        e.getPlayer().setAllowFlight(true);

        e.getPlayer().getInventory().setHeldItemSlot(3);
        ItemStack SELECTOR = new ItemBuilder(Material.COMPASS).setName(ChatColor.GOLD + "Server Selector").toItemStack();
        ItemStack ENDERBUTT = new ItemBuilder(Material.ENDER_PEARL, 2).setName(ChatColor.GOLD + "Ender Butt").toItemStack();
        ItemStack COSMETICS = new ItemBuilder(Material.ENDER_CHEST).setName(ChatColor.AQUA + "Cosmetics").toItemStack();
        e.getPlayer().getInventory().setItem(3, SELECTOR);
        e.getPlayer().getInventory().setItem(4, COSMETICS);
        e.getPlayer().getInventory().setItem(5, ENDERBUTT);

        Location spawn = new Location(e.getPlayer().getWorld(),
                Hub.getInstance().getConfig().getDouble("spawn.x"), Hub.getInstance().getConfig().getDouble("spawn.y"), Hub.getInstance().getConfig().getDouble("spawn.z"));
        e.getPlayer().teleport(spawn);

    }


    @EventHandler
    public void onHurt(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE)
            return;
        e.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(2.5).setY(1));
        player.setAllowFlight(true);
        player.playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 15);
    }

    /* UNCOMMENT THIS IF YOU DO NOT WANT INFINITE DOUBLE JUMP
    @EventHandler
    public void onHurt(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                player.setAllowFlight(true);
                e.setCancelled(true);
            }
        }

     */

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!e.getPlayer().isOp() && !e.getPlayer().hasPermission("world.breakblock") && !e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBreak(BlockPlaceEvent e) {
        if (!e.getPlayer().isOp() && !e.getPlayer().hasPermission("world.breakblock") && !e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

}
