package com.example.mixin;

import com.example.AnchorAutomator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ExampleMixin {
    
    @Inject(method = "interactBlock", at = @At("TAIL"))
    private void onInteractBlock(MinecraftClient client, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (cir.getReturnValue() == ActionResult.SUCCESS || cir.getReturnValue() == ActionResult.CONSUME) {
            AnchorAutomator.onBlockPlaced(hitResult);
        }
    }
}
