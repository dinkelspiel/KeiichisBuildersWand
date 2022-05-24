package com.willmexe.keiichisbuilderswand.items;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemBuildersWand {
    public enum State {
        select,
        build
    }
    public static class wand_pos {
        public Location pos1;
        public Location pos2;
        public State state = State.select;
        public List<Location> blocks = new ArrayList<>();
        public Material block;
    }

    public static String name = "§fBuilder's Wand";

    public static HashMap<String, wand_pos> wand_positions = new HashMap<>();

    public static void init() {
        ItemStack builderswand = new ItemStack(Material.NETHERITE_AXE, 1);
        ItemMeta metaBuildersawnd = builderswand.getItemMeta();
        metaBuildersawnd.setDisplayName(name);
        builderswand.setItemMeta(metaBuildersawnd);

        ShapelessRecipe recipeBuilderswand = new ShapelessRecipe(builderswand);

        recipeBuilderswand.addIngredient(Material.NETHERITE_AXE);
        recipeBuilderswand.addIngredient(Material.NETHER_STAR);

        KeiichisCore.getPlugin().getServer().addRecipe(recipeBuilderswand);
    }

    public static boolean fillBlocks(Player sender, Location pos1, Location pos2, Material block) {
        if(pos1.getWorld() != pos2.getWorld()) {
            sender.sendMessage(GlobalVariables.error_prefix + "Locations are not in the same world! Aborting.");
            return false;
        }

        int startX = 0;
        int endX = 0;
        int startZ = 0;
        int endZ = 0;
        int startY = 0;
        int endY = 0;

        if(pos1.getX() < pos2.getX()) {
            startX = (int)pos1.getX();
            endX = (int)pos2.getX() + 1;
        } else {
            startX = (int)pos2.getX();
            endX = (int)pos1.getX() + 1;
        }

        if(pos1.getZ() < pos2.getZ()) {
            startZ = (int)pos1.getZ();
            endZ = (int)pos2.getZ() + 1;
        } else {
            startZ = (int)pos2.getZ();
            endZ = (int)pos1.getZ() + 1;
        }

        if(pos1.getY() < pos2.getY()) {
            startY = (int)pos1.getY();
            endY = (int)pos2.getY() + 1;
        } else {
            startY = (int)pos2.getY();
            endY = (int)pos1.getY() + 1;
        }

        int w = endX - startX;
        int h = endZ - startZ;
        int d = endY - startY;
        int totalBlocks = w * h * d;
        int blocksInInventory = 0;

        for(ItemStack item : sender.getInventory().getContents()) {
            if(item == null) {
                continue;
            }

            if(item.getType() == block) {
                blocksInInventory += item.getAmount();
            }
        }

        if(blocksInInventory < totalBlocks) {
            sender.sendMessage(GlobalVariables.error_prefix + "You do not have enough blocks in your inventory! " + totalBlocks + " are needed.");
            return false;
        }

        blocksInInventory = totalBlocks;
        for(int itemslot = 0; itemslot < sender.getInventory().getSize(); itemslot++) {
            ItemStack item = sender.getInventory().getItem(itemslot);
            if(item == null) {
                continue;
            }
            if(item.getType() == block && blocksInInventory > 0) {
                int removeAmount = item.getAmount();
                if(item.getAmount() > blocksInInventory) {
                    sender.getInventory().getItem(itemslot).setAmount(item.getAmount() - blocksInInventory);
                } else {
                    ItemStack air = new ItemStack(Material.AIR);
                    sender.getInventory().setItem(itemslot, air);
                }
                blocksInInventory -= removeAmount;
            }
        }

        wand_positions.get(sender.getUniqueId().toString()).block = block;
        for(var x = startX; x < endX; x++) {
            for(var z = startZ; z < endZ; z++) {
                for(var y = startY; y < endY; y++) {
                    Location loc = new Location(pos1.getWorld(), x, y, z);
                    wand_positions.get(sender.getUniqueId().toString()).blocks.add(loc);

//                    pos1.getWorld().setBlockData(loc, block.createBlockData());
                }
            }
        }
        Collections.shuffle(wand_positions.get(sender.getUniqueId().toString()).blocks);
        return true;
    }

    public static void renderSelections() {
        for(var i : wand_positions.entrySet())  {
            if(i.getValue().pos1 != null && i.getValue().pos2 != null) {
                UUID uuid = UUID.fromString(i.getKey().toString());
                if(KeiichisCore.getPlugin().getServer().getPlayer(uuid) != null) {
                    KeiichisCore.getPlugin().getServer().getPlayer(uuid).performCommand("renderselection");
                }
            }
        }
    }

    public static void placeBlocks() {
        for(var i : wand_positions.entrySet())  {
            if(i.getValue().pos1 != null && i.getValue().pos2 != null) {
                UUID uuid = UUID.fromString(i.getKey().toString());
                if(KeiichisCore.getPlugin().getServer().getPlayer(uuid) != null) {
                    if(wand_positions.get(i.getKey()).state == ItemBuildersWand.State.build) {
                        KeiichisCore.getPlugin().getServer().getPlayer(uuid).performCommand("renderselection");
                        if(wand_positions.get(i.getKey()).blocks.size() > 0) {
                            wand_positions.get(i.getKey()).pos1.getWorld().setBlockData(
                                    wand_positions.get(i.getKey()).blocks.get(0),
                                    wand_positions.get(i.getKey()).block.createBlockData());
                            wand_positions.get(i.getKey()).blocks.remove(0);
                        } else {
                            wand_positions.remove(i.getKey());
                        }
                    }
                }
            }
        }
    }
}
