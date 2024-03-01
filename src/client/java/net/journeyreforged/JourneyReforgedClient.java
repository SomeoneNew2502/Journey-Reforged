package net.journeyreforged;

import com.mojang.datafixers.optics.Prism;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.journeyreforged.entity.RendererRegistry;
import net.journeyreforged.entity.model.*;

public class JourneyReforgedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		RendererRegistry.register();
		EntityModelLayerRegistry.registerModelLayer(WoodenDaggerThrownModel.LAYER_LOCATION, WoodenDaggerThrownModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(StoneDaggerThrownModel.LAYER_LOCATION, StoneDaggerThrownModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(GoldenDaggerThrownModel.LAYER_LOCATION, GoldenDaggerThrownModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(IronDaggerThrownModel.LAYER_LOCATION, IronDaggerThrownModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(DiamondDaggerThrownModel.LAYER_LOCATION, DiamondDaggerThrownModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(NetheriteDaggerThrownModel.LAYER_LOCATION, NetheriteDaggerThrownModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PrismarineDaggerThrownModel.LAYER_LOCATION, PrismarineDaggerThrownModel::getTexturedModelData);
	}
}