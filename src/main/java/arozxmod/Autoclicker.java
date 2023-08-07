package arozxmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import arozxmod.screens.AutoclickerScreen;

import static arozxmod.ClientInitializer.count;

public class Autoclicker {
    public static final Logger LOGGER = LoggerFactory.getLogger("Autoclicker");

    public static int lClickDelayInt = 0;
    public static int rClickDelayInt = 0;
    public static boolean lHoldMode = false;
    public static boolean rHoldMode = false;
    public static boolean lTargetEntityMode = false;
    public static boolean rTargetEntityMode = false;
    public static boolean lCooldownAttackMode = false;
    public static int lTimer = 0;
    public static int rTimer = 0;
    public static int cps = 0;

    public static void onUpdate() {
        assert MinecraftClient.getInstance().player != null;
        if (Config.autoclickerEnabledL) {
            if (Config.cpsEnabled && Config.cps>0) {
                if (count > 0) {
                    cps = 20/cps;
                    count--;
                } else {
                    lCooldownAttackMode = true;
                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                    LOGGER.info("click sent" + System.currentTimeMillis() / 1000 + "count: " + count);
                    count = cps;
                }
                lCooldownAttackMode = false;

            } else {
                if (!lHoldMode) {
                    if (!lTargetEntityMode) {
                        SendLeftClick();
                    } else {
                        if (MinecraftClient.getInstance().targetedEntity != null) {
                            SendLeftClick();
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), true);
                }
            }
        }
        // Right autoclicker
        if (Config.autoclickerEnabledR) {
            if (Config.cpsEnabled && Config.cps>0) {
                if (count > 0) {
                    count--;
                } else {
                    rTargetEntityMode = false;
                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                    LOGGER.info("Right click sent" + System.currentTimeMillis() / 1000);
                    count = cps;
                }
                rTargetEntityMode = true;

            } else {
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

        private static void SendLeftClick () {
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
