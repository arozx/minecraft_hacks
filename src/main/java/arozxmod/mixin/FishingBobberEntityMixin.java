package arozxmod.mixin;

import arozxmod.Config;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    @Shadow private boolean caughtFish;

    @Inject(method = "onTrackedDataSet", at = @At("TAIL"))
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (caughtFish && Config.autoFishingEnabled) {
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
        }
    }
}
