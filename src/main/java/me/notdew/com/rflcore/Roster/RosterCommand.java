package me.notdew.com.rflcore.Roster;

import me.notdew.com.rflcore.RFLCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.notdew.com.rflcore.RFLCore.Teams;

public class RosterCommand implements CommandExecutor {
    RFLCore plugin = RFLCore.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {return true;}

        if (!(args.length > 0)) {sender.sendMessage(ChatColor.RED + "Invalid Arguments"); return true;}
        Player p = ((Player) sender).getPlayer();
        if (args[0].equalsIgnoreCase("add")) {
            if (!(Teams.contains(args[2].toLowerCase()))) {return true;}

            sender.sendMessage(ChatColor.GREEN + "" + Bukkit.getPlayer(args[1]).getDisplayName() + " has been added to the: " + args[2] + " roster.");

            String teamName = args[2].toString().toLowerCase();

            List<String> template = RFLCore.getInstance().getConfig().getStringList(teamName);
            template.add(Bukkit.getPlayer(args[1]).getUniqueId().toString());
            RFLCore.getInstance().getConfig().set(teamName, template);
            RFLCore.getInstance().saveConfig();
            return true;
        } else if (args[0].equalsIgnoreCase("create")) {
            List<String> template = new ArrayList<String>();


            if (Teams.contains(args[1].toLowerCase())) {sender.sendMessage(ChatColor.RED + "" + "Team has already been created."); return true;}
            String teamName = args[1].toLowerCase();
            RFLCore.getInstance().getConfig().set(teamName, template);
            Teams.add(teamName);
            plugin.getConfig().set("Teams", Teams);
            sender.sendMessage(ChatColor.GREEN + "You have successfully created the " + teamName + " team!");
            RFLCore.getInstance().getConfig().set("Teams", Teams);
            RFLCore.getInstance().saveConfig();
            Bukkit.broadcastMessage(args[1].toLowerCase());
            return true;
        } else if (args[0].equalsIgnoreCase("remove")) {

            if (!(RFLCore.getInstance().getConfig().getStringList(args[2]).contains(p.getUniqueId().toString()))) {sender.sendMessage(ChatColor.RED + "" + "Player is not on that team."); return true;}
            String teamName = args[2].toString().toLowerCase();

            List<String> template = RFLCore.getInstance().getConfig().getStringList(teamName);
            template.remove(Bukkit.getPlayer(args[1]).getUniqueId());
            sender.sendMessage(ChatColor.GREEN + "" + Bukkit.getPlayer(args[1]).getDisplayName() + " has been removed from the: " + args[2] + " roster.");
            RFLCore.getInstance().getConfig().set(teamName, template);
            RFLCore.getInstance().saveConfig();
            return true;
        } else if (args[0].equalsIgnoreCase("who")){
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (onWhatTeam(target) == null) {
                    sender.sendMessage(ChatColor.RED + "" + "Player Is Not On Any Teams Currently.");
                } else {
                    sender.sendMessage(ChatColor.GREEN + "" + target.getDisplayName() + " is on the " + onWhatTeam(target) + " roster.");
                }
            }
            return true;
        } else if (args[0].equalsIgnoreCase("delete")) {
            if (!(Teams.contains(args[1].toLowerCase()))) {sender.sendMessage(ChatColor.RED + "" + "Team has not been created."); return true;}

            RFLCore.getInstance().getConfig().set(args[1].toLowerCase(), null);
            Teams.remove(args[1].toLowerCase());
            plugin.getConfig().set("Teams", Teams);
            sender.sendMessage(ChatColor.GREEN + "" + "Removed team: " + args[1].toLowerCase());

            RFLCore.getInstance().saveConfig();


        }
        else if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage(ChatColor.GREEN + Teams.toString());
        } else if (args[0].equalsIgnoreCase("get")) {
            if (!(Teams.contains(args[1].toLowerCase()))) {sender.sendMessage(ChatColor.RED + "" + "Team has not been created."); return true;}
            String msg = " ";
            List <String> names = new ArrayList<String>();
            for (String uuid : RFLCore.config.getStringList("captains")) {
                if (onWhatTeam(uuid) != null) {
                    if (onWhatTeam(uuid).equalsIgnoreCase(args[1])) {
                        msg = ChatColor.GREEN + "Captain: " + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName();
                    }
                }

            }
            if (msg.equals(" ") ) {
                msg = ChatColor.GREEN + "Captain: ";
            }
            sender.sendMessage(ChatColor.GREEN + "--------------");
            sender.sendMessage(ChatColor.GREEN + args[1].substring(0, 1).toUpperCase() + args[1].substring(1));
            sender.sendMessage(ChatColor.GREEN + " ");


            sender.sendMessage(msg);
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.GREEN + "Roster:");
            for (int i = 0; i < RFLCore.getInstance().getConfig().getStringList(args[1].toLowerCase()).size(); i++) {
                sender.sendMessage(ChatColor.GREEN + "" + (i+1) + ": " + Bukkit.getOfflinePlayer(UUID.fromString(RFLCore.getInstance().getConfig().getStringList(args[1].toLowerCase()).get(i))).getName());

            }

            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.GREEN + "--------------");

        } else if (args[0].equalsIgnoreCase("captain")) {
            List<String> caps = RFLCore.getInstance().getConfig().getStringList("captains");
            if (args[1].equalsIgnoreCase("add")) {
                caps.add(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                RFLCore.config.set("captains", caps);
                sender.sendMessage(ChatColor.GREEN + "Added.");
                RFLCore.getInstance().saveConfig();

            } else if (args[1].equalsIgnoreCase("remove")) {
                caps.remove(Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString());
                RFLCore.config.set("captains", caps);
                sender.sendMessage(ChatColor.GREEN + "Removed.");
                RFLCore.getInstance().saveConfig();
            } else if (args[1].equalsIgnoreCase("list")) {
                ArrayList<String> list = new ArrayList<String>();
                for (String s : RFLCore.getInstance().getConfig().getStringList("captains")) {
                    list.add(Bukkit.getOfflinePlayer(UUID.fromString(s)).getName());
                }

                sender.sendMessage(ChatColor.GREEN + "All Captains: " + list.toString());

            }

        }


        return true;
    }

    public static String onWhatTeam(Player p)
    {


        for (int i = 0; i < Teams.size(); i++) {
            if (RFLCore.getInstance().getConfig().getStringList(Teams.get(i)).contains(p.getUniqueId().toString())) {
                return Teams.get(i);
            }
        }
        return null;
    }
    public static String onWhatTeam(String p)
    {


        for (int i = 0; i < Teams.size(); i++) {
            if (RFLCore.getInstance().getConfig().getStringList(Teams.get(i)).contains(p)) {
                return Teams.get(i);
            }
        }
        return null;
    }

}
