package randomtreasure.randomtreasure;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

import static randomtreasure.randomtreasure.SettingsLoad.weightItem;

public final class RandomTreasure extends JavaPlugin implements CommandExecutor {

    public void load(){
        FileConfiguration fileConfiguration = getConfig();
        new SettingsLoad().fc(fileConfiguration);
    }

    @Override
    public void onEnable() {
        this.load();
        saveDefaultConfig();
        getCommand("randomtreasureconfigreload").setExecutor(this);
        getServer().getPluginManager().registerEvents(new Events(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(sender instanceof Player){
            if(!(sender.isOp())){
                return false;
            }
        }
        weightItem.clear();

        sender.sendMessage("§c[RandomTreasure]:ConfigFile reloading.");
        reloadConfig();
        this.load();
        for(Map.Entry<Double, ItemStack> entry : weightItem.entrySet()){
            sender.sendMessage("weight:"+entry.getKey()+"\nitem:"+entry.getValue());
        }

        sender.sendMessage("§a[RandomTreasure]:ConfigFile reload complete!");
        return true;
    }
}
