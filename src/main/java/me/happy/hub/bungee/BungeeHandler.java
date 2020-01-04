package me.happy.hub.bungee;

import lombok.Getter;
import lombok.Setter;
import me.happy.hub.Hub;
import me.happy.hub.bungee.listener.BungeeIncomingListener;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class BungeeHandler {

    private Hub plugin;
    @Getter @Setter private int playerCount;
    @Getter @Setter private Map<String, Integer> serverCount = new HashMap<>();

    public BungeeHandler(Hub plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this.plugin, "BungeeCord");
        this.plugin.getServer().getMessenger().registerIncomingPluginChannel(this.plugin, "BungeeCord", new BungeeIncomingListener());
    }

    public void refreshCount(String server)
    {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("PlayerCount");
            out.writeUTF(server);
            Bukkit.getServer().sendPluginMessage(this.plugin, "BungeeCord", out.toByteArray());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getStatus(int port) {
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress(Hub.getInstance().getServer().getIp(), port), 15);
            s.close();
            return true;
        } catch (Exception e) {
            // not online
            return false;
        }
    }
}
