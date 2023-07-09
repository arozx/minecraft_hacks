package arozxmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoatFlying {
    public static final Logger LOGGER = LoggerFactory.getLogger("BoatFlyingMod");

    private static boolean changeForwardSpeed;
    private static double upwardSpeed = 0.15;
    private static double forwardSpeed;
    static int toggle = 0;

    public static void onUpdate() {
        // Check if the player is riding a vehicle
        if (!player.hasVehicle()) return;

        Entity vehicle = player.getVehicle();
        Vec3d velocity = vehicle.getVelocity();

        // Default motion
        double motionX = velocity.x;
        double motionY = 0;
        double motionZ = velocity.z;

        // Up/down movement
        if (MinecraftClient.getInstance().options.jumpKey.isPressed()) {
            motionY = upwardSpeed;
            if (toggle == 0 || velocity.y <= -0.035) {
                toggle = 40;
                // debug
                LOGGER.info("Fall"+toggle);
            }
            toggle--;
        } else if (MinecraftClient.getInstance().options.sprintKey.isPressed()) {
            motionY = velocity.y;
        }

        // Forward movement
        if (MinecraftClient.getInstance().options.forwardKey.isPressed() && changeForwardSpeed) {
            float yawRad = vehicle.getYaw() * MathHelper.RADIANS_PER_DEGREE;
            motionX = MathHelper.sin(-yawRad) * forwardSpeed;
            motionZ = MathHelper.cos(yawRad) * forwardSpeed;
        }

        // Apply motion
        vehicle.setVelocity(motionX, motionY, motionZ);
    }
    static PlayerEntity player = MinecraftClient.getInstance().player;
    public void setUpwardSpeed ( double upwardSpeed){
        this.upwardSpeed = upwardSpeed;
    }

    public void setForwardSpeed ( double forwardSpeed){
        this.forwardSpeed = forwardSpeed;
    }

    public void setChangeForwardSpeed ( boolean changeForwardSpeed){
        this.changeForwardSpeed = changeForwardSpeed;
    }



}