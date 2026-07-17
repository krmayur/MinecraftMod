package com.mayuresh.portableweathermachine;

import com.mayuresh.portableweathermachine.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import com.mayuresh.portableweathermachine.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(PortableWeatherMachineMod.MODID)
public class PortableWeatherMachineMod {

    public static final String MODID = "portableweathermachine";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PortableWeatherMachineMod(IEventBus modEventBus, ModContainer modContainer) {

        // Register our items
        ModItems.register(modEventBus);

        // Register common setup
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Register game events
        NeoForge.EVENT_BUS.register(this);

        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Portable Weather Machine loaded!");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server started.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.PORTABLE_WEATHER_MACHINE);
        }
    }
}