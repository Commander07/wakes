package com.goby56.wakes.render;


import com.goby56.wakes.WakesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;

public enum RenderType {
    AUTO(null),
    SOLID(ShaderProgramKeys.RENDERTYPE_SOLID),
    TRANSLUCENT(ShaderProgramKeys.RENDERTYPE_TRANSLUCENT),
    CUTOUT(ShaderProgramKeys.RENDERTYPE_CUTOUT),
    ENTITY_SOLID(ShaderProgramKeys.RENDERTYPE_ENTITY_SOLID),
    ENTITY_TRANSLUCENT(ShaderProgramKeys.RENDERTYPE_ENTITY_TRANSLUCENT),
    ENTITY_TRANSLUCENT_CULL(ShaderProgramKeys.RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE),
    ENTITY_CUTOUT(ShaderProgramKeys.RENDERTYPE_ENTITY_CUTOUT),
    ENTITY_CUTOUT_NO_CULL(ShaderProgramKeys.RENDERTYPE_ENTITY_CUTOUT_NO_CULL),
    ENTITY_CUTOUT_NO_CULL_Z_OFFSET(ShaderProgramKeys.RENDERTYPE_ENTITY_CUTOUT_NO_CULL_Z_OFFSET)
    ;

    public final ShaderProgramKey program;

    RenderType(ShaderProgramKey program) {
        this.program = program;
    }

    public static ShaderProgram getProgram() {
        if (WakesClient.CONFIG_INSTANCE.renderType == RenderType.AUTO) {
            if (MinecraftClient.isFabulousGraphicsOrBetter()) {
                return MinecraftClient.getInstance().getShaderLoader().getOrCreateProgram(ENTITY_CUTOUT.program);
            } else {
                return MinecraftClient.getInstance().getShaderLoader().getOrCreateProgram(ENTITY_TRANSLUCENT_CULL.program);
            }
        }
        return MinecraftClient.getInstance().getShaderLoader().getOrCreateProgram(WakesClient.CONFIG_INSTANCE.renderType.program);
    }
}
