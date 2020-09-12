CREATE TABLE IF NOT EXISTS cast
(
    id                    INT UNSIGNED        NOT NULL COMMENT 'キャストID',
    store_id              INT UNSIGNED        NOT NULL COMMENT '店舗ID',
    cast_cat_id           INT UNSIGNED        NOT NULL COMMENT 'キャスト(猫)ID',
    employment_status     ENUM ('main','sub') NOT NULL DEFAULT 'main' COMMENT '雇用ステータス',
    first_attendance_date DATE COMMENT '初出勤日',
    last_attendance_date DATE COMMENT '初出勤日',
    store_memo            VARCHAR(256) COMMENT '店舗メモ',
    created_date_time     DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    created_by            INT UNSIGNED        NOT NULL DEFAULT 0 COMMENT '登録者',
    version               INT UNSIGNED        NOT NULL DEFAULT 0 COMMENT 'Version',
    updated_date_time     DATETIME                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    updated_by            INT UNSIGNED                 DEFAULT NULL COMMENT '更新者',
    deleted_date_time     DATETIME                     DEFAULT NULL COMMENT '削除日時',
    deleted_flag          TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '論理削除フラグ:0-未削除,1-削除済'
) DEFAULT CHARSET = UTF8MB4 COMMENT 'キャスト';

CREATE TABLE IF NOT EXISTS cast_cat
(
    id                INT UNSIGNED        NOT NULL COMMENT 'キャスト(猫)ID',
    name              VARCHAR(256)        NOT NULL COMMENT 'キャスト(猫)名',
    type              VARCHAR(256) COMMENT '猫種',
    brothers          JSON COMMENT '兄弟',
    sisters           JSON COMMENT '姉妹',
    memo              VARCHAR(256) COMMENT 'キャストメモ',
    created_date_time DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    created_by        INT UNSIGNED        NOT NULL DEFAULT 0 COMMENT '登録者',
    version           INT UNSIGNED        NOT NULL DEFAULT 0 COMMENT 'Version',
    updated_date_time DATETIME                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    updated_by        INT UNSIGNED                 DEFAULT NULL COMMENT '更新者',
    deleted_date_time DATETIME                     DEFAULT NULL COMMENT '削除日時',
    deleted_flag      TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '論理削除フラグ:0-未削除,1-削除済'
) DEFAULT CHARSET = UTF8MB4 COMMENT 'キャスト(猫)';