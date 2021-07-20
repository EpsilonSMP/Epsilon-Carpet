package world.epsilonsmp.EpsilonCarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import world.epsilonsmp.EpsilonCarpet.commands.*;
import net.minecraft.server.command.ServerCommandSource;

public class EpsilonCarpetExtension implements CarpetExtension {

    public static final String MOD_NAME = "Epsilon-Carpet";
    public static final String MOD_ID = "epsilon-carpet";
    public static final String VERSION = "2021.7.20";
    public static final String MC_VERSION = "1.17.1";

    public static void noop() {}

    static {
        EpsilonCarpetExtension extension = new EpsilonCarpetExtension();
        CarpetServer.manageExtension(extension);
        extension.onGameStarted();
    }
    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(EpsilonCarpetSettings.class);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        CommandLocation.register(dispatcher);
        CommandEnderchest.register(dispatcher);
        CommandTotal.register(dispatcher);
        CommandSignal.register(dispatcher);
        CommandComputation.register(dispatcher);
        CommandBatch.register(dispatcher);
    }

}
