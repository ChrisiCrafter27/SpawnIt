package de.chrisicrafter.spawnit.event;

import de.chrisicrafter.spawnit.SpawnIt;
import de.chrisicrafter.spawnit.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpawnIt.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEventBusClientEvents {
    @SubscribeEvent
    public static void onBeaconTooltip(ItemTooltipEvent event) {
        if(event.getItemStack().is(ModItems.MOB_CAPTOR.get())) {
            event.getToolTip().add(Component.literal(ChatFormatting.GRAY + "Sneak-use on entity to captor its spawn egg."));
        }
    }
}
