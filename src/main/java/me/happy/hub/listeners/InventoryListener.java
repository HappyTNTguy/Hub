package me.happy.hub.listeners;

import me.happy.hub.Hub;
import me.happy.hub.cosmetic.CosmeticsInventory;
import me.happy.hub.inventory.ServerSelector;
import me.happy.hub.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            if (player.getItemInHand().getType() == Material.COMPASS) {
                ServerSelector.inv(player);
            }
            if (player.getItemInHand().getType() == Material.ENDER_CHEST) {
                CosmeticsInventory.inv(player);
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (!e.getInventory().getTitle().equals(ChatColor.GOLD + "Server Selector")) return;
        if (e.getCurrentItem() == null) return;
        if (!e.getCurrentItem().hasItemMeta()) return;

        e.setCancelled(true);
        e.setResult(Event.Result.DENY);
        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Server Selector")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Leagues")) {
                if (!Hub.getInstance().getBungeeHandler().getStatus(25566)) {
                    player.sendMessage(ChatColor.RED + "That server is not online!");
                }else {
                    player.chat("/play Leagues");
                }
            }
        }
    }

    @EventHandler
    public void onInvClick1(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (!e.getInventory().getTitle().equals(ChatColor.AQUA + "Cosmetics")) return;
        if (e.getCurrentItem() == null) return;
        if (!e.getCurrentItem().hasItemMeta()) return;

        e.setCancelled(true);
        e.setResult(Event.Result.DENY);
        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Cosmetics")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Rainbow Armor")) {
                if (!Hub.getInstance().getProfileManager().getPlayerData(player).isHasRainbowArmor()) {
                    Hub.getInstance().getProfileManager().getPlayerData(player).setHasRainbowArmor(true);
                    player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).toItemStack());
                    player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).toItemStack());
                    player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).toItemStack());
                    player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).toItemStack());
                    player.updateInventory();
                }else {
                    Hub.getInstance().getProfileManager().getPlayerData(player).setHasRainbowArmor(false);
                    player.getInventory().setHelmet(new ItemBuilder(Material.AIR).toItemStack());
                    player.getInventory().setChestplate(new ItemBuilder(Material.AIR).toItemStack());
                    player.getInventory().setLeggings(new ItemBuilder(Material.AIR).toItemStack());
                    player.getInventory().setBoots(new ItemBuilder(Material.AIR).toItemStack());
                    player.updateInventory();
                }
            }
        }
    }
}
