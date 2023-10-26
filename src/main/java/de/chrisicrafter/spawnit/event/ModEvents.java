package de.chrisicrafter.spawnit.event;

import de.chrisicrafter.spawnit.SpawnIt;
import de.chrisicrafter.spawnit.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpawnIt.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onMobCaptorEntityInteraction(PlayerInteractEvent.EntityInteract event) {
        if(event.getLevel() instanceof ServerLevel level && event.getEntity().isCrouching() && !event.getLevel().isClientSide() && event.getEntity().getItemInHand(event.getHand()).is(ModItems.MOB_CAPTOR.get())) {
            SpawnEggItem item = ForgeSpawnEggItem.fromEntityType(event.getTarget().getType());
            if(item != null) {
                level.playSound(null, event.getPos(), SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS);
                event.getEntity().setItemInHand(event.getHand(), new ItemStack(item));
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onSpawnerMined(BlockEvent.BreakEvent event) {
        if(event.getLevel() instanceof ServerLevel level && !event.getLevel().isClientSide()) {
            ItemStack stack = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            if(stack.getItem() instanceof PickaxeItem pickaxe && pickaxe.getTier().getLevel() >= Tiers.NETHERITE.getLevel() && stack.getEnchantmentLevel(Enchantments.SILK_TOUCH) >= 1) {
                stack.hurt(stack.getMaxDamage(), RandomSource.createNewThreadLocalInstance(), null);
                ItemEntity item = new ItemEntity(level, event.getPos().getX() + 0.5, event.getPos().getY(), event.getPos().getZ() + 0.5, new ItemStack(Items.SPAWNER));
                level.addFreshEntity(item);
            }
        }
    }
}
