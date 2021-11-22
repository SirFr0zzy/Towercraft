package de.sirfrozzy.multiworldsystem.events;

import de.sirfrozzy.multiworldsystem.utils.InventoryManager;
import de.sirfrozzy.multiworldsystem.utils.ItemBuilder;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimeClickEvent implements Listener {

    @EventHandler
    public void handle(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase("§6§lTime§1§2§c§5§b§f§a")) {
            event.setCancelled(true);
            World world = player.getWorld();
            switch (event.getSlot()) {
                case 0:
                    world.setTime(0);
                    player.openInventory(InventoryManager.getGameRuleInventory(player));
                    break;
                case 1:
                    world.setTime(6000);
                    player.openInventory(InventoryManager.getGameRuleInventory(player));
                    break;
                case 3:
                    world.setTime(12000);
                    player.openInventory(InventoryManager.getGameRuleInventory(player));
                    break;
                case 4:
                    world.setTime(18000);
                    player.openInventory(InventoryManager.getGameRuleInventory(player));
                    break;
            }

        }
    }

}
