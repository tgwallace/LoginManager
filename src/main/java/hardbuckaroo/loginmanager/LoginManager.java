package hardbuckaroo.loginmanager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class LoginManager extends JavaPlugin {

    private File playerDataFile;
    private FileConfiguration playerData;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        createCustomConfigs();

        Bukkit.getPluginManager().registerEvents(new Listeners(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FileConfiguration getPlayerData() {
        return playerData;
    }

    public void savePlayerData(){
        try {
            this.playerData.save(this.playerDataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createCustomConfigs() {
        playerDataFile = new File(getDataFolder(), "playerData.yml");
        if (!playerDataFile.exists()) {
            playerDataFile.getParentFile().mkdirs();
            saveResource("playerData.yml", false);
        }

        playerData = new YamlConfiguration();
        try {
            playerData.load(playerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
