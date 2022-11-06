package me.notdew.com.rflcore.Kicking;

import me.notdew.com.rflcore.Game.GameObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;


public class KickingCommand implements CommandExecutor {
    public static ArrayList<Player> kicking = new ArrayList<Player>();
    public static HashMap<Player, BallObject> ballplayer = new HashMap<Player, BallObject>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player)sender;
        BallObject b = new BallObject(p, true);
        ballplayer.put(p, b);
        return false;
    }
}
