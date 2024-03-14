package dev.muon.apothiccombat.mixin;
import dev.shadowsoffire.attributeslib.impl.AttributeEvents;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import dev.shadowsoffire.attributeslib.api.AttributeHelper;
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
    @Overwrite
    public void affixModifiers(ItemAttributeModifierEvent e) {
        if (e.getSlotType() == EquipmentSlot.CHEST && e.getItemStack().getItem() instanceof ElytraItem && !e.getModifiers().containsKey(ALObjects.Attributes.ELYTRA_FLIGHT.get())) {
            e.addModifier(ALObjects.Attributes.ELYTRA_FLIGHT.get(), new AttributeModifier(AttributeHelper.ELYTRA_FLIGHT_UUID, () -> "attributeslib:elytra_item_flight", 1, Operation.ADDITION));
        }
    }
}
