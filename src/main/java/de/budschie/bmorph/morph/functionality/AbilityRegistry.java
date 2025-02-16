package de.budschie.bmorph.morph.functionality;

import java.util.function.Supplier;

import de.budschie.bmorph.main.References;
import de.budschie.bmorph.morph.functionality.configurable.ActivateAbilityIf;
import de.budschie.bmorph.morph.functionality.configurable.AirSuffocationAbility;
import de.budschie.bmorph.morph.functionality.configurable.AttackYeetAbility;
import de.budschie.bmorph.morph.functionality.configurable.AttributeModifierAbility;
import de.budschie.bmorph.morph.functionality.configurable.AudiovisualEffectOnEnableAbility;
import de.budschie.bmorph.morph.functionality.configurable.BlockPassthroughAbility;
import de.budschie.bmorph.morph.functionality.configurable.BlockTrailAbility;
import de.budschie.bmorph.morph.functionality.configurable.Boom;
import de.budschie.bmorph.morph.functionality.configurable.BossbarAbility;
import de.budschie.bmorph.morph.functionality.configurable.BurnInSunAbility;
import de.budschie.bmorph.morph.functionality.configurable.ClimbingAbility;
import de.budschie.bmorph.morph.functionality.configurable.CommandOnDisable;
import de.budschie.bmorph.morph.functionality.configurable.CommandOnEnable;
import de.budschie.bmorph.morph.functionality.configurable.CommandOnUseAbility;
import de.budschie.bmorph.morph.functionality.configurable.ConfigurableAbility;
import de.budschie.bmorph.morph.functionality.configurable.CowMilkAbility;
import de.budschie.bmorph.morph.functionality.configurable.DamageImmunityAbility;
import de.budschie.bmorph.morph.functionality.configurable.DelegateOnDamageAbility;
import de.budschie.bmorph.morph.functionality.configurable.DisableMorphItem;
import de.budschie.bmorph.morph.functionality.configurable.EffectOnAttackEntity;
import de.budschie.bmorph.morph.functionality.configurable.EffectOnUseAbility;
import de.budschie.bmorph.morph.functionality.configurable.ElderGuardianJumpscareAbility;
import de.budschie.bmorph.morph.functionality.configurable.FlyAbility;
import de.budschie.bmorph.morph.functionality.configurable.GuardianAbility;
import de.budschie.bmorph.morph.functionality.configurable.ImmuneToDamageIfAbility;
import de.budschie.bmorph.morph.functionality.configurable.InstaDeathOnCookieAbility;
import de.budschie.bmorph.morph.functionality.configurable.InstaJumpAbility;
import de.budschie.bmorph.morph.functionality.configurable.InstaRegenAbility;
import de.budschie.bmorph.morph.functionality.configurable.JesusAbility;
import de.budschie.bmorph.morph.functionality.configurable.MobAttackAbility;
import de.budschie.bmorph.morph.functionality.configurable.MorphAbility;
import de.budschie.bmorph.morph.functionality.configurable.NoFlames;
import de.budschie.bmorph.morph.functionality.configurable.NoKnockbackAbility;
import de.budschie.bmorph.morph.functionality.configurable.ParrotDanceAbility;
import de.budschie.bmorph.morph.functionality.configurable.PassiveEffectAbility;
import de.budschie.bmorph.morph.functionality.configurable.PassiveTickCommandAbility;
import de.budschie.bmorph.morph.functionality.configurable.PhantomAbility;
import de.budschie.bmorph.morph.functionality.configurable.PredicateAbility;
import de.budschie.bmorph.morph.functionality.configurable.ProjectileShootingAbility;
import de.budschie.bmorph.morph.functionality.configurable.PufferfishAbility;
import de.budschie.bmorph.morph.functionality.configurable.RandomDelegatingOnUseAbility;
import de.budschie.bmorph.morph.functionality.configurable.SheepEatGrassAbility;
import de.budschie.bmorph.morph.functionality.configurable.SoundOnUseAbility;
import de.budschie.bmorph.morph.functionality.configurable.SquidBoostAbility;
import de.budschie.bmorph.morph.functionality.configurable.TeleportAbility;
import de.budschie.bmorph.morph.functionality.configurable.TeleportOnProjectileContactAbility;
import de.budschie.bmorph.morph.functionality.configurable.TransformEntityOnDeath;
import de.budschie.bmorph.morph.functionality.configurable.WalkOnPowderedSnowAbility;
import de.budschie.bmorph.morph.functionality.configurable.WaterBreathingAbility;
import de.budschie.bmorph.morph.functionality.configurable.WaterDislikeAbility;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = Bus.MOD)
public class AbilityRegistry
{	
	public static final ResourceLocation ABILITY_REGISTRY_ID = new ResourceLocation(References.MODID, "abilities");
	
	public static DeferredRegister<ConfigurableAbility<? extends Ability>> ABILITY_REGISTRY = DeferredRegister.<ConfigurableAbility<? extends Ability>>create(ABILITY_REGISTRY_ID, References.MODID); 
	
	@SuppressWarnings("unchecked")
	public static Supplier<IForgeRegistry<ConfigurableAbility<? extends Ability>>> REGISTRY = ABILITY_REGISTRY.<ConfigurableAbility<? extends Ability>>makeRegistry((Class<ConfigurableAbility<?>>)((Class<?>)ConfigurableAbility.class), () -> new RegistryBuilder<ConfigurableAbility<?>>().disableSaving().setName(ABILITY_REGISTRY_ID));
	
	public static RegistryObject<ConfigurableAbility<Boom>> BOOM = ABILITY_REGISTRY.register("boom", () -> new ConfigurableAbility<>(Boom.CODEC));
	public static RegistryObject<ConfigurableAbility<AttackYeetAbility>> ATTACK_YEET = ABILITY_REGISTRY.register("yeet", () -> new ConfigurableAbility<>(AttackYeetAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<ClimbingAbility>> CLIMBING_ABILITY = ABILITY_REGISTRY.register("climbing", () -> new ConfigurableAbility<>(ClimbingAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<FlyAbility>> FLYING_ABILITY = ABILITY_REGISTRY.register("flying", () -> new ConfigurableAbility<>(FlyAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<PassiveTickCommandAbility>> PASSIVE_TICK_COMMAND_ABILITY = ABILITY_REGISTRY.register("ticking_command", () -> new ConfigurableAbility<>(PassiveTickCommandAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<CommandOnEnable>> COMMAND_ON_ENABLE_ABILITY = ABILITY_REGISTRY.register("command_on_enable", () -> new ConfigurableAbility<>(CommandOnEnable.CODEC));
	public static RegistryObject<ConfigurableAbility<CommandOnDisable>> COMMAND_ON_DISABLE_ABILITY = ABILITY_REGISTRY.register("command_on_disable", () -> new ConfigurableAbility<>(CommandOnDisable.CODEC));
	public static RegistryObject<ConfigurableAbility<CommandOnUseAbility>> COMMAND_ON_USE_ABILITY = ABILITY_REGISTRY.register("command_on_use", () -> new ConfigurableAbility<>(CommandOnUseAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<AttributeModifierAbility>> ATTRIBUTE_MODIFIER_ABILITY = ABILITY_REGISTRY.register("attribute_modifier", () -> new ConfigurableAbility<>(AttributeModifierAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<DamageImmunityAbility>> DAMAGE_IMMUNITY_ABILITY = ABILITY_REGISTRY.register("damage_immunity", () -> new ConfigurableAbility<>(DamageImmunityAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<EffectOnAttackEntity>> EFFECT_ON_ATTACK_ABILITY = ABILITY_REGISTRY.register("effect_on_attack", () -> new ConfigurableAbility<>(EffectOnAttackEntity.CODEC));
	public static RegistryObject<ConfigurableAbility<InstaDeathOnCookieAbility>> COOKIE_DEATH = ABILITY_REGISTRY.register("cookie_death", () -> new ConfigurableAbility<>(InstaDeathOnCookieAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<InstaJumpAbility>> INSTA_JUMP_ABILITY = ABILITY_REGISTRY.register("insta_jump", () -> new ConfigurableAbility<>(InstaJumpAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<InstaRegenAbility>> INSTA_REGEN_ABILITY = ABILITY_REGISTRY.register("insta_regen", () -> new ConfigurableAbility<>(InstaRegenAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<MobAttackAbility>> MOB_ATTACK_ABILITY = ABILITY_REGISTRY.register("mob_attack", () -> new ConfigurableAbility<>(MobAttackAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<NoKnockbackAbility>> NO_KNOCKBACK_ABILITY = ABILITY_REGISTRY.register("no_knockback", () -> new ConfigurableAbility<>(NoKnockbackAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<PassiveEffectAbility>> PASSIVE_EFFECT_ABILITY = ABILITY_REGISTRY.register("passive_effect", () -> new ConfigurableAbility<>(PassiveEffectAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<ProjectileShootingAbility>> PROJECTILE_SHOOTING_ABILITY = ABILITY_REGISTRY.register("shoot_projectile", () -> new ConfigurableAbility<>(ProjectileShootingAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<SquidBoostAbility>> SQUID_BOOST_ABILITY = ABILITY_REGISTRY.register("squid_boost", () -> new ConfigurableAbility<>(SquidBoostAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<TeleportAbility>> TELEPORT_ABILITY = ABILITY_REGISTRY.register("teleport", () -> new ConfigurableAbility<>(TeleportAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<WaterBreathingAbility>> WATER_BREATHING_ABILITY = ABILITY_REGISTRY.register("water_breathing", () -> new ConfigurableAbility<>(WaterBreathingAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<WaterDislikeAbility>> WATER_DISLIKE_ABILITY = ABILITY_REGISTRY.register("water_dislike", () -> new ConfigurableAbility<>(WaterDislikeAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<BlockPassthroughAbility>> BLOCK_PASSTHROUGH_ABILITY = ABILITY_REGISTRY.register("block_passthrough", () -> new ConfigurableAbility<>(BlockPassthroughAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<SoundOnUseAbility>> SOUND_ON_USE_ABILITY = ABILITY_REGISTRY.register("sound_on_use", () -> new ConfigurableAbility<>(SoundOnUseAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<PufferfishAbility>> PUFFERFISH_ABILITY = ABILITY_REGISTRY.register("pufferfish", () -> new ConfigurableAbility<>(PufferfishAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<GuardianAbility>> GUARDIAN_ABILITY = ABILITY_REGISTRY.register("guardian_laser", () -> new ConfigurableAbility<>(GuardianAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<ElderGuardianJumpscareAbility>> ELDER_GUARDIAN_JUMPSCARE_ABILITY = ABILITY_REGISTRY.register("elder_guardian_jumpscare", () -> new ConfigurableAbility<>(ElderGuardianJumpscareAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<RandomDelegatingOnUseAbility>> RANDOM_DELEGATING_ON_USE_ABILITY = ABILITY_REGISTRY.register("random_delegation_on_use", () -> new ConfigurableAbility<>(RandomDelegatingOnUseAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<WalkOnPowderedSnowAbility>> WALK_ON_POWDERED_SNOW = ABILITY_REGISTRY.register("walk_on_powder_snow", () -> new ConfigurableAbility<>(WalkOnPowderedSnowAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<BurnInSunAbility>> BURN_IN_SUN_ABILITY = ABILITY_REGISTRY.register("burn_in_sun", () -> new ConfigurableAbility<>(BurnInSunAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<TransformEntityOnDeath>> TRANSFORM_ENTITY_ON_DEATH_ABILITY = ABILITY_REGISTRY.register("transform_on_kill", () -> new ConfigurableAbility<>(TransformEntityOnDeath.CODEC));
	public static RegistryObject<ConfigurableAbility<AirSuffocationAbility>> SUFFOCATE_ON_LAND = ABILITY_REGISTRY.register("suffocate_on_land", () -> new ConfigurableAbility<>(AirSuffocationAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<PhantomAbility>> PHANTOM_FLIGHT = ABILITY_REGISTRY.register("phantom_glide", () -> new ConfigurableAbility<>(PhantomAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<ParrotDanceAbility>> PARROT_DANCE = ABILITY_REGISTRY.register("parrot_dance", () -> new ConfigurableAbility<>(ParrotDanceAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<BlockTrailAbility>> BLOCK_TRAIL = ABILITY_REGISTRY.register("block_trail", () -> new ConfigurableAbility<>(BlockTrailAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<SheepEatGrassAbility>> SHEEP_EAT_GRASS = ABILITY_REGISTRY.register("sheep_eat_grass", () -> new ConfigurableAbility<>(SheepEatGrassAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<CowMilkAbility>> COW_MILK = ABILITY_REGISTRY.register("cow_milk", () -> new ConfigurableAbility<>(CowMilkAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<MorphAbility>> MORPH_ABILITY = ABILITY_REGISTRY.register("morph_ability", () -> new ConfigurableAbility<>(MorphAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<PredicateAbility>> PREDICATE_ABILITY = ABILITY_REGISTRY.register("predicate_ability", () -> new ConfigurableAbility<>(PredicateAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<DelegateOnDamageAbility>> DELEGATE_ON_DAMAGE_ABILITY = ABILITY_REGISTRY.register("delegate_on_damage", () -> new ConfigurableAbility<>(DelegateOnDamageAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<EffectOnUseAbility>> EFFECT_ON_USE_ABILITY = ABILITY_REGISTRY.register("effect_on_use", () -> new ConfigurableAbility<>(EffectOnUseAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<DisableMorphItem>> STUN_MORPH_ABILITY = ABILITY_REGISTRY.register("disable_morph_item", () -> new ConfigurableAbility<>(DisableMorphItem.CODEC));
	public static RegistryObject<ConfigurableAbility<BossbarAbility>> BOSSBAR_ABILITY = ABILITY_REGISTRY.register("bossbar", () -> new ConfigurableAbility<>(BossbarAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<AudiovisualEffectOnEnableAbility>> AUDIOVISUAL_EFFECT_ON_ENABLE = ABILITY_REGISTRY.register("audiovisual_effect_on_enable", () -> new ConfigurableAbility<>(AudiovisualEffectOnEnableAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<TeleportOnProjectileContactAbility>> TELEPORT_ON_PROJECTILE_CONTACT = ABILITY_REGISTRY.register("teleport_on_projectile_contact", () -> new ConfigurableAbility<>(TeleportOnProjectileContactAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<ImmuneToDamageIfAbility>> IMMUNE_TO_DAMAGE_IF = ABILITY_REGISTRY.register("immune_to_damage_if", () -> new ConfigurableAbility<>(ImmuneToDamageIfAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<JesusAbility>> JESUS = ABILITY_REGISTRY.register("jesus", () -> new ConfigurableAbility<>(JesusAbility.CODEC));
	public static RegistryObject<ConfigurableAbility<NoFlames>> NO_FLAMES = ABILITY_REGISTRY.register("no_flames", () -> new ConfigurableAbility<>(NoFlames.CODEC));
	public static RegistryObject<ConfigurableAbility<ActivateAbilityIf>> ACTIVATE_ABILITY_IF = ABILITY_REGISTRY.register("activate_ability_if", () -> new ConfigurableAbility<>(ActivateAbilityIf.CODEC));

}

