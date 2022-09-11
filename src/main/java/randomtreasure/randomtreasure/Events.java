package randomtreasure.randomtreasure;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Events implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        new BlockPlace().place(event);
    }
}
