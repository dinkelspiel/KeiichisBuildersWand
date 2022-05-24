package com.willmexe.keiichisbuilderswand.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichisbuilderswand.items.ItemBuildersWand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSelectionClear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player) sender;

        ItemBuildersWand.wand_positions.remove(player.getUniqueId().toString());
        player.sendMessage(GlobalVariables.success_prefix + "Cleared Selection!");

        return true;
    }
}
