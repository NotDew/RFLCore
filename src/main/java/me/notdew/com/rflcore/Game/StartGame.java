package me.notdew.com.rflcore.Game;

import me.notdew.com.rflcore.RFLCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import static me.notdew.com.rflcore.RFLCore.Teams;

public class StartGame implements CommandExecutor {

    public static HashMap<String, GameObject> games = new HashMap<String, GameObject>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if(args.length == 2) {
            String team1 = args[0];
            String team2 = args[1];
            if (!(Teams.contains(team1) && Teams.contains(team2))) {
                sender.sendMessage(ChatColor.RED + "One Or More Teams Was NOT Found.");
                return false;
            }
            List<String> roster1 = RFLCore.getInstance().getConfig().getStringList(team1);
            List<String> roster2 = RFLCore.getInstance().getConfig().getStringList(team2);
            if (GameCommand.players.containsKey((Player)sender)) {
                GameObject g = GameCommand.players.get((Player)sender);
                g.startGame(team1, team2, roster1, roster2, (Player) sender);
                games.put(team1, g);
                games.put(team2, g);

                sender.sendMessage(ChatColor.GREEN + "Game Successfully Created!");
            } else {
                sender.sendMessage(ChatColor.RED + "Error In Creation.");

            }


        }

        return false;
    }
}
