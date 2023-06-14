package arozxmod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NightVision {
    public static final Logger LOGGER = LoggerFactory.getLogger("NightVisionMod");
    public static void getBrightness(MinecraftClient client) {
        SimpleOption<Double> gamma = client.options.getGamma();
    }
}
