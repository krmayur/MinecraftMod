package com.mayuresh.portableweathermachine.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PortableWeatherMachineItem extends Item {

    public PortableWeatherMachineItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {
            long time = level.getDayTime() % 24000;
            String weather;
            long dayNumber = (level.getDayTime() / 24000) + 1;

            if (level.isThundering()) {
                weather = "Thunderstorm";
            } else if (level.isRaining()) {
                weather = "Rain";
            } else {
                weather = "Clear";
            }

            String timeOfDay;

            if (time < 1000) {
                timeOfDay = "Sunrise";
            } else if (time < 6000) {
                timeOfDay = "Morning";
            } else if (time < 12000) {
                timeOfDay = "Afternoon";
            } else if (time < 13000) {
                timeOfDay = "Sunset";
            } else if (time < 18000) {
                timeOfDay = "Evening";
            } else {
                timeOfDay = "Night";
            }
            player.displayClientMessage(
                    Component.literal("Weather: " + weather +
                                    " | Time: " + timeOfDay +
                                    " | Day: " + dayNumber),
                    false
            );
        }

        return InteractionResult.SUCCESS;
    }
}