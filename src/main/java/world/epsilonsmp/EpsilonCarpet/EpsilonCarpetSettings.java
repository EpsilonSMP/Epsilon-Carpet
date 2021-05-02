package world.epsilonsmp.EpsilonCarpet;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import javax.net.ssl.SSLContext;
import java.util.Arrays;

import static carpet.settings.RuleCategory.*;

public class EpsilonCarpetSettings {
    public static final String EpsilonCarpetSettingsCategory = "epsilon-carpet";
    public static final String EndSettingsCategory = "epsilon-carpet-end-features";
    public static final String CannonSettingsCategory = "epsilon-carpet-cannon-features";
    public static final String ScoreboardSettingsCategory = "epsilon-carpet-scoreboard-features";
    public static final String EntitySettingsCategory = "epsilon-carpet-entity-features";
    public static final String PlayerTweakSettingsCategory = "epsilon-carpet-player-tweaks";

    /* ===== Begin TheEnd Rules ===== */

    @Rule(
            desc = "Toggle for end gateway cooldown.",
            category = { EpsilonCarpetSettingsCategory, EndSettingsCategory, FEATURE }
    )
    public static boolean endGatewayCooldown = true;

    @Rule(
            desc = "Toggle for the main end island structure generation, turns off portal, egg, obsidian pillars, gateways and crystals.",
            category = { EpsilonCarpetSettingsCategory, EndSettingsCategory, FEATURE }
    )
    public static boolean endMainIslandStructureGen = true;

    @Rule(
            desc = "Toggle for end obsidian platform generation excluding players.",
            category = { EpsilonCarpetSettingsCategory, EndSettingsCategory, FEATURE }
    )
    public static boolean noObsidianPlatform = false;

    /* ===== End TheEnd Rules ===== */

    /* ===== Begin Cannon Rules ===== */


    @Rule(
            desc = "Prevents ender pearls from getting deleted when they move into unloaded chunks",
            extra = {"This also means that ender pearls load chunks",
                    "Merged with keepEnderPearlsTicked from carpet-addons by whoImT"},
            category = {EpsilonCarpetSettingsCategory,SURVIVAL, EXPERIMENTAL}
    )
    public static boolean forceLoadEnderPearls = false;

    @Rule(
            desc = "Debug TNT momentum transfer to enderpearls in console.",
            category = { EpsilonCarpetSettingsCategory, CREATIVE, CannonSettingsCategory }
    )
    public static boolean logTNTMomentum = false;

    @Rule(
            desc = "TNT optimized for large amounts in Cannons.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, OPTIMIZATION, CannonSettingsCategory }
    )
    public static boolean ftlTNT = false;

    @Rule(
            desc = "Changes the distance projectiles check for collisions. If set to 0 all Blocks to the destination will be checked which is the Vanilla behaviour.",
            extra = {"This reduces lag for fast projectiles. In 1.12 the value was 200."},
            category = {EXPERIMENTAL,OPTIMIZATION, "carpetaddons"},
            options = {"0","200"},
            strict = false,
            validate = {projectileRaycastLengthValidator.class}
    )
    public static int projectileRaycastLength = 0;

    private static class projectileRaycastLengthValidator extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value greater or equal to 0";
        }

    }

    /* ===== End Cannon Rules ===== */

    /* ===== Begin Commands Rules ===== */

    @Rule(
            desc = "Enables /location command to know where is a player.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, COMMAND }
    )
    public static boolean commandLocation = false;

    @Rule(
            desc = "Enables /signal command to get a container with comparator value.",
            category = { EpsilonCarpetSettingsCategory, CREATIVE, COMMAND }
    )
    public static boolean commandSignal = false;

    @Rule(
            desc = "Enables /enderchest command to open the enderchest of a player.",
            category = { EpsilonCarpetSettingsCategory, FEATURE, COMMAND }
    )
    public static boolean commandEnderchest = false;

    @Rule(
            desc = "Enables /total command to know the total sum of a scoreboard.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, COMMAND }
    )
    public static boolean commandTotal = false;

    @Rule(
            desc = "Enables /computation command to test redstone contraptions.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, COMMAND }
    )
    public static boolean commandComputation = false;

    @Rule(
            desc = "Enables /batch command to execute commands multiple times.",
            category = { EpsilonCarpetSettingsCategory, COMMAND }
    )
    public static boolean commandBatch = false;

    /* ===== End Commands Rules ===== */

    /* ===== Begin Score Rules ===== */

    @Rule(
            desc = "Bots don't appear on scoreboards and do not count in the total.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, FEATURE, ScoreboardSettingsCategory }
    )
    public static boolean filterBotsInScores = false;

    @Rule(
            desc = "The scoreboard total appears on the scoreboard.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, FEATURE, ScoreboardSettingsCategory}
    )
    public static boolean totalScore = false;

    /* ===== End Score Rules ===== */

    /* ===== Begin Entity Rules ===== */

    @Rule(
            desc = "Prevents phantoms from spawning if mobcap is full",
            category = {EpsilonCarpetSettingsCategory,SURVIVAL, EXPERIMENTAL}
    )
    public static boolean phantomsCapped = false;

    @Rule(
            desc = "Force shulkers to teleport when stay in invalid positions.",
            category = { EpsilonCarpetSettingsCategory, EndSettingsCategory, SURVIVAL, FEATURE, EntitySettingsCategory }
    )
    public static boolean forceShulkerTeleport = false;

    @Rule(
            desc = "Fishes only can spawn between y:45 and y:63, both excluded.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, BUGFIX, EntitySettingsCategory }
    )
    public static boolean seaLevelFishes = false;

    @Rule (
            desc = "When true turns off endermen griefing...except melons",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, EntitySettingsCategory }
    )
    public static boolean antiEnderGriefExceptMelon = false; // fu melons

    @Rule(
            desc = "Enables old donkey / llama dupe bug.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, FEATURE, EntitySettingsCategory }
    )
    public static boolean llamaDupeExploit = false;

    /* ===== End Entity Rules ===== */

    /* ===== Begin PlayerTweaks Rules =====*/

    @Rule(
            desc = "Backports 1.12 flint and steel behavior. Flint and steel can be used for updating observers / buds.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, FEATURE, PlayerTweakSettingsCategory }
    )
    public static boolean oldFlintAndSteelBehavior = false;

    /* Begin CarefulBreak stuff */
    private static final String[] carefulBreakOptions = new String[] { "never", "always", "sneaking", "no-sneaking" };
    @Rule(
            desc = "Places the mined block in the player inventory when sneaking.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, FEATURE, EXPERIMENTAL, PlayerTweakSettingsCategory },
            options = { "never", "always", "sneaking", "no-sneaking" },
            validate = { carefulBreakValidator.class }
    )
    public static String carefulBreak = "never";

    private static class carefulBreakValidator extends Validator<String> {

        @Override
        public String validate(ServerCommandSource serverCommandSource, ParsedRule<String> parsedRule, String s, String s2) {
            if ((serverCommandSource == null || parsedRule.get().equals(s)) && Arrays.asList(carefulBreakOptions).contains(s))
                carefulBreak = s;
            return s;
        }
    }
    /* End CarefulBreak stuff */

    @Rule(
            desc = "Emerald ore acts as an update suppressor.",
            category = { EpsilonCarpetSettingsCategory, CREATIVE }
    )
    public static boolean oreUpdateSuppressor = false;

    @Rule(
            desc = "Enable the possibility to store shulkerboxes inside shulkerboxes.",
            category = { EpsilonCarpetSettingsCategory, SURVIVAL, FEATURE, PlayerTweakSettingsCategory }
    )
    public static boolean shulkerInception = false;

    /* ===== End PlayerTweaks Rules =====*/

    @Rule(
            desc = "Re-adds teleporting to portal POIs without portal blocks",
            extra = {"Update suppressor go brrr"},
            category = {EpsilonCarpetSettingsCategory,SURVIVAL, EXPERIMENTAL}
    )
    public static boolean teleportToPoiWithoutPortals = false;

}
