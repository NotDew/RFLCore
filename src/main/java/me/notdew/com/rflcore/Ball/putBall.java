package me.notdew.com.rflcore.Ball;

import me.notdew.com.rflcore.Kicking.KickingCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class putBall implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player)sender;
        KickingCommand.ballplayer.get(p).spawnBall(p.getLocation());

        return false;
    }
}
