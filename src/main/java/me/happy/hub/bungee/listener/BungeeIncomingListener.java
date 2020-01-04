package me.happy.hub.bungee.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.happy.hub.Hub;
import net.minecraft.util.com.google.common.primitives.Ints;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class BungeeIncomingListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equals("BungeeCord")) return;
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
            String subChannel = in.readUTF();
            if (subChannel.equals("PlayerCount")) {
                String server = in.readUTF();
                if (server.equalsIgnoreCase("Leagues")) {
                    Hub.getInstance().getBungeeHandler().getServerCount().put(server.toLowerCase(), in.readInt());
                }
                if (server.equalsIgnoreCase("Kitmap")) {
                    Hub.getInstance().getBungeeHandler().getServerCount().put(server.toLowerCase(), in.readInt());
                }
                if (server.equalsIgnoreCase("Practice")) {
                    Hub.getInstance().getBungeeHandler().getServerCount().put(server.toLowerCase(), in.readInt());
                }
                if (server.equals("ALL")) {
                    Hub.getInstance().getBungeeHandler().setPlayerCount(in.readInt());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
