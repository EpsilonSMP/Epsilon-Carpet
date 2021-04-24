# Epsilon-Carpet
A carpet extension made for EpsilonSMP in Minecraft 1.16.x

This is a fork of [FractalCarpetAddon](https://github.com/FracturedCode/FractalCarpetAddon). This addon may be incompatible with [carpet-addons](https://github.com/whoImT/carpet-addons).


#EpsilonSMP carpet rules
## Categories
* (TheEnd)[#theend-rules]
* (Cannon)[#cannon-rules]
* (Command)[#command-rules]
* (Score)[#score-rules]
* (Entity)[#entity-rules]
* (PlayerTweaks)[#playertweaks]

## TheEnd rules

### endGatewayCooldown
Remove the end gateway cooldown.
* Type: `boolean`
* Default value: `true`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `feature`, `epsilon-end-features`

### endMainIslandStructureGen
No end spikes, portal, crystal, egg or gateway generation when false.
* Type: `boolean`
* Default value: `true`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `feature`, `epsilon-end-features`

### noObsidianPlatform
Entities do not generate the obsidian platform in the end, except players.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `feature`, `epsilon-end-features`

## Cannon rules

### keepProjectilesTicked
Keep projectiles ticked in unloaded chunks.
* Type: `string`
* Default value: `default`
* Required options: `default`, `all`, `player-only`, `enderpearls`
* Categories: `epsilon-carpet`, `feature`

### logTNTMomentum
Debug TNT momentum transfer to enderpearls in console.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `creative`

### ftlTNT
TNT optimized for large amounts in Cannons.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `optimization`

## Command rules

### commandLocation
Enables /location command to know where is a player.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `command`

### commandSignal
Enables /signal command to get a container with comparator value.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `creative`, `command`

### commandEnderchest
Enables /enderchest command to open the enderchest of a player.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `feature`, `command`

### commandTotal
Enables /total command to know the total sum of a scoreboard.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `command`

### commandComputation
Enables /computation command to test redstone contraptions.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `command`

### commandBatch
Enables /batch command to execute commands multiple times.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `command`

## Score rules

### filterBotsInScores
Bots don't appear on scoreboards and do not count in the total.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `feature`

### totalScore
The scoreboard total appears on the scoreboard.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `feature`

## Entity rules

### forceShulkerTeleport
Force shulkers to teleport when stay in invalid positions.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `epsilon-end-features`, `survival`, `feature`

### seaLevelFishes
Fishes only can spawn between y:45 and y:63, both excluded.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `bugfix`

### maxHeightmap
Set the max value possible for heightmap. USE AT YOUR OWN RISK!
* Type: `integer`
* Default value: `255`
* Valid options: `0-255`
* Categories: `epsilon-carpet`, `survival`, `experimental`, `optimization`

### llamaDupeExploit
Enables old donkey / llama dupe bug.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `feature`

## PlayerTweaks

### oldFlintAndSteelBehavior
Backports 1.12 flint and steel behavior. Flint and steel can be used for updating observers / buds.
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `epsilon-carpet`, `survival`, `feature`

### carefulBreak
Places the mined block in the player inventory when sneaking.
* Type: `string`
* Default value: `never`
* Required options: `never`, `always`, `sneaking`, `no-sneaking`
* Categories: `epsilon-carpet`, `survival`, `feature`, `experimental`

### oreUpdateSuppressor
Emerald ore acts as an update suppressor.
* Type: `boolean`
* Default value: `false`
* Required options: `false`, `true`
* Categories: `epsilon-carpet`, `creative`

## Features in Development
- Experimental ender pearl ticking for cannons

## Development Roadmap
-   Passive Farms Fix
-   Keep enderpearls traveling without loading chunks and teleport player when lands
