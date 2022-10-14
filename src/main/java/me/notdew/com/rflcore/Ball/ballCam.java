package me.notdew.com.rflcore.Ball;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ballCam implements CommandExecutor {
    public static ArrayList<Player> inballcam = new ArrayList<Player>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (inballcam.contains((Player)sender)) {
            inballcam.remove((Player)sender);
            sender.sendMessage(ChatColor.RED + "BallCam DISABLED!");
        } else {
            inballcam.add((Player)sender);
            sender.sendMessage(ChatColor.GREEN + "BallCam ENABLED!");
        }

        return false;
    }
}
