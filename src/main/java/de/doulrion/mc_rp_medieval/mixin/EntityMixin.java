package de.doulrion.mc_rp_medieval.mixin;

import de.doulrion.mc_rp_medieval.areamanager.AreaHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class EntityMixin {

	@Unique
	private final StatusEffect curse = StatusEffects.SPEED;
	private  AreaHandler areas = AreaHandler.getInstance();


	@Inject(method = "tick()V", at = @At(value = "TAIL"))
	private void tick(CallbackInfo cb) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (! (entity instanceof ServerPlayerEntity playerEntity) ) return;
		if ( playerEntity.isCreative() || playerEntity.isSpectator() ) return;

		var curseLevelAtPlayerLocation = areas.getAreaLevel(playerEntity.getPos().x, playerEntity.getPos().z );

		if ( curseLevelAtPlayerLocation == 0 ) return;

		if (! playerEntity.hasStatusEffect(curse)) {
			applyEffect(playerEntity, curseLevelAtPlayerLocation, 0);
			return;
		}

		var currentEffectDuration = Objects.requireNonNull(playerEntity.getStatusEffect(curse)).getDuration();
		var currentEffectLevel = Objects.requireNonNull(playerEntity.getStatusEffect(curse)).getAmplifier();
		if (currentEffectDuration <= 750) {
			applyEffect(playerEntity, curseLevelAtPlayerLocation, currentEffectLevel);
		}
	}

	private void applyEffect(ServerPlayerEntity player, int curseLevelAtLocation,int currentEffectLevel) {
		player.setStatusEffect(
				new StatusEffectInstance(curse, 1000, currentEffectLevel + curseLevelAtLocation)
				, player);
	}
}