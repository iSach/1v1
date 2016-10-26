package be.isach.onevsone.command.subcommands;

import be.isach.onevsone.OneVOne;
import be.isach.onevsone.command.SubCommand;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by sacha on 26-10-16.
 */
public class SubCommandCreateArena extends SubCommand {

    public SubCommandCreateArena(OneVOne oneVOne) {
        super(oneVOne, "Creates an arena.", "/1v1 create <arena>", "onevone.admin.create", "create", "createarena", "c");
    }

    @Override
    public void onSubCommandPlayer(Player player, String[] args) {

    }

    @Override
    public void onSubCommandConsole(ConsoleCommandSender sender, String[] args) {

    }
}
