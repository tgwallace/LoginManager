package hardbuckaroo.loginmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LoginManager extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new Listeners(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
