package hardbuckaroo.loginmanager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {
    private final LoginManager plugin;
    public Listeners(LoginManager plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!plugin.getPlayerData().contains(player.getUniqueId().toString())) {
            if(plugin.getConfig().contains("FirstLoginMessage")) {
                for (String line : plugin.getConfig().getStringList("FirstLoginMessage")) {
                    player.sendRawMessage(line);
                }
            }
            else if(plugin.getConfig().contains("EveryLoginMessage")) {
                for (String line : plugin.getConfig().getStringList("EveryLoginMessage")) {
                    player.sendRawMessage(line);
                }
            }
            if(plugin.getConfig().contains("FirstLoginItems")) {
                for(String line : plugin.getConfig().getStringList("FirstLoginItems")) {
                    String[] items = line.split(",");
                    ItemStack itemStack = new ItemStack(Material.valueOf(items[0]),Integer.parseInt(items[1]));
                    player.getInventory().addItem(itemStack);
                }
            }
            plugin.getPlayerData().set(player.getUniqueId().toString(),player.getName());
            plugin.savePlayerData();
        } else {
            if(plugin.getConfig().contains("EveryLoginMessage")) {
                for (String line : plugin.getConfig().getStringList("EveryLoginMessage")) {
                    player.sendRawMessage(line);
                }
            }
        }
    }
}
