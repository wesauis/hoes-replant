package io.github.wesauis.hoes_replant.mixin;

import io.github.wesauis.hoes_replant.extension.LogAware;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public abstract class HoeMixin implements LogAware {

    private static final TagKey<Block> TAG_CROPS = BlockTags.CROPS;
    private static final TagKey<Item> TAG_SEEDS = TagKey.of(Registry.ITEM_KEY, new Identifier("hoes_replant", "seeds"));


    @Inject(method = "postMine", at = @At("HEAD"))
    private void blockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        if (world.isClient) return;
        if (!isHoe()) return;
        if (!state.isIn(TAG_CROPS)) return;

        cropHarvested(stack, world, state, pos, miner, cir);
    }

    private boolean isHoe() {
        return HoeItem.class.getName().equals(getClass().getName());
    }

    private void cropHarvested(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        var seeds = miner.getOffHandStack();
        if (!seeds.isIn(TAG_SEEDS)) return;
    }
}
