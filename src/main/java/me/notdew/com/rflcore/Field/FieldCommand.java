package me.notdew.com.rflcore.Field;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.SessionManager;
import com.sk89q.worldedit.util.formatting.text.TextComponent;
import com.sk89q.worldedit.world.World;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;


public class FieldCommand implements CommandExecutor {

    public static HashMap<org.bukkit.entity.Player, FieldObject> creations = new HashMap<org.bukkit.entity.Player, FieldObject>();
    public static HashMap<String, FieldObject> fieldnames = new HashMap<String, FieldObject>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {


            //create
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender; // platform-specific player class, generally obtained from a command, event, etc.
            Player actor = BukkitAdapter.adapt(player); // WorldEdit's native Player class extends Actor
            SessionManager manager = WorldEdit.getInstance().getSessionManager();
            LocalSession localSession = manager.get(actor);
            Region region; // declare the region variable
            // note: not necessarily the player's current world, see the concepts page
            World selectionWorld = localSession.getSelectionWorld();
            try {
                if (selectionWorld == null) throw new IncompleteRegionException();
                region = localSession.getSelection(selectionWorld);
            } catch (IncompleteRegionException ex) {
                player.sendMessage(String.valueOf(TextComponent.of("Please make a region selection first.")));
                return false;
            }
            creations.put(player, new FieldObject(region, player, args[1]));
            fieldnames.put(args[1], creations.get(player));


            return false;
        } else if (args.length == 1){
            if (args[0].equals("away")) {
                creations.get((org.bukkit.entity.Player) sender).inAway();
                return false;
            } else if (args[0].equals("list")) {
                sender.sendMessage(fieldnames.toString());
                return false;
            }
        }
        return false;
    }
}
