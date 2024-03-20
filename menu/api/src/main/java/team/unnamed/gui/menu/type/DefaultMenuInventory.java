package team.unnamed.gui.menu.type;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.util.MenuUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DefaultMenuInventory implements MenuInventory {

    protected final String title;
    protected final int slots;
    protected final List<ItemClickable> items;
    protected final Consumer<Player> openAction;
    protected final Consumer<Player> closeAction;
    protected final boolean canIntroduceItems;

    protected DefaultMenuInventory(
            String title, int slots,
            List<ItemClickable> items,
            Consumer<Player> openAction,
            Consumer<Player> closeAction,
            boolean canIntroduceItems
    ) {
        this.title = title;
        this.slots = slots;
        this.items = items;
        this.openAction = openAction;
        this.closeAction = closeAction;
        this.canIntroduceItems = canIntroduceItems;
    }

    @NotNull
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getSlots() {
        return slots;
    }

    @NotNull
    @Override
    public List<ItemClickable> getItems() {
        return items;
    }

    @Override
    public void clearItems() {
        this.items.clear();
        MenuUtil.fillItemList(this.items, this.slots);
    }

    @Override
    public void setItem(ItemClickable item) {
        this.items.set(item.getSlot(), item);
    }

    @Override
    public void removeItem(int slot) {
        this.items.remove(slot);
    }

    @Nullable
    @Override
    public Consumer<Player> getOpenAction() {
        return openAction;
    }

    @Nullable
    @Override
    public Consumer<Player> getCloseAction() {
        return closeAction;
    }

    @Override
    public boolean canIntroduceItems() {
        return canIntroduceItems;
    }

}
