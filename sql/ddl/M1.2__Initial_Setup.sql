CREATE TABLE IF NOT EXISTS store
(
    id                INT UNSIGNED        NOT NULL COMMENT '店舗ID',
    name              VARCHAR(256)        NOT NULL COMMENT '店舗名',
    phone_number      CHAR(13) COMMENT '店舗連絡先(電話番号)',
    mail_address      VARCHAR(256) COMMENT '店舗連絡先(メールアドレス)',
    postal_code       CHAR(8) COMMENT '店舗住所(郵便番号)',
    prefectures       VARCHAR(256)        NOT NULL COMMENT '店舗住所(都道府県)',
    address_1         VARCHAR(256) COMMENT '店舗住所(市区町村)',
    address_2         VARCHAR(256) COMMENT '店舗住所(その他)',
    address_aside     VARCHAR(256) COMMENT '店舗住所(補足)',
    google_map_url    VARCHAR(256) COMMENT '店舗住所(google map)',
    opening_time      TIME                NOT NULL COMMENT '営業時間(開店時間)',
    closing_time      TIME                NOT NULL COMMENT '営業時間(閉店時間)',
    hours_aside       VARCHAR(256)        NOT NULL COMMENT '営業時間(補足)',
    created_date_time DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    created_by        INT UNSIGNED        NOT NULL DEFAULT 0 COMMENT '登録者',
    version           INT UNSIGNED        NOT NULL DEFAULT 0 COMMENT 'Version',
    updated_date_time DATETIME                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    updated_by        INT UNSIGNED                 DEFAULT NULL COMMENT '更新者',
    deleted_date_time DATETIME                     DEFAULT NULL COMMENT '削除日時',
    deleted_flag      TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '論理削除フラグ:0-未削除,1-削除済'
) DEFAULT CHARSET = UTF8MB4 COMMENT '店舗';