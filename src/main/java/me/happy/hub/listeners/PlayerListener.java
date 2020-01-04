package me.happy.hub.listeners;

import me.happy.hub.Hub;
import me.happy.hub.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

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
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) { //Double Jump Part 1 Main
        Player player = e.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE)
            return;
        e.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(2.5).setY(1));
        player.getWorld().playSound(player.getLocation(), Sound.BLAZE_HIT, 1.0F, 1.0F);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) { //Double Jump Part 2
        Player player = e.getPlayer();
        if ((player.getGameMode() != GameMode.CREATIVE)
                && (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
                && (!player.isFlying()))
            player.setAllowFlight(true);
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
