package net.mattias.randomitem.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.mattias.randomitem.util.RandomItemGiver;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SetIntervalCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setinterval")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("seconds", IntegerArgumentType.integer(1))
                        .executes(context -> {
                            int seconds = IntegerArgumentType.getInteger(context, "seconds");
                            RandomItemGiver.setInterval(seconds);
                            context.getSource().sendSuccess(() -> Component.literal("Interval set to " + seconds + " seconds."), true);
                            return 1;
                        })));
    }
}
