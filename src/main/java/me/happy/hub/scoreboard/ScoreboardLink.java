package me.happy.hub.scoreboard;

import com.sun.istack.internal.NotNull;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import me.happy.hub.Hub;
import me.signatured.ezqueuespigot.EzQueueAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardLink implements AssembleAdapter {

    // TODO: MAKE IT COMPATIBLE WITH A GOOD QUEUE PLUGIN
    @Override
    public String getTitle(Player player) {
        return ChatColor.translateAlternateColorCodes('&', Hub.getInstance().getConfig().getString("scoreboard.title"));
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();
        Hub.getInstance().getBungeeHandler().refreshCount("ALL");

        if (EzQueueAPI.getQueue(player) != null)
        {
            int spot = EzQueueAPI.getPosition(EzQueueAPI.getQueue(player));
            for (String s : Hub.getInstance().getConfig().getStringList("scoreboard.queuetext")) {
                String newString = ChatColor.translateAlternateColorCodes('&', s);
                newString.replaceAll("%spot%", String.valueOf(spot).replaceAll("%playersinqueue%", String.valueOf(EzQueueAPI.getQueueSize(EzQueueAPI.getQueue(player.getUniqueId())))));
                newString.replaceAll("%queuename%", getQueuedServer(player));
                lines.add(newString.replaceAll("%online%", String.valueOf(Hub.getInstance().getBungeeHandler().getPlayerCount())).replaceAll("%rank%", getRank(player)));
            }
            //scoreboard.add(Hub.this.getConfig().getString("scoreboard.queue"));
            //scoreboard.add(Hub.this.getConfig().getString("scoreboard.server").replaceAll("%server%", Hub.getQueuedServer(player)));
            //scoreboard.add(Hub.this.getConfig().getString("scoreboard.position").replaceAll("%spot%", String.valueOf(spot)).replaceAll("%max%", String.valueOf(EzQueueAPI.getQueueSize(Hub.getQueuedServer(player)))));
            //scoreboard.add("");
        }

        lines.clear();
        for (String s : Hub.getInstance().getConfig().getStringList("scoreboard.text")) {
            String newString = ChatColor.translateAlternateColorCodes('&', s);
            lines.add(newString.replaceAll("%online%", String.valueOf(Hub.getInstance().getBungeeHandler().getPlayerCount())).replaceAll("%rank%", getRank(player)));
        }
        return lines;
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

    public static String getQueuedServer(Player p)
    {
        String server = EzQueueAPI.getQueue(p);
        if (server.equalsIgnoreCase("leagues")) {
            return "Leagues";
        }
        return null;
    }

}
