CREATE TABLE IF NOT EXISTS cities(
  id                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
  name                   VARCHAR      NOT NULL,
  description            VARCHAR      NOT NULL,
  slug                   VARCHAR      NOT NULL,
  added_at               DATETIME     NOT NULL
);