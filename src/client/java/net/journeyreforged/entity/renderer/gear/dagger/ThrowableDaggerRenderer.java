package net.journeyreforged.entity.renderer.gear.dagger;

import net.journeyreforged.entity.model.*;
import net.journeyreforged.item.gear.dagger.ThrowableDaggerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ThrowableDaggerRenderer extends EntityRenderer<ThrowableDaggerEntity> {
    private static final Identifier WOOD_TEXTURE = new Identifier("journeyreforged", "textures/entity/wooden_dagger_thrown.png");
    private static final Identifier STONE_TEXTURE = new Identifier("journeyreforged", "textures/entity/stone_dagger_thrown.png");
    private static final Identifier GOLDEN_TEXTURE = new Identifier("journeyreforged", "textures/entity/golden_dagger_thrown.png");
    private static final Identifier IRON_TEXTURE = new Identifier("journeyreforged", "textures/entity/iron_dagger_thrown.png");
    private static final Identifier DIAMOND_TEXTURE = new Identifier("journeyreforged", "textures/entity/diamond_dagger_thrown.png");
    private static final Identifier NETHERITE_TEXTURE = new Identifier("journeyreforged", "textures/entity/netherite_dagger_thrown.png");
    private static final Identifier PRISMARINE_TEXTURE = new Identifier("journeyreforged", "textures/entity/prismarine_dagger_thrown.png");
    private final Context context;

    // Add more textures as needed

    public ThrowableDaggerRenderer(Context context) {
        super(context);
        this.context = context;
        // Assuming DaggerModel is your model class and you have registered an EntityModelLayer for it
    }

    @Override
    public void render(ThrowableDaggerEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        Matrix4f jomlMatrix = new Matrix4f(); // Initialize JOML Matrix4f for transformations

        if (entity.isStuck()) {
            // When the dagger is stuck, apply a fixed rotation based on stored yaw and pitch
            Quaternionf rotation = new Quaternionf().rotateY((float)Math.toRadians(-entity.getStuckYaw())); // Convert degrees to radians and apply yaw rotation
            rotation.rotateX((float)Math.toRadians(entity.getStuckPitch())); // Apply pitch rotation to make the dagger lie flat

            jomlMatrix.rotate(rotation); // Apply the rotation to the JOML matrix
        } else {
            // Normalize the velocity vector to use it for the rotation calculation
            Vector3f velocityNorm = new Vector3f((float)entity.getVelocity().x, (float)entity.getVelocity().y, (float)entity.getVelocity().z).normalize();

            // Assuming the dagger's default forward direction is along the Z-axis in the model space
            Vector3f defaultForward = new Vector3f(0.0f, 0.0f, 1.5f);

            Quaternionf rotationQuat = new Quaternionf().rotationTo(defaultForward, velocityNorm);
            Quaternionf spinQuat = new Quaternionf().rotateX((float)Math.toRadians(entity.getRotationAngle()));
            rotationQuat.mul(spinQuat); // Combine the alignment rotation with the spinning rotation

            // Create a new JOML Matrix4f for the transformation, starting with the combined rotation*/
            jomlMatrix.rotate(rotationQuat);
        }

        // Now, use your conversion method to apply this transformation to the Minecraft MatrixStack
        convertToMinecraftMatrix(jomlMatrix, matrices);

        // Use the correct model based on the dagger's material
        EntityModel<ThrowableDaggerEntity> correctModel = getModelForMaterial(entity);

        // Render the model with applied orientation
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(getTexture(entity)));
        correctModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }

    private void convertToMinecraftMatrix(Matrix4f jomlMatrix, MatrixStack minecraftMatrixStack) {
        // Decompose the JOML matrix into translation, rotation, and scale components
        Vector3f translation = new Vector3f();
        Quaternionf rotation = new Quaternionf();
        Vector3f scale = new Vector3f();
        jomlMatrix.getTranslation(translation);
        jomlMatrix.getUnnormalizedRotation(rotation);
        jomlMatrix.getScale(scale);

        // Apply translation
        minecraftMatrixStack.translate(translation.x, translation.y, translation.z);

        // Apply rotation
        minecraftMatrixStack.multiply(rotation);

        // Apply scale
        minecraftMatrixStack.scale(scale.x, scale.y, scale.z);
    }

    private EntityModel<ThrowableDaggerEntity> getModelForMaterial(ThrowableDaggerEntity entity) {
        // Correct method name from getMaterail to getDaggerType
        switch (entity.getDaggerType()) {
            case WOOD:
                return new WoodenDaggerThrownModel(context.getPart(WoodenDaggerThrownModel.LAYER_LOCATION));
            case STONE:
                return new StoneDaggerThrownModel(context.getPart(StoneDaggerThrownModel.LAYER_LOCATION));
            case GOLD:
                return new GoldenDaggerThrownModel(context.getPart(GoldenDaggerThrownModel.LAYER_LOCATION));
            case IRON:
                return new IronDaggerThrownModel(context.getPart(IronDaggerThrownModel.LAYER_LOCATION));
            case DIAMOND:
                return new DiamondDaggerThrownModel(context.getPart(DiamondDaggerThrownModel.LAYER_LOCATION));
            case NETHERITE:
                return new NetheriteDaggerThrownModel(context.getPart(NetheriteDaggerThrownModel.LAYER_LOCATION));
            case PRISMARINE:
                return new PrismarineDaggerThrownModel(context.getPart(PrismarineDaggerThrownModel.LAYER_LOCATION));
            default:
                // Consider handling an unexpected case more gracefully
                return new IronDaggerThrownModel(context.getPart(IronDaggerThrownModel.LAYER_LOCATION));
        }
    }



    @Override
    public Identifier getTexture(ThrowableDaggerEntity entity) {
        // Here, determine the material of the dagger and return the appropriate texture
        // This is a simplified example. You need to adjust it based on your actual material representation
        switch (entity.getDaggerType()) {
            case WOOD:
                return WOOD_TEXTURE;
            case STONE:
                return STONE_TEXTURE;
            case GOLD:
                return GOLDEN_TEXTURE;
            case IRON:
                return IRON_TEXTURE;
            case DIAMOND:
                return DIAMOND_TEXTURE;
            case NETHERITE:
                return NETHERITE_TEXTURE;
            case PRISMARINE:
                return PRISMARINE_TEXTURE;
            // Add more cases as needed
            default:
                return IRON_TEXTURE; // Fallback texture
        }
    }
}