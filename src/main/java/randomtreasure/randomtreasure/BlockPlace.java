package randomtreasure.randomtreasure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static randomtreasure.randomtreasure.SettingsLoad.weightItem;

public class BlockPlace {
    public void place(BlockPlaceEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location location = event.getBlock().getLocation();

        if(player.getInventory().getItemInMainHand().getType()==Material.CHEST && player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.MENDING)){
            event.setCancelled(true);
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);

            double random = Math.random();
            double total = 0.0;

            for (Map.Entry<Double,ItemStack> entry : weightItem.entrySet()){
                if(total <= random && random < total + entry.getKey()){
                    ItemStack itemStack = entry.getValue();
                    world.dropItemNaturally(location,itemStack);
                    return;
                }else{
                    total += entry.getKey();
                }
            }
        }
    }
}
