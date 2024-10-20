-- liquibase formatted sql

-- changeset asavershin:createTables

CREATE TABLE IF NOT EXISTS seasons
(
    season_id uuid PRIMARY KEY,
    season_start_date DATE NOT NULL,
    season_end_date DATE NOT NULL,
    season_description VARCHAR (256),
    organization_id uuid
    );

CREATE TABLE if not exists fields
(
    field_id uuid PRIMARY KEY,
    field_name VARCHAR (100) NOT NULL,
    field_description VARCHAR (256),
    season_id uuid,
    FOREIGN KEY (season_id) REFERENCES seasons (season_id)
    );

CREATE TABLE IF NOT EXISTS contours
(
    contour_id uuid PRIMARY KEY,
    contour_square_area VARCHAR (40) NOT NULL,
    contour_geom GEOMETRY NOT NULL,
    contour_description VARCHAR (256),
    contour_color VARCHAR (6) NOT NULL,
    field_id uuid not null,
    FOREIGN KEY (field_id) REFERENCES fields (field_id)
    );

CREATE TABLE if not exists crop_rotations
(
    crop_rotation_id uuid PRIMARY KEY,
    contour_id uuid,
    FOREIGN KEY (contour_id) REFERENCES contours (contour_id),
    crop_rotation_start_date DATE NOT NULL,
    crop_rotation_end_date DATE NOT NULL,
    crop_rotation_culture VARCHAR (20),
    crop_rotation_cultivar VARCHAR (20),
    crop_rotation_description VARCHAR (256)
    );

CREATE TABLE if not exists soil_compositions
(
    soil_composition_id uuid PRIMARY KEY,
    contour_id uuid,
    FOREIGN KEY (contour_id) REFERENCES contours (contour_id),
    soil_composition_ph VARCHAR (10),
    soil_composition_sample_date DATE NOT NULL,
    soil_composition_organic_matter VARCHAR (10),
    soil_composition_mobile_p VARCHAR (10),
    soil_composition_mobile_k VARCHAR (10),
    soil_composition_mobile_s VARCHAR (10),
    soil_composition_nitrate_n VARCHAR (10),
    soil_composition_ammonium_n VARCHAR (10),
    soil_composition_hydrolytic_acidity VARCHAR (10),
    soil_composition_ca_exchange VARCHAR (10),
    soil_composition_mg_exchange VARCHAR (10),
    soil_composition_b VARCHAR (10),
    soil_composition_co VARCHAR (10),
    soil_composition_mn VARCHAR (10),
    soil_composition_zn VARCHAR (10)
    );








