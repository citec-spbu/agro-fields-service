create table if not exists seasons
(
  season_id       uuid primary key,
  name            text,
  start_date      date    not null,
  end_date        date    not null,
  organization_id uuid    not null,
  archived        boolean not null default false
);

create table if not exists fields
(
  field_id    uuid primary key,
  name        text    not null,
  description text,
  season_id   uuid    not null,
  foreign key (season_id) references seasons (season_id),
  archived    boolean not null default false
);

create table if not exists contours
(
  contour_id  uuid primary key,
  square_area text     not null,
  geom        geometry not null,
  color       text     not null,
  field_id    uuid     not null,
  foreign key (field_id) references fields (field_id),
  archived    boolean  not null default false
);

create table if not exists crop_rotations
(
  crop_rotation_id uuid primary key,
  contour_id       uuid    not null,
  foreign key (contour_id) references contours (contour_id),
  start_date       date    not null,
  end_date         date,
  culture          text,
  cultivar         text,
  description      text,
  archived         boolean not null default false
);

create table if not exists soil_compositions
(
  soil_composition_id uuid primary key,
  contour_id          uuid    not null,
  foreign key (contour_id) references contours (contour_id),
  ph                  text,
  sample_date         date    not null,
  organic_matter      text,
  mobile_p            text,
  mobile_k            text,
  mobile_s            text,
  nitrate_n           text,
  ammonium_n          text,
  hydrolytic_acidity  text,
  ca_exchange         text,
  mg_exchange         text,
  b                   text,
  co                  text,
  mn                  text,
  zn                  text,
  archived            boolean not null default false
);

commit;