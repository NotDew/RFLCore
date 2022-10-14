package me.notdew.com.rflcore.Game;

import me.notdew.com.rflcore.Field.FieldCommand;
import me.notdew.com.rflcore.Field.FieldObject;
import me.notdew.com.rflcore.RFLCore;
import me.notdew.com.rflcore.Roster.RosterCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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


    public GameObject(String field, Player p) {
        fo = FieldCommand.fieldnames.get(field);
        p.sendMessage(ChatColor.GREEN + "Do /startgame team1 team2");


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
        admin.sendMessage(cap1.getName() + "|" + cap2.getName());





    }
}
