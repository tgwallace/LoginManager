package hardbuckaroo.loginmanager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class Listeners implements Listener {
    private final LoginManager plugin;
    public Listeners(LoginManager plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String firstMessage = plugin.getConfig().getString("FirstLoginMessage");
        String message = plugin.getConfig().getString("EveryLoginMessage");
        if(!plugin.getPlayerData().contains(player.getUniqueId().toString())) {
            if(firstMessage != null && !firstMessage.isEmpty()) player.sendRawMessage(firstMessage);
            else if(message != null && !message.isEmpty()) player.sendRawMessage(message);
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
            if(message != null && !message.isEmpty()) player.sendRawMessage(message);
        }
    }
}
