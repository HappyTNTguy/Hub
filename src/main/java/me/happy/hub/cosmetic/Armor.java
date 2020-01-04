package me.happy.hub.cosmetic;

import me.happy.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Armor {

    private static int r;
    private static int g;
    private static int b = 20;
    private static int time = 59;
    
    public static void makeClockAndChangingTimers()
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Hub.getInstance(), () -> {
            switch (Armor.time)
            {
                case 59:
                    Armor.r = 255;
                    Armor.g = 0;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 58:
                    Armor.r = 255;
                    Armor.g = 68;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 57:
                    Armor.r = 255;
                    Armor.g = 111;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 56:
                    Armor.r = 255;
                    Armor.g = 171;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 55:
                    Armor.r = 255;
                    Armor.g = 255;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 54:
                    Armor.r = 188;
                    Armor.g = 255;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 53:
                    Armor.r = 128;
                    Armor.g = 255;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 52:
                    Armor.r = 43;
                    Armor.g = 255;
                    Armor.b = 0;
                    Armor.time -= 1;
                    break;
                case 51:
                    Armor.r = 0;
                    Armor.g = 255;
                    Armor.b = 9;
                    Armor.time -= 1;
                    break;
                case 50:
                    Armor.r = 0;
                    Armor.g = 255;
                    Armor.b = 51;
                    Armor.time -= 1;
                    break;
                case 49:
                    Armor.r = 0;
                    Armor.g = 255;
                    Armor.b = 111;
                    Armor.time -= 1;
                    break;
                case 48:
                    Armor.r = 0;
                    Armor.g = 255;
                    Armor.b = 162;
                    Armor.time -= 1;
                    break;
                case 47:
                    Armor.r = 0;
                    Armor.g = 255;
                    Armor.b = 230;
                    Armor.time -= 1;
                    break;
                case 46:
                    Armor.r = 0;
                    Armor.g = 239;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 45:
                    Armor.r = 0;
                    Armor.g = 196;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 44:
                    Armor.r = 0;
                    Armor.g = 173;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 43:
                    Armor.r = 0;
                    Armor.g = 162;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 42:
                    Armor.r = 0;
                    Armor.g = 137;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 41:
                    Armor.r = 0;
                    Armor.g = 100;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 40:
                    Armor.r = 0;
                    Armor.g = 77;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 39:
                    Armor.r = 0;
                    Armor.g = 34;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 38:
                    Armor.r = 17;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 37:
                    Armor.r = 37;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 36:
                    Armor.r = 68;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 35:
                    Armor.r = 89;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 34:
                    Armor.r = 102;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 33:
                    Armor.r = 124;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 32:
                    Armor.r = 154;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 31:
                    Armor.r = 222;
                    Armor.g = 0;
                    Armor.b = 255;
                    Armor.time -= 1;
                    break;
                case 30:
                    Armor.r = 255;
                    Armor.g = 0;
                    Armor.b = 247;
                    Armor.time -= 1;
                    break;
                case 29:
                    Armor.r = 255;
                    Armor.g = 0;
                    Armor.b = 179;
                    Armor.time -= 1;
                    break;
                case 28:
                    Armor.r = 255;
                    Armor.g = 0;
                    Armor.b = 128;
                    Armor.time = 59;
            }
            Color c = Color.fromRGB(Armor.r, Armor.g, Armor.b);
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (Hub.getInstance().getProfileManager().getPlayerData(p).isHasRainbowArmor()) {
                    if ((p.getInventory().getHelmet() != null) && (p.getInventory().getHelmet().getType() == Material.LEATHER_HELMET)) {
                        p.getInventory().setHelmet(Armor.getColorArmor(Material.LEATHER_HELMET, c));
                    }
                    if ((p.getInventory().getChestplate() != null) && (p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE)) {
                        p.getInventory().setChestplate(Armor.getColorArmor(Material.LEATHER_CHESTPLATE, c));
                    }
                    if ((p.getInventory().getLeggings() != null) && (p.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS)) {
                        p.getInventory().setLeggings(Armor.getColorArmor(Material.LEATHER_LEGGINGS, c));
                    }
                    if ((p.getInventory().getBoots() != null) && (p.getInventory().getBoots().getType() == Material.LEATHER_BOOTS)) {
                        p.getInventory().setBoots(Armor.getColorArmor(Material.LEATHER_BOOTS, c));
                    }
                }
            }
        }, 0L, 2L);
    }

    private static ItemStack getColorArmor(Material m, Color c)
    {
        ItemStack i = new ItemStack(m, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta)i.getItemMeta();
        meta.setColor(c);
        i.setItemMeta(meta);
        return i;
    }
}
