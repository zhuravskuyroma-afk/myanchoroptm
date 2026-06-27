package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class AnchorModClient implements ClientModInitializer {
    public static KeyBinding configKeyBinding;

    @Override
    public void onInitializeClient() {
        configKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.anchormod.open_menu", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_H, 
                "category.anchormod"
        ));
    }
}
