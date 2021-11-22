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

public class WeatherClickEvent implements Listener {

    @EventHandler
    public void handle(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase("§6§lWeather§1§c§3§4§f§a§8")) {
            event.setCancelled(true);
            World world = player.getWorld();
            switch (event.getSlot()) {
                case 1:
                    world.setThundering(false);
                    world.setStorm(false);
                    player.openInventory(InventoryManager.getGameRuleInventory(player));
                    break;
                case 2:
                    world.setStorm(true);
                    player.openInventory(InventoryManager.getGameRuleInventory(player));
                    break;
                case 3:
                    world.setThundering(true);
                    player.openInventory(InventoryManager.getGameRuleInventory(player));
                    break;
            }
            return;
        }
    }

}
