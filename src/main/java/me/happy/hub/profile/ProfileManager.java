package me.happy.hub.profile;

import me.happy.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager implements Listener {

    // FROM WENJAPVP
    private Map<UUID, Profile> profile;

    public ProfileManager() {
        this.profile = new HashMap<>();

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            Profile data = new Profile(player.getUniqueId(), player.getName());
            this.profile.put(player.getUniqueId(), data);
        }

        Bukkit.getPluginManager().registerEvents(this, Hub.getInstance());
    }

    public Profile getPlayerData(Player player) {
        return profile.get(player.getUniqueId());
    }

    public void savePlayerData() {
        for (Profile data : profile.values()) {
            data.save();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        profile.put(player.getUniqueId(), new Profile(player.getUniqueId(), player.getName()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (profile.containsKey(player.getUniqueId())) {
            profile.get(player.getUniqueId()).save();

            new BukkitRunnable() {
                public void run() {
                    profile.remove(player.getUniqueId());
                }
            }.runTaskLater(Hub.getInstance(), 2L);
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();

        if (profile.containsKey(player.getUniqueId())) {
            profile.get(player.getUniqueId()).save();

            new BukkitRunnable() {
                public void run() {
                    profile.remove(player.getUniqueId());
                }
            }.runTaskLater(Hub.getInstance(), 2L);
        }
    }

}
