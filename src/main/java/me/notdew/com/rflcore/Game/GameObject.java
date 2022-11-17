package me.notdew.com.rflcore.Game;

import com.sk89q.worldedit.math.Vector3;
import me.notdew.com.rflcore.Field.FieldCommand;
import me.notdew.com.rflcore.Field.FieldObject;
import me.notdew.com.rflcore.RFLCore;
import me.notdew.com.rflcore.Roster.RosterCommand;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class GameObject {
    FieldObject fo;
    String team1;
    String team2;
    List<String> roster1;
    List<String> roster2;
    Player cap1;
    Player cap2;
    Player admin;
    char ball;
    char homedir;
    char awaydir;


    public GameObject(String field, Player p) {
        ball = 'N';
        fo = FieldCommand.fieldnames.get(field);
        homedir = fo.getHomeOffenseDirection();
        awaydir = fo.getAwayOffenseDirection();
        p.sendMessage(ChatColor.GREEN + "Do /startgame team1 team2");


    }


    public Location moveOffense(int yards) {
        return null;
    }
    public Location moveDefense(int yards) {
        return null;
    }

    public void startGame(String team1, String team2, List<String> roster1, List<String> roster2, Player admin) {
        this.team1 = team1;
        this.team2 = team2;
        this.roster1 = roster1;
        this.roster2 = roster2;
        this.admin = admin;
        for (String s : RFLCore.getInstance().getConfig().getStringList("captains")) {
            if (RosterCommand.onWhatTeam(s).equalsIgnoreCase(team1)) {
                OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(s));
                if (op.isOnline()) {
                    cap1 = (Player)op;
                } else {
                    admin.sendMessage(ChatColor.RED + "" + team1 + " Has No Captain. Cancelling.");
                    return;
                }
            } else if (RosterCommand.onWhatTeam(s).equalsIgnoreCase(team2)) {
                OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(s));
                if (op.isOnline()) {
                    cap2 = (Player)op;
                } else {
                    admin.sendMessage(ChatColor.RED + "" + team2 + " Has No Captain. Cancelling.");
                    return;
                }
            }
        }
        Vector3 centers = fo.getRegion().getCenter();

        Vector center = new Vector(centers.getBlockX(), centers.getBlockY(), centers.getBlockZ());
        Vector cap1loc = center;
        Vector cap2loc = center;
        if (fo.getDirection().equals("S")) {
            // + Z
            cap1loc.setZ(center.getBlockZ() + 3);
            cap2loc.setZ(center.getBlockZ() - 3);

        } else if (fo.getDirection().equals("W")) {
            // - X
            cap1loc.setX(center.getBlockX() + 3);
            cap2loc.setX(center.getBlockX() - 3);

        } else if (fo.getDirection().equals("N")) {
            // - Z
            cap1loc.setZ(center.getBlockZ() + 3);
            cap2loc.setZ(center.getBlockZ() - 3);

        } else if (fo.getDirection().equals("E")) {
            // + X
            cap1loc.setX(center.getBlockX() + 3);
            cap2loc.setX(center.getBlockX() - 3);

        }
        cap1.teleport(cap1loc.toLocation((World) fo.getRegion().getWorld()));
        cap2.teleport(cap2loc.toLocation((World) fo.getRegion().getWorld()));






        admin.sendMessage(cap1.getName() + "|" + cap2.getName());

        ball = 'H';





    }
}
