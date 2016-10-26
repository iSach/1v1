package be.isach.onevsone.command;

import be.isach.onevsone.OneVOne;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sacha on 26-10-16.
 */
public abstract class SubCommand {

    private String description, usage, permission;
    private List<String> aliases;
    private OneVOne oneVOne;

    public SubCommand(OneVOne oneVOne, String description, String usage, String permission, String... aliases) {
        this.oneVOne = oneVOne;
        this.description = description;
        this.usage = usage;
        this.permission = permission;
        this.aliases = Arrays.asList(aliases);
    }

    public abstract void onSubCommandPlayer(Player player, String[] args);

    public abstract void onSubCommandConsole(ConsoleCommandSender sender, String[] args);

    public String getPermission() {
        return permission;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public OneVOne getOneVOne() {
        return oneVOne;
    }

    public boolean is(String alias) {
        return aliases.contains(alias);
    }
}
