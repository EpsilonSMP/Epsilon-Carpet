package xyz.epsilonsmp.EpsilonCarpet.utils;

import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;

import java.util.Comparator;

/**
 * @vktec /chonk -> vktec.chonk.Chonk
 */
public class ChunkHelper {

    public static final ChunkTicketType<ChunkPos> TICKET = ChunkTicketType.create("epsilon", Comparator.comparingLong(ChunkPos::toLong), 1);

    public static void loadTicking(ServerWorld world, ChunkPos pos) {
        load(world, pos, 1);
    }

    public static void loadEntityTicking(ServerWorld world, ChunkPos pos) {
        load(world, pos, 2);
    }

    private static void load(ServerWorld world, ChunkPos pos, int level) {
        ServerChunkManager manager = world.getChunkManager();
        manager.addTicket(ChunkHelper.TICKET, pos, level, pos);

        ChunkHolder holder = manager.getChunkHolder(pos.toLong());
        if (holder.getLevel() > 33 - level)
            manager.tick();
    }

}
