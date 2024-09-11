package com.sarinsa.tomfoolery.client.render.entity.ghastinator;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sarinsa.tomfoolery.client.TomfooleryModelLayers;
import com.sarinsa.tomfoolery.common.core.Tomfoolery;
import com.sarinsa.tomfoolery.common.entity.living.Ghastinator;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class GhastinatorRenderer extends MobRenderer<Ghastinator, GhastinatorModel> {

    private static final ResourceLocation IDLE = Tomfoolery.resourceLoc("textures/entity/ghastinator/idle.png");
    private static final ResourceLocation SEES_TARGET = Tomfoolery.resourceLoc("textures/entity/ghastinator/sees_target.png");
    private static final ResourceLocation SHOOTING = Tomfoolery.resourceLoc("textures/entity/ghastinator/shooting.png");

    public GhastinatorRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastinatorModel(context.bakeLayer(TomfooleryModelLayers.GHASTINATOR)), 0.0F);
        addLayer(new GhastinatorEyesLayer(this));
    }

    @Override
    protected void scale(@Nonnull Ghastinator ghastinator, PoseStack poseStack, float partialTick) {
        poseStack.scale(100F, 100F, 100F);
    }

    @Override
    @Nonnull
    public ResourceLocation getTextureLocation(Ghastinator ghastinator) {
        if (ghastinator.isAggressive()) {
            return ghastinator.isCharging() ? SHOOTING : SEES_TARGET;
        }
        else {
            return ghastinator.isCharging() ? SHOOTING : IDLE;
        }
    }
}
