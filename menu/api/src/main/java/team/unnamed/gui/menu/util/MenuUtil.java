package team.unnamed.gui.menu.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.adapt.MenuInventoryWrapper;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.type.MenuInventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public final class MenuUtil {

    private static final Constructor<?> WRAPPER_CONSTRUCTOR;


    private enum VersionWrapper {
        V1_17("1.17", "v1_17"),
        V1_17_1("1.17.1", "v1_17"),
        V1_18("1.18", "v1_18"),
        V1_18_1("1.18.1", "v1_18"),
        V1_18_2("1.18.2", "v1_18_2"),
        V1_19("1.19", "v1_19"),
        V1_19_1("1.19.1", "v1_19"),
        V1_19_2("1.19.2", "v1_19"),
        V1_19_3("1.19.3", "v1_19_2"),
        V1_19_4("1.19.4", "v1_19_3"),
        V1_20("1.20", "v1_20"),
        V1_20_1("1.20.1", "v1_20"),
        V1_20_2("1.20.2", "v1_20_2"),
        V1_20_3("1.20.3", "v1_20_3"),
        V1_20_4("1.20.4", "v1_20_3"),
        V1_20_5("1.20.5", "v1_20_4"),
        V1_20_6("1.20.6", "v1_20_4"),
        V1_21("1.21", "v1_21");

        private String version;
        private String packageName;

         VersionWrapper(String version, String packageName) {
            this.version = version;
            this.packageName = packageName;
        }

        public String getPackageName() {
            return packageName;
        }

        public String getVersion() {
            return version;
        }

        public static VersionWrapper getByVersion(String version) {
             var list = Arrays.stream(VersionWrapper.values())
                     .filter(versionWrapper -> versionWrapper.version.equalsIgnoreCase(version))
                     .toList();

             return list.get(0);
        }
    }
    static {
        try {
            WRAPPER_CONSTRUCTOR = Class.forName(
                    "team.unnamed.gui.menu." + VersionWrapper.getByVersion(Bukkit.getServer().getMinecraftVersion()).getPackageName()
                            + ".MenuInventoryWrapperImpl"
            ).getConstructor(InventoryHolder.class, MenuInventory.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new ExceptionInInitializerError("Your server version isn't supported for ungui.");
        }
    }

    private MenuUtil() {
        // the class shouldn't be instantiated
        throw new UnsupportedOperationException();
    }

    public static void fillItemList(List<ItemClickable> items, int slots) {
        for (int i = 0; i < slots; i++) {
            items.add(null);
        }
    }

    public static final @NotNull Byte versionNumber =  Byte.parseByte(Bukkit.getMinecraftVersion().split("\\.")[1]);
    public static final @NotNull Byte patchNumber = Bukkit.getMinecraftVersion().split("\\.").length < 3 ? 0 :  Byte.parseByte(Bukkit.getMinecraftVersion().split("\\.")[2]);


    public static @NotNull Inventory parseToInventory(MenuInventory menuInventory) {
        try {
            MenuInventoryWrapper wrapper
                    = (MenuInventoryWrapper) WRAPPER_CONSTRUCTOR.newInstance(
                    null, menuInventory);

            return wrapper.getRawInventory();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ExceptionInInitializerError(
                    "An error has occurred while creating menu "
                            + menuInventory.getTitle());
        }
    }

    public static boolean isCustomMenu(Inventory inventory) {
        if (inventory == null) {
            return false;
        }

        InventoryHolder holder = inventory.getHolder();

        return holder instanceof MenuInventoryWrapper
                || inventory instanceof MenuInventoryWrapper;
    }

    public static MenuInventoryWrapper getAsWrapper(Inventory inventory) {
        InventoryHolder holder = inventory.getHolder();

        return holder == null ?
                (MenuInventoryWrapper) inventory :
                (MenuInventoryWrapper) holder;
    }

}
