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

            String weather = getWeather(level);
            long dayNumber = getDayNumber(level);
            String timeOfDay = getTimeOfDay(level);
            String moonPhase = getMoonPhase(level);

            player.displayClientMessage(
                    Component.literal("Weather: " + weather +
                                    " | Time: " + timeOfDay +
                                    " | Day: " + dayNumber +
                                    " | Moon: " + moonPhase
                    ),
                    false
            );
        }

        return InteractionResult.SUCCESS;
    }

    private String getWeather(Level level) {
        if (level.isThundering()) {
            return "Thunderstorm";
        } else if (level.isRaining()) {
            return "Rain";
        } else {
            return "Clear";
        }
    }

    private String getTimeOfDay(Level level) {
        long time = level.getDayTime() % 24000;

        if (time < 1000) {
            return "Sunrise";
        } else if (time < 6000) {
            return "Morning";
        } else if (time < 12000) {
            return "Afternoon";
        } else if (time < 13000) {
            return "Sunset";
        } else if (time < 18000) {
            return "Evening";
        } else {
            return "Night";
        }
    }

    private long getDayNumber(Level level) {
        return (level.getDayTime() / 24000) + 1;
    }

    private String getMoonPhase(Level level) {

        long worldDay = level.getDayTime() / 24000L;
        int phase = (int) (worldDay % 8);

        return switch (phase) {
            case 0 -> "Full Moon";
            case 1 -> "Waning Gibbous";
            case 2 -> "Last Quarter";
            case 3 -> "Waning Crescent";
            case 4 -> "New Moon";
            case 5 -> "Waxing Crescent";
            case 6 -> "First Quarter";
            case 7 -> "Waxing Gibbous";
            default -> "Unknown";
        };
    }
}