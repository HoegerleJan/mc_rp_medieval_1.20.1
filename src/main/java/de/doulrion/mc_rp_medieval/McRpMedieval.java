package de.doulrion.mc_rp_medieval;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McRpMedieval implements ModInitializer {
	public static final String MOD_ID = "mc_rp_medieval";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Jost Bauer joined the chat!");
	}
}