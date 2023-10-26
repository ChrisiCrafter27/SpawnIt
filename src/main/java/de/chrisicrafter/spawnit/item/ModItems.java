package de.chrisicrafter.spawnit.item;

import de.chrisicrafter.spawnit.SpawnIt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpawnIt.MOD_ID);

    public static final RegistryObject<Item> MOB_CAPTOR = ITEMS.register("mob_captor", () -> new Item(new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
