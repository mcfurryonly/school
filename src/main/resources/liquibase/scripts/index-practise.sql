-- liquibase formatted sql

-- changeset amaximov:1
 CREATE INDEX student_name_index ON student (name);

 -- changeset amaximov:2
  CREATE INDEX faculty_name_index ON faculty (name);

  -- changeset amaximov:3
    CREATE INDEX faculty_color_index ON faculty (color);