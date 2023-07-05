package arozxmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Autoclicker {
    public static int lClickDelayInt = 0;
    public static int rClickDelayInt = 0;
    public static boolean lHoldMode = false;
    public static boolean rHoldMode = false;
    public static boolean lTargetEntityMode = false;
    public static boolean rTargetEntityMode = false;
    public static boolean lCooldownAttackMode = false;
    public static int lTimer = 0;
    public static int rTimer = 0;

    public static void onUpdate() {
        assert MinecraftClient.getInstance().player != null;

        if (ArozxMod.autoclickerEnabledL) {
            if (!lHoldMode) {
                if (!lTargetEntityMode) {
                    if (lCooldownAttackMode) {
                        if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                        }
                    } else {
                        if (lTimer < lClickDelayInt) lTimer++;
                        else {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                            lTimer = 0;
                        }
                    }
                } else {
                    if (MinecraftClient.getInstance().targetedEntity != null) {
                        if (lCooldownAttackMode) {
                            if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                            }
                        } else {
                            if (lTimer < lClickDelayInt) lTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                lTimer = 0;
                            }
                        }
                    }
                }
            } else {
                KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), true);
            }
        }
        if (ArozxMod.autoclickerEnabledR) {
            if (!rHoldMode) {
                if (!rTargetEntityMode) {
                    if (rTimer < rClickDelayInt) rTimer++;
                    else {
                        KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                        rTimer = 0;
                    }
                } else {
                    if (MinecraftClient.getInstance().targetedEntity != null) {
                        if (rTimer < rClickDelayInt) rTimer++;
                        else {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                            rTimer = 0;
                        }
                    }
                }
            } else {
                KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), true);
            }
        }
    }
}
