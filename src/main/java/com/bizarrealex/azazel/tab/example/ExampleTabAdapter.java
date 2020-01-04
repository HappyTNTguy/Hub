package com.bizarrealex.azazel.tab.example;

import com.bizarrealex.azazel.tab.TabAdapter;
import com.bizarrealex.azazel.tab.TabTemplate;
import me.happy.hub.Hub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ExampleTabAdapter implements TabAdapter {
    public TabTemplate getTemplate(Player player) {
        TabTemplate template = new TabTemplate();

        List<String> leftEntry = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            leftEntry.add(i - 1, Hub.getInstance().getConfig().getString("tab.left-" + i));
        }

        for (String s : leftEntry) {
            String serverName = "";
            if (s.endsWith("status%")) {
                if (Hub.getInstance().getServerNames().contains(s.replace("status%", "").replaceFirst("%", ""))) {
                    serverName = s.replace("status%", "").replaceFirst("%", "");
                }
            }
            if (s.contains("players%")) {
                if (Hub.getInstance().getServerNames().contains(s.replace("players%", "").replaceFirst("%", "").replaceAll("[0-9]", "").replaceAll("[^a-zA-Z]", ""))) {
                    serverName = s.replace("players%", "").replaceFirst("%", "").replaceAll("[0-9]", "").replaceAll("[^a-zA-Z]", "");
                }
            }

            template.left(s.replaceAll("%online%", String.valueOf(Hub.getInstance().getBungeeHandler().getPlayerCount()))
                    .replaceAll("%rank%", getRank(player)).replaceAll("%" + serverName + "players%", String.valueOf(Hub.getInstance().getBungeeHandler().getServerCount().get(serverName)))
                    .replaceAll("%" + serverName + "status%", Hub.getInstance().getBungeeHandler().getStatus(25566) ? ChatColor.GREEN + "Online" : ChatColor.RED + "Offline"));
        }
        List<String> middleEntry = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            middleEntry.add(i - 1, Hub.getInstance().getConfig().getString("tab.middle-" + i));
        }

        for (String s : middleEntry) {
            String serverName = "";
            if (s.endsWith("status%")) {
                if (Hub.getInstance().getServerNames().contains(s.replace("status%", "").replaceFirst("%", ""))) {
                    serverName = s.replace("status%", "").replaceFirst("%", "");
                }
            }
            if (s.contains("players%")) {
                if (Hub.getInstance().getServerNames().contains(s.replace("players%", "").replaceFirst("%", "").replaceAll("[0-9]", "").replaceAll("[^a-zA-Z]", ""))) {
                    serverName = s.replace("players%", "").replaceFirst("%", "");
                }
            }

            template.middle(s.replaceAll("%online%", String.valueOf(Hub.getInstance().getBungeeHandler().getPlayerCount()))
                    .replaceAll("%rank%", getRank(player)).replaceAll("%" + serverName + "players%", String.valueOf(Hub.getInstance().getBungeeHandler().getServerCount().get(serverName)))
                    .replaceAll("%" + serverName + "status%", Hub.getInstance().getBungeeHandler().getStatus(25566) ? ChatColor.GREEN + "Online" : ChatColor.RED + "Offline"));
        }
        List<String> rightEntry = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            rightEntry.add(i - 1, Hub.getInstance().getConfig().getString("tab.right-" + i));
        }

        for (String s : rightEntry) {
            String serverName = "";
            if (s.contains("players%")) {
                if (Hub.getInstance().getServerNames().contains(s.replace("players%", "").replaceFirst("%", "").replaceAll("[0-9]", "").replaceAll("[^a-zA-Z]", ""))) {
                    serverName = s.replace("players%", "").replaceFirst("%", "");
                }
            }
            if (s.endsWith("players%")) {
                if (Hub.getInstance().getServerNames().contains(s.replace("players%", "").replaceFirst("%", ""))) {
                    serverName = s.replace("players%", "").replaceFirst("%", "");
                }
            }

            template.right(s.replaceAll("%online%", String.valueOf(Hub.getInstance().getBungeeHandler().getPlayerCount()))
                    .replaceAll("%rank%", getRank(player)).replaceAll("%" + serverName + "players%", String.valueOf(Hub.getInstance().getBungeeHandler().getServerCount().get(serverName)))
                    .replaceAll("%" + serverName + "status%", Hub.getInstance().getBungeeHandler().getStatus(25566) ? ChatColor.GREEN + "Online" : ChatColor.RED + "Offline"));
        }

        return template;
    }

    private String getRank(Player player) {
        String rank = Hub.getInstance().getChat().getPrimaryGroup(player);
        if (rank == null) {
            rank = "Unknown";
        }

        ConfigurationSection rank_color = Hub.getInstance().getConfig().getConfigurationSection("ranks");
        String color = rank_color.getString(rank) == null ? ChatColor.WHITE.toString() : ChatColor.translateAlternateColorCodes('&', rank_color.getString(rank));

        return color + rank;
    }
}
