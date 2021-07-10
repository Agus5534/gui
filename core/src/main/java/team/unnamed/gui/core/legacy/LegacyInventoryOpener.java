package team.unnamed.gui.core.legacy;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import team.unnamed.gui.core.version.ServerVersionProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LegacyInventoryOpener {

	private static Method legacyMethod;

	private static Method getLegacyMethod() {
		if (legacyMethod == null)
			try {
				Class<?> legacy = Class.forName("team.unnamed.gui.v1_7_R4.LegacyInventory");
				legacyMethod = legacy.getDeclaredMethod("openCustomInventory", Player.class, Inventory.class);
				legacyMethod.setAccessible(true);
			} catch (NoSuchMethodException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		return legacyMethod;
	}

	public static void open(Player player, Inventory inventory) {
		if (ServerVersionProvider.SERVER_VERSION_INT != 7) {
			player.openInventory(inventory);
		} else {
			try {
				getLegacyMethod().invoke(null, player, inventory);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(
						"An error has occurred while invoke the menu " + inventory.getTitle(), e
				);
			}
		}
	}
}
