package team.unnamed.gui.menu.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import team.unnamed.gui.menu.adapt.MenuInventoryWrapper;
import team.unnamed.gui.menu.type.MenuInventory;
import team.unnamed.gui.menu.util.MenuUtil;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class InventoryCloseListener
        implements Listener {

    private final Plugin plugin;

    public InventoryCloseListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if (MenuUtil.isCustomMenu(inventory)) {
            MenuInventoryWrapper wrapper = MenuUtil.getAsWrapper(inventory);
            MenuInventory menuInventory = wrapper.getMenuInventory();
            Consumer<Player> action = menuInventory.getCloseAction();

            if (action != null) {
                Bukkit.getScheduler().runTaskLater(plugin, ()->action.accept((Player) event.getPlayer()), 2L);
            }
        }
    }

}
