package me.happy.hub;

import com.bizarrealex.azazel.Azazel;
import com.bizarrealex.azazel.tab.example.ExampleTabAdapter;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import me.happy.hub.bungee.BungeeHandler;
import me.happy.hub.command.ReloadConfigCommand;
import me.happy.hub.cosmetic.Armor;
import me.happy.hub.listeners.EnderButtListener;
import me.happy.hub.listeners.InventoryListener;
import me.happy.hub.listeners.PlayerListener;
import me.happy.hub.profile.ProfileManager;
import me.happy.hub.scoreboard.ScoreboardLink;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hub extends JavaPlugin {

    @Getter private static Hub instance;
    @Getter private BungeeHandler bungeeHandler;
    @Getter private ProfileManager profileManager;
    @Getter private Chat chat;
    @Getter private List<String> serverNames = new ArrayList<>();

    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        this.bungeeHandler = new BungeeHandler(this);
        this.profileManager = new ProfileManager();

        getCommand("rc").setExecutor(new ReloadConfigCommand());

        this.setupChat();

        this.registerListeners();

        Armor.makeClockAndChangingTimers();

        Assemble assemble = new Assemble(this, new ScoreboardLink());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.MODERN);
        new Azazel(this, new ExampleTabAdapter());

        for (String s : this.getConfig().getStringList("servers")) {
            serverNames.add(s.toLowerCase());
        }
    }

    public void onDisable() {
        instance = null;
    }

    //TODO: CONFIG.YML LOCATION

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerListener(),
                new EnderButtListener(),
                new InventoryListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }
}
