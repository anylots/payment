CREATE TABLE IF NOT EXISTS `order_record`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `order_id` VARCHAR(64) NOT NULL,
   `user_id` VARCHAR(64) NOT NULL,
   `biz_type` VARCHAR(32) NOT NULL,
   `context` VARCHAR(256) NOT NULL,
   `status` INT NOT NULL,
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
   `update_time` datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   PRIMARY KEY ( `id` ),
   UNIQUE KEY uk_order_id(order_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;