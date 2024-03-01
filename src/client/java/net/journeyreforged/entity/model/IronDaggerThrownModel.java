package net.journeyreforged.entity.model;

import net.journeyreforged.item.gear.dagger.ThrowableDaggerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class IronDaggerThrownModel extends EntityModel<ThrowableDaggerEntity> {
        public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("journeyreforged", "iron_dagger_thrown"), "main");
        private ModelPart bb_main;
        public IronDaggerThrownModel(ModelPart root) {
                this.bb_main = root.getChild("bb_main");
        }

        public static TexturedModelData getTexturedModelData() {
                ModelData modelData = new ModelData();
                ModelPartData modelPartData = modelData.getRoot();
                ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

                ModelPartData pommel_r1 = bb_main.addChild("pommel_r1", ModelPartBuilder.create().uv(20, 3).cuboid(3.75F, 3.75F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(6, 0).cuboid(-1.25F, 2.75F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                        .uv(14, 3).cuboid(2.75F, -1.25F, -0.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 0).cuboid(2.75F, 2.75F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 4).cuboid(1.75F, 1.75F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                        .uv(6, 3).cuboid(-0.25F, -0.25F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(6, 3).cuboid(-1.25F, -1.25F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(6, 3).cuboid(-2.25F, -2.25F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(6, 3).cuboid(-3.25F, -3.25F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(6, 3).cuboid(-4.25F, -4.25F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(6, 3).cuboid(-5.25F, -5.25F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 4).cuboid(-6.25F, -6.25F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 7).cuboid(-6.25F, -6.25F, -0.5F, 13.0F, 13.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.6036F, 0.0F, -1.5708F, -0.7854F, 1.5708F));
                return TexturedModelData.of(modelData, 28, 21);
        }

        @Override
        public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
                bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        }

        @Override
        public void setAngles(ThrowableDaggerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

        }
}