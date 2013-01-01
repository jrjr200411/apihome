CREATE TABLE `t_ued_quit_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(100) NOT NULL,
  `end_node` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_ued_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `encode_url` varchar(50) NOT NULL,
  `surl` varchar(100) DEFAULT NULL,
  `encode_surl` varchar(50) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `pub_time` datetime DEFAULT NULL,
  `kind` varchar(100) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `thumbnail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_encode_url` (`encode_url`) USING BTREE,
  UNIQUE KEY `uni_encode_surl` (`encode_surl`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_ued_article_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `art_id` int(11) DEFAULT NULL,
  `encode_url` varchar(50) NOT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `topic_url` varchar(200) NOT NULL,
  `encode_topic_url` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_encode_url_topic_url` (`encode_url`,`encode_topic_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_ued_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `topic_url` varchar(200) DEFAULT NULL,
  `encode_topic_url` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_encode_topic_url` (`encode_topic_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_ued_rss_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `website` varchar(100) DEFAULT NULL,
  `rss` varchar(100) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `is_all` tinyint(1) NOT NULL DEFAULT '0',
  `selector` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_rss` (`rss`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


