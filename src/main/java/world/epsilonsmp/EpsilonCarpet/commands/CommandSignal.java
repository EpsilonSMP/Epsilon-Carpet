package world.epsilonsmp.EpsilonCarpet.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.BaseText;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandSignal {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder literalargumentbuilder = literal("signal")
                .requires((player) -> player.hasPermissionLevel(2) && EpsilonCarpetSettings.commandSignal)
                .then(argument("value", IntegerArgumentType.integer(1, 959))
                        .executes(context -> execute(context.getSource(), IntegerArgumentType.getInteger(context, "value"), false))
                        .then(argument("barrel", BoolArgumentType.bool())
                                .executes(context -> execute(context.getSource(), IntegerArgumentType.getInteger(context, "value"), BoolArgumentType.getBool(context, "barrel")))
                        )
                );

        dispatcher.register(literalargumentbuilder);
    }

    private static int execute(ServerCommandSource source, int value, boolean barrel) throws CommandSyntaxException {
        if (!EpsilonCarpetSettings.commandSignal || source == null)
            return 0;

        ItemStack item;
        CompoundTag tags = new CompoundTag();
        if (value <= 3 && !barrel) {
            item = Items.CAULDRON.getDefaultStack();
            CompoundTag tag = new CompoundTag();
            tag.putString("level", String.valueOf(value));
            tags.put("BlockStateTag", tag);
        } else if (value <= 8 && value != 7 && !barrel) {
            item = Items.COMPOSTER.getDefaultStack();
            CompoundTag tag = new CompoundTag();
            tag.putString("level", String.valueOf(value));
            tags.put("BlockStateTag", tag);
        } else {
            item = Items.BARREL.getDefaultStack();

            int count = (int) Math.ceil(27 * (value - 1) / 14D);
            byte slot = 0;
            ListTag itemsTag = new ListTag();
            while (count > 0) {
                CompoundTag slotTag = new CompoundTag();
                slotTag.putByte("Slot", slot);
                slotTag.putString("id", Registry.ITEM.getId(Items.WHITE_SHULKER_BOX).toString());
                slotTag.putByte("Count", (byte) (count > 63 ? 64 : count));
                itemsTag.add(slotTag);
                slot++;
                count -= 64;
            }

            CompoundTag tag = new CompoundTag();
            tag.put("Items", itemsTag);
            tags.put("BlockEntityTag", tag);
        }

        BaseText text = new LiteralText("Signal: " + value);
        text.setStyle(text.getStyle().withColor(Formatting.RED));
        item.setTag(tags);
        item.setCustomName(text);
        source.getPlayer().giveItemStack(item);

        return 1;
    }

}
