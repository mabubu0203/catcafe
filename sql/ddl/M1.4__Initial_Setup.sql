CREATE TABLE IF NOT EXISTS notice
(
    id                          INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'お知らせID',
    store_id                    INT UNSIGNED COMMENT '店舗ID',
    summary                     VARCHAR(256)                NOT NULL COMMENT 'お知らせ概要',
    detail                      VARCHAR(1024) COMMENT 'お知らせ詳細',
    publication_start_date_time DATETIME COMMENT '掲載開始日時',
    publication_end_date_time   DATETIME COMMENT '掲載終了日時',
    created_date_time           DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    created_by                  INT UNSIGNED                NOT NULL DEFAULT 0 COMMENT '登録者',
    version                     INT UNSIGNED                NOT NULL DEFAULT 0 COMMENT 'Version',
    updated_date_time           DATETIME                             DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    updated_by                  INT UNSIGNED                         DEFAULT NULL COMMENT '更新者',
    deleted_date_time           DATETIME                             DEFAULT NULL COMMENT '削除日時',
    deleted_flag                ENUM ('is_false','is_true') NOT NULL DEFAULT 'is_false' COMMENT '論理削除フラグ:is_false-未削除,is_true-削除済',
    PRIMARY KEY (id)
) DEFAULT CHARSET = UTF8MB4 COMMENT 'お知らせ';

CREATE TABLE IF NOT EXISTS provide_service
(
    id                INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'お知らせID',
    store_id          INT UNSIGNED                NOT NULL COMMENT '店舗ID',
    name              VARCHAR(256)                NOT NULL COMMENT '提供サービス名',
    price             DECIMAL(10, 3)              NOT NULL COMMENT '価格(税抜き)',
    detail            VARCHAR(1024) COMMENT '提供サービス詳細',
    created_date_time DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    created_by        INT UNSIGNED                NOT NULL DEFAULT 0 COMMENT '登録者',
    version           INT UNSIGNED                NOT NULL DEFAULT 0 COMMENT 'Version',
    updated_date_time DATETIME                             DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    updated_by        INT UNSIGNED                         DEFAULT NULL COMMENT '更新者',
    deleted_date_time DATETIME                             DEFAULT NULL COMMENT '削除日時',
    deleted_flag      ENUM ('is_false','is_true') NOT NULL DEFAULT 'is_false' COMMENT '論理削除フラグ:is_false-未削除,is_true-削除済',
    PRIMARY KEY (id)
) DEFAULT CHARSET = UTF8MB4 COMMENT '提供サービス';

CREATE TABLE IF NOT EXISTS frequently_asked_question
(
    id                INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'FAQID',
    store_id          INT UNSIGNED COMMENT '店舗ID',
    category          VARCHAR(256)                NOT NULL COMMENT 'お問い合わせカテゴリー',
    question_summary  VARCHAR(256)                NOT NULL COMMENT '質問概要',
    question_detail   VARCHAR(1024) COMMENT '質問詳細',
    answer_summary    VARCHAR(256)                NOT NULL COMMENT '回答概要',
    answer_detail     VARCHAR(1024) COMMENT '回答詳細',
    created_date_time DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    created_by        INT UNSIGNED                NOT NULL DEFAULT 0 COMMENT '登録者',
    version           INT UNSIGNED                NOT NULL DEFAULT 0 COMMENT 'Version',
    updated_date_time DATETIME                             DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    updated_by        INT UNSIGNED                         DEFAULT NULL COMMENT '更新者',
    deleted_date_time DATETIME                             DEFAULT NULL COMMENT '削除日時',
    deleted_flag      ENUM ('is_false','is_true') NOT NULL DEFAULT 'is_false' COMMENT '論理削除フラグ:is_false-未削除,is_true-削除済',
    PRIMARY KEY (id)
) DEFAULT CHARSET = UTF8MB4 COMMENT 'お知らせ';