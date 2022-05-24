package com.willmexe.keiichisbuilderswand.events;

import com.willmexe.keiichisbuilderswand.GlobalVariables;
import com.willmexe.keiichisbuilderswand.items.ItemBuildersWand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player player = (Player) e.getPlayer();

        if (player.getItemInHand().getItemMeta() == null) {
            return;
        }
        if(player.getItemInHand().getItemMeta().getDisplayName().contains("Builder's Wand")) {
            if(ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()) == null) {
                ItemBuildersWand.wand_positions.put(player.getUniqueId().toString(), new ItemBuildersWand.wand_pos());
            }
        }

        if (player.getItemInHand().getItemMeta().getDisplayName().contains("Builder's Wand") && ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).state == ItemBuildersWand.State.select) {
            if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                e.setCancelled(true);
                if (ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos1 == null) {
                    ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos1 = e.getClickedBlock().getLocation();
                    player.sendMessage(GlobalVariables.alert_prefix + "You have set the first position with the Builder's Wand!");
                } else {
                    ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos2 = e.getClickedBlock().getLocation();
                    player.sendMessage(GlobalVariables.alert_prefix + "You have made a selection with the Builders Wand. Click a block in your inventory to fill the selection or rightclick to remove the selection.");
                }
            } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (player.getItemInHand().getItemMeta().getDisplayName().contains("Builder's Wand")) {
                    if (ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()) != null) {
                        player.performCommand("selectionclear");
                    }
                }
            }
        } else if(player.getItemInHand().getItemMeta().getDisplayName().contains("Builder's Wand") && ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).state == ItemBuildersWand.State.build) {
            player.sendMessage(GlobalVariables.error_prefix + "You can't use the wand while another selection is in progress.");
        }
    }

}
