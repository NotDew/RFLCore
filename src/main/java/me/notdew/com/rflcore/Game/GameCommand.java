package me.notdew.com.rflcore.Game;

import me.notdew.com.rflcore.Field.FieldCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GameCommand implements CommandExecutor {
    public static HashMap<Player, GameObject> players = new HashMap<Player, GameObject>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            return false;
        }
        if (args.length == 2) {
            if (args[0].equals("create")) {
                if (FieldCommand.fieldnames.containsKey(args[1])) {
                    players.put(p, new GameObject(args[1], p));

                } else {
                    p.sendMessage(ChatColor.RED + "Field Not Found.");
                    return false;
                }

            }
        }
        return false;
    }
}
