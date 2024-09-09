package com.sarinsa.tomfoolery.client.render.entity.ghastinator;

import com.sarinsa.tomfoolery.common.entity.living.Ghastinator;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

/**
 * Essentially a copy of the vanilla Ghast model,
 * but with a non-stupid pivot point.
 */
public class GhastinatorModel extends HierarchicalModel<Ghastinator> {

    private final ModelPart root;
    private final ModelPart[] tentacles = new ModelPart[9];

    public GhastinatorModel(ModelPart root) {
        this.root = root;

        for(int i = 0; i < tentacles.length; ++i) {
            tentacles[i] = root.getChild(createTentacleName(i));
        }
    }

    private static String createTentacleName(int i) {
        return "tentacle" + i;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        partDefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 23.6F, 0.0F));
        RandomSource random = RandomSource.create(1660L);

        for(int i = 0; i < 9; ++i) {
            float f = (((float)(i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
            float f1 = ((float)(i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
            int j = random.nextInt(7) + 8;

            partDefinition.addOrReplaceChild(
                    createTentacleName(i),
                    CubeListBuilder.create()
                            .texOffs(0, 0)
                            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, (float)j, 2.0F),
                    PartPose.offset(f, 30.6F, f1));
        }
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    @Override
    public void setupAnim(Ghastinator ghastinator, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        root.xRot = headPitch * (float) Math.PI / 180.0F;

        for (int i = 0; i < tentacles.length; ++i) {
            tentacles[i].xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + (float)i) + 0.4F;
        }
    }

    @Override
    public ModelPart root() {
        return root;
    }
}
