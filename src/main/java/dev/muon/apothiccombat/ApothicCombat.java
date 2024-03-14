package dev.muon.apothiccombat;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(ApothicCombat.MODID)
public class ApothicCombat
{
    public static final String MODID = "apothiccombat";
    private static final Logger LOGGER = LogManager.getLogger();
    @SubscribeEvent
    public void clearReachModifiers(ItemAttributeModifierEvent event) {
        if (event.getModifiers().containsKey(ForgeMod.ENTITY_REACH.get())) {
            event.removeAttribute(ForgeMod.ENTITY_REACH.get());
        }
    }
    public ApothicCombat()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        ApothicCombat.LOGGER.info("Loading Apothic Combat");
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {}

    public static ResourceLocation loc(String id) {
        return new ResourceLocation(ApothicCombat.MODID, id);
    }
}