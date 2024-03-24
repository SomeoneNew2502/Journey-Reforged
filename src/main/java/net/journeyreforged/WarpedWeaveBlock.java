// Custom block class for WARPED_WEAVE to handle fall damage reduction and non-flammability
    public static class WarpedWeaveBlock extends Block {
        public WarpedWeaveBlock(AbstractBlock.Settings settings) {
            super(settings);
        }

        @Override
        public void onLandedUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
            entity.handleFallDamage(fallDistance, 0.2f); // Reduces fall damage like hay bales
        }
    }
}