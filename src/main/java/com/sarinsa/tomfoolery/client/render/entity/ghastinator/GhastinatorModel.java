package com.sarinsa.tomfoolery.client.render.entity.ghastinator;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sarinsa.tomfoolery.common.entity.living.Ghastinator;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GhastinatorModel extends HierarchicalModel<Ghastinator> {


    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart[] tentacles = new ModelPart[19];

    public GhastinatorModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");

        for(int i = 0; i < tentacles.length; ++i) {
            tentacles[i] = body.getChild("tentacle_" + i);
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition tentacle_18 = body.addOrReplaceChild("tentacle_18", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 5.0F, 1.0F, 0.5672F, 0.2618F, 1.5708F));

        PartDefinition tentacle_17 = body.addOrReplaceChild("tentacle_17", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -3.0F, 1.0F, 0.5672F, -0.2618F, 1.5708F));

        PartDefinition tentacle_16 = body.addOrReplaceChild("tentacle_16", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 5.0F, 1.0F, 0.5672F, -0.2618F, -1.5708F));

        PartDefinition tentacle_15 = body.addOrReplaceChild("tentacle_15", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -3.0F, 1.0F, 0.5672F, 0.2618F, -1.5708F));

        PartDefinition tentacle_14 = body.addOrReplaceChild("tentacle_14", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 4.0F, 0.7854F, 0.0F, -1.5708F));

        PartDefinition tentacle_13 = body.addOrReplaceChild("tentacle_13", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 2.0F, -4.0F, 0.5672F, 0.0F, -1.5708F));

        PartDefinition tentacle_12 = body.addOrReplaceChild("tentacle_12", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 0.0F, 4.0F, 0.7854F, 0.0F, 1.5708F));

        PartDefinition tentacle_11 = body.addOrReplaceChild("tentacle_11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 2.0F, -4.0F, 0.5672F, 0.0F, 1.5708F));

        PartDefinition tentacle_10 = body.addOrReplaceChild("tentacle_10", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 7.0F, -5.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition tentacle_9 = body.addOrReplaceChild("tentacle_9", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 8.0F, 5.0F, 0.2182F, 0.5236F, 0.0F));

        PartDefinition tentacle_8 = body.addOrReplaceChild("tentacle_8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.0F, -5.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition tentacle_7 = body.addOrReplaceChild("tentacle_7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.0F, 5.0F, 0.2182F, -0.5236F, 0.0F));

        PartDefinition tentacle_6 = body.addOrReplaceChild("tentacle_6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 8.0F, 0.0F, 0.0873F, 0.0F, -0.0436F));

        PartDefinition tentacle_5 = body.addOrReplaceChild("tentacle_5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 8.0F, 0.0F, 0.0873F, 0.0F, 0.0436F));

        PartDefinition tentacle_4 = body.addOrReplaceChild("tentacle_4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 4.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition tentacle_3 = body.addOrReplaceChild("tentacle_3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -7.0F, 2.0F, 2.1817F, 0.0F, -0.2618F));

        PartDefinition tentacle_2 = body.addOrReplaceChild("tentacle_2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -7.0F, 2.0F, 2.1817F, 0.0F, 0.2618F));

        PartDefinition tentacle_1 = body.addOrReplaceChild("tentacle_1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -3.0F, 2.3126F, 0.0F, 0.0F));

        PartDefinition tentacle_0 = body.addOrReplaceChild("tentacle_0", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, -3.0F, 0.0873F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(Ghastinator entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        body.xRot = headPitch * (float) Math.PI / 180.0F;
        body.yRot = netHeadYaw * (float) Math.PI / 180.0F;

        for (int i = 0; i < tentacles.length; ++i) {
            float mult = entity.isAggressive() ? 3.0F : 1.0F;
            tentacles[i].xRot = 0.2F * Mth.sin(mult * ageInTicks * 0.3F + (float)i) + 0.4F;
        }
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
