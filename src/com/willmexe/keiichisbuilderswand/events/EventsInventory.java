package com.willmexe.keiichisbuilderswand.events;

import com.willmexe.keiichisbuilderswand.GlobalVariables;
import com.willmexe.keiichisbuilderswand.items.ItemBuildersWand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EventsInventory implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) {
            return;
        }

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Crafting")) {
            if(ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()) == null) {
                return;
            }
            if(ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos1 != null && ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos2 != null && ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).state == ItemBuildersWand.State.select) {
                e.setCancelled(true);

                if(e.getClickedInventory().getItem(e.getSlot()) == null) {
                    return;
                }

                Material block = e.getClickedInventory().getItem(e.getSlot()).getType();
                if(!block.isBlock()) {
                    player.sendMessage(GlobalVariables.error_prefix + "Clicked item is not a block! Try again.");
                    return;
                }
                if(ItemBuildersWand.fillBlocks(player, ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos1, ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos2, block)) {
                    player.sendMessage(GlobalVariables.success_prefix + "You have started filling a selection!");
                    player.closeInventory();
                    ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).state = ItemBuildersWand.State.build;
                }
            }
        }
    }
}
