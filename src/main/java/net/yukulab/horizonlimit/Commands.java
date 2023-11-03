package net.yukulab.horizonlimit;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.command.argument.DimensionArgumentType.dimension;
import static net.minecraft.command.argument.DimensionArgumentType.getDimensionArgument;
import static net.minecraft.command.argument.EntityArgumentType.getPlayer;
import static net.minecraft.command.argument.EntityArgumentType.player;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.Command;
import java.util.HashMap;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.yukulab.horizonlimit.config.ServerConfig;
import net.yukulab.horizonlimit.config.UserHeight;

public class Commands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("hl")
                    .requires((serverCommandSource -> serverCommandSource.getEntity() instanceof ServerPlayerEntity))
                    .then(literal("set")
                            .then(argument("target", player())
                                    .then(argument("world", dimension())
                                            .then(argument("limit", integer(-64, 256))
                                                    .then(argument("isSkySide", bool())
                                                            .executes(context -> {
                                                                var target = getPlayer(context, "target");
                                                                var world = getDimensionArgument(context, "world");
                                                                var limit = getInteger(context, "limit");
                                                                var side = getBool(context, "isSkySide");
                                                                ServerConfig config = context.getSource()
                                                                        .getServer()
                                                                        .horizonlimit$getServerConfig();
                                                                var dim = world.getDimension()
                                                                        .effects()
                                                                        .toString();
                                                                var map = config.limit()
                                                                        .get(dim);
                                                                if (map == null) {
                                                                    map = new HashMap<>();
                                                                }
                                                                map.put(target.getUuid(), new UserHeight(side, limit));
                                                                config.limit().put(dim, map);
                                                                context.getSource()
                                                                        .getServer()
                                                                        .horizonlimit$setServerConfig(config);
                                                                String feedbackLangkey =
                                                                        "command.hl.height." + (side ? "min" : "max");
                                                                context.getSource()
                                                                        .sendFeedback(
                                                                                () -> Text.translatable(
                                                                                        feedbackLangkey,
                                                                                        target.getEntityName(),
                                                                                        limit),
                                                                                true);

                                                                return Command.SINGLE_SUCCESS;
                                                            }))))))
                    .then(literal("count").then(argument("tick", integer()).executes(context -> {
                        var tick = getInteger(context, "tick");
                        ServerConfig config = context.getSource().getServer().horizonlimit$getServerConfig();
                        context.getSource()
                                .getServer()
                                .horizonlimit$setServerConfig(new ServerConfig(tick, config.limit()));
                        context.getSource()
                                .sendFeedback(
                                        () -> Text.translatable(
                                                "command.hl.count", String.format("%.2f", (double) tick / 20)),
                                        true);
                        return Command.SINGLE_SUCCESS;
                    }))));
        });
    }
}
