package com.willmexe.keiichisbuilderswand;

import com.willmexe.keiichisbuilderswand.commands.CommandRenderSelection;
import com.willmexe.keiichisbuilderswand.commands.CommandSelectionClear;
import com.willmexe.keiichisbuilderswand.events.EventsInventory;
import com.willmexe.keiichisbuilderswand.events.EventsPlayerInteract;
import com.willmexe.keiichiscore.gui.GuiCraftBook;
import com.willmexe.keiichisbuilderswand.items.ItemBuildersWand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class KeiichisBuildersWand extends JavaPlugin {
    private static KeiichisBuildersWand instance;

    public void schedule(long RenderTicks, long PlaceTicks) {
        int renderSelectionTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                () -> ItemBuildersWand.renderSelections(), 0L, RenderTicks);
        int placeBlocksTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                () -> ItemBuildersWand.placeBlocks(), 0L, PlaceTicks);
    }

    @Override
    public void onEnable() {
        instance = this;

        getCommand("selectionclear").setExecutor(new CommandSelectionClear());
        getCommand("renderselection").setExecutor(new CommandRenderSelection());

        getServer().getPluginManager().registerEvents(new EventsInventory(), this);
        getServer().getPluginManager().registerEvents(new EventsPlayerInteract(), this);

        ItemBuildersWand.init();

        getServer().getConsoleSender().sendMessage(GlobalVariables.title + "Initialized");

        GuiCraftBook.add("§fBuilder's Wand", Material.NETHERITE_AXE, "\uEFD8");

        schedule(10, 2);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(GlobalVariables.title + "Disabled");
    }

    public static KeiichisBuildersWand getPlugin() {
        return instance;
    }
}
