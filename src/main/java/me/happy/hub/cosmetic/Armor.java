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
    private static boolean countdown = false;
    
    public static void makeClockAndChangingTimers() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Hub.getInstance(), () -> {
                Color c = Color.fromRGB(Armor.r, Armor.g, Armor.b);
                if (!countdown) {
                    if (r < 255) {
                        r += 5;
                    }
                    if (r == 255 && g < 255) {
                        g += 5;
                    }

                    if (r == 255 && g == 255 && b < 255) {
                        b += 5;
                    }

                    if (r == 255 & g == 255 && b == 255) {
                        //r = 0;
                        //g = 0;
                        //b = 0;
                        // r += 5;
                        countdown = true;
                    }
                }else if (countdown) {
                    if (r > 0) {
                        r -= 5;
                    }
                    if (r == 0 && g > 0) {
                        g -= 5;
                    }
                    if (r == 0 && g == 0 && b > 0) {
                        b -= 5;
                    }
                    if (r == 0 && g == 0 && b == 0)  {
                        countdown = false;
                    }
                }

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

    private static ItemStack getColorArmor(Material m, Color c) {
        ItemStack i = new ItemStack(m, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta)i.getItemMeta();
        meta.setColor(c);
        i.setItemMeta(meta);
        return i;
    }
}
