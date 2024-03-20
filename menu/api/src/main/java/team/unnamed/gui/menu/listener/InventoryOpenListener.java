package team.unnamed.gui.menu.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.adapt.MenuInventoryWrapper;
import team.unnamed.gui.menu.type.MenuInventory;
import team.unnamed.gui.menu.util.MenuUtil;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class InventoryOpenListener
        implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();

        if (MenuUtil.isCustomMenu(inventory)) {
            MenuInventoryWrapper wrapper = MenuUtil.getAsWrapper(inventory);
            MenuInventory menuInventory = wrapper.getMenuInventory();
            Consumer<Player> action = menuInventory.getOpenAction();

            if(action != null) {
                action.accept((Player) event.getPlayer());
            }
        }
    }

}
