package dev.muon.apothiccombat.mixin;

import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.logic.WeaponRegistry;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import shadows.apotheosis.adventure.loot.LootCategory;

@Mixin(value = LootCategory.class, remap = false)
public class LootCategoryMixin {
    @Inject(method = "forItem", at = @At("RETURN"), cancellable = true)
    private static void HeavyWeaponCategory(ItemStack itemStack, CallbackInfoReturnable<LootCategory> ci) {
        if (net.minecraftforge.fml.ModList.get().isLoaded("bettercombat")) {
            if (ci.getReturnValue() == LootCategory.SWORD) {
                WeaponAttributes weaponAttributes = WeaponRegistry.getAttributes(itemStack);
                if (weaponAttributes != null && weaponAttributes.isTwoHanded()) {
                    ci.setReturnValue(LootCategory.HEAVY_WEAPON);
                }
            }
        }
    }
}