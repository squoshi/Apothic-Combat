package dev.muon.apothiccombat.mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadows.apotheosis.core.attributeslib.impl.AttributeEvents;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraftforge.event.ItemAttributeModifierEvent;

@Mixin(value = AttributeEvents.class, remap = false)
public abstract class AttributeEventsMixin {

    /**
     * Overwrites the affixModifiers method to remove the entity reach attribute tooltip from all items.
     * This change is made to address compatibility issues with Better Combat, which introduces its own attack range attribute, rendering the default entity reach attribute redundant.
     * The original method's approach to modifying tooltips does not accommodate this scenario well, necessitating this overwrite.
     *
     * @author muon-rw, Apothic Combat
     * @reason To ensure compatibility with Better Combat by removing the redundant entity reach attribute from all items
     */
    @Inject(method = "affixModifiers", at = @At("HEAD"), cancellable = true)
    public void affixModifiers(ItemAttributeModifierEvent e, CallbackInfo ci) {
        ci.cancel();
    }
}
