package world.epsilonsmp.EpsilonCarpet.utils;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import world.epsilonsmp.EpsilonCarpet.EpsilonCarpetSettings;

public class DummyGetHelper
{
    public static Comparable<?> dummyGetMethod(BlockState blockState, Property<Direction.Axis> property) {
        return EpsilonCarpetSettings.teleportToPoiWithoutPortals && !blockState.contains(Properties.HORIZONTAL_AXIS) ?
                Direction.Axis.Z : blockState.get(property);
    }
}
