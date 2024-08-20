package net.mattias.randomitem.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class RandomItemGiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomItemGiver.class);
    private static int interval = 30; // 30 second interval default

    public static int getInterval() {
        return interval;
    }

    public static void setInterval(int seconds) {
        interval = seconds;
    }

    public static void giveRandomItem(Player player) {
        RandomSource random = RandomSource.create();
        Optional<Item> optionalItem = BuiltInRegistries.ITEM.getRandom(random).map(itemEntry -> itemEntry.value());

        if (optionalItem.isPresent()) {
            Item randomItem = optionalItem.get();
            ItemStack itemStack = new ItemStack(randomItem, 1);
            player.addItem(itemStack);
            LOGGER.info("Gave {} to player {}", randomItem.getDescriptionId(), player.getName().getString());
        } else {
            LOGGER.warn("Failed to retrieve a random item.");
        }
    }
}
