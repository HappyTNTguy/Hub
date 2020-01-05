package me.happy.hub.cosmetic;

import me.happy.hub.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CosmeticsInventory {

    public static void inv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Cosmetics");
        ItemStack RAINBOW_ARMOR = new ItemBuilder(Material.WOOL, 1, (byte) 3).setName(ChatColor.GOLD + "Rainbow Armor").toItemStack();
        inv.setItem(0, RAINBOW_ARMOR);

        player.openInventory(inv);
    }
}
