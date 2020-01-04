package me.happy.hub.inventory;

import me.happy.hub.Hub;
import me.happy.hub.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSelector {

    public static void inv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Server Selector");

        ItemStack SPACER = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7).setName(" ").toItemStack();

        for (int i = 0; i < 27; i++) {
            inv.setItem(i, SPACER);
        }

        ItemStack LEAGUES = new ItemBuilder(Material.DIAMOND_SWORD, 1).setName(ChatColor.RED + "Leagues").setLore(getLore("Leagues", 25566)).toItemStack();

        inv.setItem(10, LEAGUES);

        player.openInventory(inv);
    }

    private static List<String> getLore(String servername, int port) {
        List<String> lore = new ArrayList<>();
        Hub.getInstance().getBungeeHandler().refreshCount(servername);
        for (String s : Hub.getInstance().getConfig().getStringList(servername + ".lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s).replaceAll("%status%", Hub.getInstance().getBungeeHandler().getStatus(port) ? ChatColor.GREEN + "Online" : ChatColor.RED + "Offline")
            .replaceAll("%" + servername.toLowerCase() + "players%", String.valueOf(Hub.getInstance().getBungeeHandler().getServerCount().get(servername.toLowerCase()))));
        }
        return lore;
    }
}
