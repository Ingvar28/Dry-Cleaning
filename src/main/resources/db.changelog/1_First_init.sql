CREATE TABLE `drycleaning`.`client` (
    `id` BIGINT NOT NULL AUTO_INCREMENT ,
     `first_name` VARCHAR(20) NOT NULL ,
      `last_name` VARCHAR(40) NOT NULL ,
       `phone` VARCHAR(15) NOT NULL ,
        `email` VARCHAR(40) NOT NULL ,
        `client _level` VARCHAR(40) NOT NULL ,
         `description` VARCHAR(255) NOT NULL ,
          `order` BIGINT NOT NULL ,
           PRIMARY KEY (`id`)
            UNIQUE INDEX 'email_UNIQUE' ('email' ASC) VISIBLE)
 ENGINE = MyISAM;