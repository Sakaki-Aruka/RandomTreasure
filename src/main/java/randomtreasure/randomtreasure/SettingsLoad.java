package randomtreasure.randomtreasure;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SettingsLoad {
    public static FileConfiguration FC;

    public void fc(FileConfiguration fileConfiguration){
        FC = fileConfiguration;
        this.configLoad();
    }

    public static Map<Double, ItemStack> weightItem = new HashMap<>();

    public void configLoad(){

        int items =FC.getInt("items");
        for (int loop = 0;loop < items; loop++){

            if(FC.contains("item"+loop)){
                String path = "item"+loop+".";

                Material material = Material.valueOf(FC.getString(path+"materialName"));
                String displayName = FC.getString(path+"displayName");

                ItemStack itemStack = new ItemStack(material);
                ItemMeta itemMeta = itemStack.getItemMeta();
                ItemMeta copy = itemStack.getItemMeta();
                itemMeta.setDisplayName(displayName);

                if(FC.contains(path+"enchantments")){
                    ArrayList<String> enchantName = new ArrayList<>(Arrays.asList(FC.getString(path+"enchantments").split(",")));
                    int loops = enchantName.size();

                    ArrayList<String> e1 = new ArrayList<>(Arrays.asList(FC.getString(path+"enchantments").split(",")));
                    ArrayList<String> e2 = new ArrayList<>(Arrays.asList(FC.getString(path+"enchantment_level").split(",")));
                    ArrayList<String> e3 = new ArrayList<>(Arrays.asList(FC.getString(path+"restriction").split(",")));
                    for (int i=0; i<loops;i++){
                        String ee1 = e1.get(i);
                        int ee2 = Integer.valueOf(e2.get(i));
                        boolean ee3 = Boolean.valueOf(e3.get(i));

                        itemStack.addUnsafeEnchantment(Enchantment.getByName(ee1),ee2);
                    }

                }

                if(FC.contains(path+"itemFlag")){
                    ArrayList<String> flags = new ArrayList<>(Arrays.asList(FC.getString(path+"itemFlag").split(",")));
                    for(String ii : flags){
                        if(!(ii.equalsIgnoreCase("Nothing"))){
                            itemMeta.addItemFlags(ItemFlag.valueOf(ii));
                        }

                    }
                }

                if(FC.contains(path+"Lore")){
                    ArrayList<String> lores = new ArrayList<>(Arrays.asList(FC.getString(path+"Lore").split("!&!")));
                    itemMeta.setLore(lores);
                }

                if(FC.contains(path+"unbreak")){
                    itemMeta.setUnbreakable(true);
                }

                double weight = FC.getDouble(path+"weight");
                if(!(itemMeta==copy)){
                    itemStack.setItemMeta(itemMeta);
                }

                weightItem.put(weight,itemStack);
            }else{
                break;
            }
        }
        return;
    }
}
