package com.willmexe.keiichisbuilderswand.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichisbuilderswand.items.ItemBuildersWand;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRenderSelection implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player)sender;

        if(ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()) == null) {
            sender.sendMessage(GlobalVariables.error_prefix + "Selection is not set!");
            return true;
        }

        Location pos1 = ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos1;
        Location pos2 = ItemBuildersWand.wand_positions.get(player.getUniqueId().toString()).pos2;

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

        for(var x = startX; x < endX; x++) {
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x, startY, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x + 0.5, startY, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x, endY, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x + 0.5, endY, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x, startY, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x + 0.5, startY, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x, endY, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x + 0.5, endY, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
        }
       for(var z = startZ; z < endZ; z++) {
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, startY, z), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, startY, z + 0.5), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, endY, z), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, endY, z + 0.5), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, endY, z), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, endY, z + 0.5), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, startY, z), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, startY, z + 0.5), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
       }
       for(var y = startY; y < endY; y++) {
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, y, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, y + 0.5, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, y, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, y + 0.5, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, y, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), startX, y + 0.5, endZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, y, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
           player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), endX, y + 0.5, startZ), 1, new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255, 255), 1));
       }

        return true;
    }
}
