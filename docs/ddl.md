---
title: Data Definition Language (DDL)
description: The SQL DDL to generate the database.
menu: DDL
order: 30
---

```sqlite
CREATE TABLE IF NOT EXISTS `Die`
(
    `die_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`        TEXT,
    `calc_symbol` TEXT
);

CREATE INDEX IF NOT EXISTS `index_Die_name` ON `Die` (`name`);

CREATE INDEX IF NOT EXISTS `index_Die_calc_symbol` ON `Die` (`calc_symbol`);

CREATE TABLE IF NOT EXISTS `Face`
(
    `face_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`    TEXT,
    `die_id`  INTEGER                           NOT NULL,
    FOREIGN KEY (`die_id`) REFERENCES `Die` (`die_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Face_name` ON `Face` (`name`);

CREATE INDEX IF NOT EXISTS `index_Face_die_id` ON `Face` (`die_id`);

CREATE TABLE IF NOT EXISTS `Formula`
(
    `formula_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`       TEXT,
    `formula`    TEXT,
    `save_path`  TEXT,
    `die_id`     INTEGER                           NOT NULL,
    FOREIGN KEY (`die_id`) REFERENCES `Die` (`die_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Formula_name` ON `Formula` (`name`);

CREATE INDEX IF NOT EXISTS `index_Formula_die_id` ON `Formula` (`die_id`);

CREATE TABLE IF NOT EXISTS `History`
(
    `history_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `created`    INTEGER                           NOT NULL,
    `name`       TEXT,
    `formula`    TEXT,
    `result`     TEXT
);

CREATE INDEX IF NOT EXISTS `index_History_created` ON `History` (`created`);
```

[ddl.sql](sql/ddl.sql)