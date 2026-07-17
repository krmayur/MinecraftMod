package com.mayuresh.portableweathermachine.registry;

import com.mayuresh.portableweathermachine.PortableWeatherMachineMod;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.mayuresh.portableweathermachine.item.PortableWeatherMachineItem;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(PortableWeatherMachineMod.MODID);

    public static final DeferredItem<Item> PORTABLE_WEATHER_MACHINE =
            ITEMS.registerItem(
                    "portable_weather_machine",
                    PortableWeatherMachineItem::new,
                    new Item.Properties().stacksTo(1)
            );
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
