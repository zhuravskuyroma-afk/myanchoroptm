package com.example;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import java.util.concurrent.ThreadLocalRandom;

public class AnchorAutomator {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void onBlockPlaced(BlockHitResult hitResult) {
        if (!AnchorModConfig.enabled  mc.world == null  mc.player == null) return;

        if (mc.world.getBlockState(hitResult.getBlockPos()).getBlock() == Blocks.RESPAWN_ANCHOR) {
            new Thread(() -> {
                try {
                    long firstDelay = ThreadLocalRandom.current().nextLong(AnchorModConfig.minDelay, AnchorModConfig.maxDelay + 1);
                    Thread.sleep(firstDelay);

                    if (switchToItem(Items.GLOWSTONE)) {
                        executeClick(hitResult);
                    }

                    long secondDelay = ThreadLocalRandom.current().nextLong(AnchorModConfig.minDelay, AnchorModConfig.maxDelay + 1);
                    Thread.sleep(secondDelay);

                    if (AnchorModConfig.switchMode == AnchorModConfig.Mode.TOTEM) {
                        switchToItem(Items.TOTEM_OF_UNDYING);
                    } else {
                        switchToItem(Items.RESPAWN_ANCHOR);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static boolean switchToItem(net.minecraft.item.Item item) {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isOf(item)) {
                mc.player.getInventory().selectedSlot = i;
                return true;
            }
        }
        return false;
    }

    private static void executeClick(BlockHitResult hitResult) {
        if (mc.interactionManager != null) {
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
        }
    }
}
