CREATE TABLE `short_url_map` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Auto increment primary key.',
                                 `l_url` varchar(160) NOT NULL DEFAULT '' COMMENT 'Original long url.',
                                 `s_url` varchar(10) NOT NULL DEFAULT '' COMMENT 'Short url.',
                                 `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time.',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `l_url` (`l_url`),
                                 UNIQUE KEY `s_url` (`s_url`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='The original long url and short url map table.';