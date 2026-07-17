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
            player.displayClientMessage(
                    Component.literal("Portable Weather Machine Activated!"),
                    false
            );
        }

        return InteractionResult.SUCCESS;
    }
}