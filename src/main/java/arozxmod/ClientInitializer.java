package arozxmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;


@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class ClientInitializer implements ClientModInitializer {

    public static int count = Autoclicker.cps;
    public static boolean playerJoin = false;
    private static void onStartTick(MinecraftClient client) {
        if(Config.flyingEnabled) {
            BoatFlying.onUpdate();
        }
        if(Config.normalFlyingEnabled) {
            Flying.tick(client);
        }
        if(Config.noFallEnabled && playerJoin) {
            NoFall.onUpdate(client);
        }
        if(Config.nightVisionEnabled) {
            NightVision.onUpdate(client);
        }
        if(Config.autoclickerEnabledL || Config.autoclickerEnabledR) {
            Autoclicker.onUpdate();
        }
    }
    private static void onPlayerJoin(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient client) {
        playerJoin = !playerJoin;
    }
    private static void onPlayerDisconnect(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient client) {
        playerJoin = !playerJoin;
    }

    @Override
    public void onInitializeClient() {

        System.out.println("Client INITIALIZED!");
        ClientTickEvents.START_CLIENT_TICK.register(ClientInitializer::onStartTick);
        ClientPlayConnectionEvents.JOIN.register(ClientInitializer::onPlayerJoin);
        ClientPlayConnectionEvents.DISCONNECT.register(ClientInitializer::onPlayerDisconnect);
    }
}
