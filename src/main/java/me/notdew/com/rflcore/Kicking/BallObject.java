package me.notdew.com.rflcore.Kicking;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BallObject {

    Player p;
    public BallObject(Player p, boolean b) {
        this.p = p;
        if (b) KickingCommand.kicking.add(p);

    }

    public void spawnBall(Location l) {

    }

}
