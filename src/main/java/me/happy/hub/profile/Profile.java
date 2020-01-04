package me.happy.hub.profile;

import lombok.Getter;
import lombok.Setter;
import me.happy.hub.Hub;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

@Getter
@Setter
public class Profile {

    private UUID uuid;
    private String name;

    private File file;
    private FileConfiguration config;

    private boolean loaded;
    private boolean hasRainbowArmor;

    public Profile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.file = new File(Hub.getInstance().getDataFolder() + File.separator + "playerdata" + File.separator + uuid.toString() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);

        this.load();
        loaded = true;
    }

    public void save(){
        this.config.set("rainbowArmor", this.hasRainbowArmor);
    }

    private void load() {
        this.hasRainbowArmor = this.config.getBoolean("rainbowArmor");
    }

}
