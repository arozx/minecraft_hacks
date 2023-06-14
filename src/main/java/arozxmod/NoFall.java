package arozxmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Overwrite;

public final class NoFall {
    public static final Logger LOGGER = LoggerFactory.getLogger("NoFallMod");
    public static void onUpdate(MinecraftClient client) {

        ClientPlayerEntity player = client.player;

        if(player.fallDistance <= (player.isFallFlying() ? 1 : 2))
            return;

        if(player.isFallFlying() && player.isSneaking()
                && !isFallingFastEnoughToCauseDamage(player))
            return;

        player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));


        LOGGER.info(player.fallDistance +"is Fall Flying | "+player.isFallFlying());

    }

    private static boolean isFallingFastEnoughToCauseDamage(ClientPlayerEntity player)
    {
        return player.getVelocity().y < -0.5;
    }
}
