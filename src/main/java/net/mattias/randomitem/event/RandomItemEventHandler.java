package net.mattias.randomitem.event;

import net.mattias.randomitem.util.RandomItemGiver;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RandomItemEventHandler {

    private static int ticks = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ticks++;
            if (ticks >= RandomItemGiver.getInterval() * 20) {
                ticks = 0;
                MinecraftServer server = event.getServer();
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    RandomItemGiver.giveRandomItem(player);
                }
            }
        }
    }
}