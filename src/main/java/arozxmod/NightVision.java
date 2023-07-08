package arozxmod;

import net.minecraft.client.MinecraftClient;

public final class NightVision {
    private static boolean wasGammaChanged;
    private static MinecraftClient client;

    public static void onUpdate(MinecraftClient client) {

        System.out.print(MinecraftClient.getInstance().options.getGamma());
        double gamma = client.options.getGamma().getValue();
        System.out.print("Brightness started at " + gamma);

        // true if gamma > 1
        wasGammaChanged = gamma > 1;

        updateGamma();
    }

    private static void updateGamma() {

        if (ArozxMod.nightVisionEnabled) {
            setGamma(16);
            return;
        }

        if(wasGammaChanged) {
            client = MinecraftClient.getInstance();
            client.options.getGamma().setValue(0.5);
        }
    }

    private static void setGamma(double target) {

        client = MinecraftClient.getInstance();

        wasGammaChanged = true;

        double oldGammaValue = client.options.getGamma().getValue();

        if (oldGammaValue < target)
            client.options.getGamma().setValue(oldGammaValue + 0.5);
        else
            client.options.getGamma().setValue(oldGammaValue - 0.5);
    }
}