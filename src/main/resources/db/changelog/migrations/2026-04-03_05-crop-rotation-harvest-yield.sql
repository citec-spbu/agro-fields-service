-- Фактическая урожайность для аналитики план/факт по сезону (т/га)
ALTER TABLE crop_rotations
    ADD COLUMN IF NOT EXISTS harvest_yield_t_per_ha double precision NULL;
