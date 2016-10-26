package be.isach.onevsone.command;

import be.isach.onevsone.OneVOne;
import be.isach.onevsone.command.subcommands.SubCommandCreateArena;
import be.isach.onevsone.util.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sacha on 26-10-16.
 */
public class SubCommandManager implements CommandExecutor {

    private OneVOne oneVOne;
    private List<SubCommand> subCommands;

    public SubCommandManager(OneVOne oneVOne) {
        this.oneVOne = oneVOne;
        this.subCommands = new ArrayList<>();

        PluginCommand pluginCommand = Bukkit.getPluginCommand("1v1");
        pluginCommand.setExecutor(this);
        pluginCommand.setAliases(Arrays.asList("onevone", "ovo"));

        registerSubCommands();
    }

    private void registerSubCommand(SubCommand subCommand) {
        subCommands.add(subCommand);
    }

    private void registerSubCommands() {
        registerSubCommand(new SubCommandCreateArena(oneVOne));
    }

    // Page 1 : 1, 2, 3, 4, 5, 6, 7, 8
    // Page 2 : 9, 10, 11, 12, 13, 14, 15, 16
    // Page 3 : 17, 18, 19, 20, 21, 22, 23, 24
    //
    // -> 1re commande de la page: (page-1) * 8 + 1
    // -> 2e commande de la page: 8 * page

    public void showHelp(CommandSender commandSender, int page) {
        commandSender.sendMessage("");
        commandSender.sendMessage("§f§l  1v1 Help (/1v1 <page>) §8§l(" + page + "/" + getMaxPages(commandSender) + ")");
        List<SubCommand> commands = this.subCommands.stream().filter(subCommand -> commandSender.hasPermission(subCommand.getPermission())).collect(Collectors.toList());
        int from = ((page - 1) * 8) + 1; // 1st command
        int to = 8 * page; // 8th command
        for (int i = from; i <= to; i++) {
            if (i > commands.size()) {
                break;
            }

            SubCommand sub = commands.get(i - 1);
            commandSender.sendMessage("    §8|  §7" + sub.getUsage() + "§f§o " + sub.getDescription());
        }
    }

    /**
     * Gets the max amount of pages.
     *
     * @return the maximum amount of pages.
     */
    private int getMaxPages(CommandSender commandSender) {
        int max = 8;
        List<SubCommand> commands = subCommands.stream().filter(subCommand ->
                commandSender.hasPermission(subCommand.getPermission())).collect(Collectors.toList());
        int i = commands.size();
        if (i % max == 0) {
            return Math.max(1, i / max);
        }
        return (int) Math.ceil(i / 8d);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /1v1
        if (args.length == 0) {
            showHelp(sender, 1);
            return true;
        }

        // /1v1 [i}
        if (args.length == 1 && MathUtils.isInteger(args[0])) {
            int i = Integer.parseInt(args[0]);
            i = Math.min(getMaxPages(sender), i);
            i = Math.max(1, i);
            showHelp(sender, i);
            return true;
        }

        // /1v1 <commande> [args}
        String alias = args[0];
        Optional<SubCommand> optional = subCommands.stream().filter(subCommand -> subCommand.is(alias)).findFirst();
        SubCommand subCommand = optional.isPresent() ? optional.get() : null;
        if (subCommand != null) {
            String[] arguments = new String[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                arguments[i - 1] = args[i];
            }
            if (sender instanceof Player) {
                subCommand.onSubCommandPlayer(((Player) sender), arguments);
            } else if (sender instanceof ConsoleCommandSender) {
                subCommand.onSubCommandPlayer(((Player) sender), arguments);
            }
            return true;
        }

        showHelp(sender, 1);

        return true;
    }
}
