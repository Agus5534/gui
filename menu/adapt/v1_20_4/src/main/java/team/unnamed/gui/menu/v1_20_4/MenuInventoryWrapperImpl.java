package team.unnamed.gui.menu.v1_20_4;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.adapt.MenuInventoryWrapper;
import team.unnamed.gui.menu.type.MenuInventory;

public class MenuInventoryWrapperImpl implements MenuInventoryWrapper {

    private final MenuInventory menuInventory;

    private final InventoryHolder owner;

    public MenuInventoryWrapperImpl(
            InventoryHolder owner,
            MenuInventory menuInventory
    ) {
        this.owner = owner;
        this.menuInventory = menuInventory;
    }

    @Override
    public @NotNull Inventory getRawInventory() {
        try {
            var craftInventoryCustom = Class.forName("org.bukkit.craftbukkit.inventory.CraftInventoryCustom");

            var constructor = craftInventoryCustom.getDeclaredConstructor(
                    InventoryHolder.class,
                    int.class,
                    String.class);

            return (Inventory) constructor.newInstance(owner, menuInventory.getSlots(), menuInventory.getTitle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull MenuInventory getMenuInventory() {
        return menuInventory;
    }

}
