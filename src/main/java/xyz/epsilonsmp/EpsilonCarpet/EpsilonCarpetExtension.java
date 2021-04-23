package xyz.epsilonsmp.EpsilonCarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import xyz.epsilonsmp.EpsilonCarpet.commands.*;
import xyz.epsilonsmp.EpsilonCarpet.utils.HttpHelper;
import net.minecraft.server.command.ServerCommandSource;

import java.time.Instant;

public class EpsilonCarpetExtension implements CarpetExtension {

    public static final String MOD_NAME = "Epsilon-Carpet";
    public static final String MOD_ID = "epsilon-carpet";
    public static final String VERSION = "2021.4.23";

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
