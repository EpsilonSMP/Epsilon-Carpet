package world.epsilonsmp.EpsilonCarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import world.epsilonsmp.EpsilonCarpet.commands.*;
import world.epsilonsmp.EpsilonCarpet.utils.HttpHelper;
import world.epsilonsmp.EpsilonCarpet.commands.*;
import net.minecraft.server.command.ServerCommandSource;

import java.time.Instant;

public class EpsilonCarpetExtension implements CarpetExtension {

    public static final String MOD_NAME = "Epsilon-Carpet";
    public static final String MOD_ID = "epsilon-carpet";
    public static final String VERSION = "2021.4.26";
    public static final String MC_VERSION = "1.16.5";

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

    private static Instant lastUpdateCheck = Instant.MIN;
    public static boolean shouldUpdate() {
        Instant now = Instant.now();
        if (lastUpdateCheck.plusSeconds(3600).isAfter(now))
            return false;
        lastUpdateCheck = now;
        return !EpsilonCarpetExtension.VERSION.equals(HttpHelper.getLatestRelease());
    }

}
