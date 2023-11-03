package net.yukulab.horizonlimit;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static net.minecraft.command.argument.DimensionArgumentType.dimension;
import static net.minecraft.command.argument.DimensionArgumentType.getDimensionArgument;
import static net.minecraft.command.argument.EntityArgumentType.getPlayer;
import static net.minecraft.command.argument.EntityArgumentType.player;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.yukulab.horizonlimit.config.ConfigIO;
import net.yukulab.horizonlimit.config.ServerConfig;
import net.yukulab.horizonlimit.config.UserHeight;

import java.util.Map;

public class Commands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("hl")
                    .requires((serverCommandSource -> serverCommandSource.getEntity() instanceof ServerPlayerEntity))
                    .then(literal("set")
                            .then(argument("target", player())
                                    .then(argument("world", dimension())
                                            .then(argument("limit", integer(-64, 256))
                                                    .then(argument("side", string())
                                                            .executes(context -> {
                                                                var target = getPlayer(context, "target");
                                                                var world = getDimensionArgument(context, "world");
                                                                var limit = getInteger(context, "limit");
                                                                var side = getString(context, "side");
                                                                // TODO set world limit
                                                                ServerConfig config = ConfigIO.readConfig();
                                                                config.limit().put(world.getDimension().toString(), Map.of(target.getUuidAsString(),new UserHeight(side,limit)))
                                                                return Command.SINGLE_SUCCESS;
                                                            }))))))
                    .then(literal("count").then(argument("tick", integer()).executes(context -> {
                        var tick = getInteger(context, "tick");
                        // TODO set tick count
                        return Command.SINGLE_SUCCESS;
                    }))));
        });
    }
}
