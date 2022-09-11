package randomtreasure.randomtreasure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

import static randomtreasure.randomtreasure.SettingsLoad.weightItem;

public class BlockPlace {
    public void place(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if(event.getBlock().getType()== Material.CHEST && player.getInventory().getItemInMainHand().getType()==Material.CHEST && player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.MENDING)){
            event.setCancelled(true);
            Random random = new Random();
            Double randomDouble = random.nextDouble();
            Double weightTotal = 0.0;

            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);

            for(Map.Entry<Double, ItemStack> entry : weightItem.entrySet()){
                if(entry.getKey() < randomDouble && randomDouble <= weightTotal){
                    Location location = event.getBlock().getLocation();
                    ItemStack itemStack = entry.getValue();
                    World world = player.getWorld();
                    world.dropItemNaturally(location,itemStack);
                    return;
                }
                weightTotal += entry.getKey();
            }
        }
    }
}
