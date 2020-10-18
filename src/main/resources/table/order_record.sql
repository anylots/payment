CREATE TABLE IF NOT EXISTS `order_record`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `order_id` VARCHAR(64) NOT NULL,
   `user_id` VARCHAR(64) NOT NULL,
   `status` INT NOT NULL,
   `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
   `updatetime` datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   PRIMARY KEY ( `id` ),
   UNIQUE KEY uk_order_id(order_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;