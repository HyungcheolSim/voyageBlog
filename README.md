# voyageBlog
spring 개인과제 나만의 블로그 만들기

프로젝트 설명 & API 명세서 & 실행화면 리팩토링 과정 등등
--------------------
https://boundless-pudding-4e9.notion.site/189b7be021e5442993234dcbb0d185e3?pvs=4

----------------------

## API 명세서
링크

<details>
<summary>최종 UML</summary>
<div markdown="1">
    <img src="https://i.postimg.cc/rpbDGqqQ/voyage-Blog-usecase-Diagram-lv3.png" height="700">
</div>
</details>
<details>
<summary>최종 ERD</summary>
<div markdown="2">

<img src="https://i.postimg.cc/tTShrbJY/erd-springblog-lv3.png" height="700">

</div>
</details>
<details>
<summary>최종 DDL ->현재 스키마 일치하지 않는데, 구현 완료 후 다시 DDL 뽑아서 수정할 예정</summary>
<div markdown="2">

```

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema post
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema post
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `post` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `post`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `post`.`user` (
  `u_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `u_password` VARCHAR(255) NOT NULL,
  `role` ENUM('ADMIN', 'USER') NOT NULL,
  `u_username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_3oypjjd5orxmgq581pe1rj5q2` (`u_username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `post`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `post`.`post` (
  `p_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `modified_at` DATETIME(6) NULL DEFAULT NULL,
  `p_contents` VARCHAR(255) NOT NULL,
  `p_title` VARCHAR(255) NOT NULL,
  `p_username` VARCHAR(255) NOT NULL,
  `u_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`p_id`),
  INDEX `FKfvid82cuoi8ffelpry6l2cgxb` (`u_id` ASC) VISIBLE,
  CONSTRAINT `FKfvid82cuoi8ffelpry6l2cgxb`
    FOREIGN KEY (`u_id`)
    REFERENCES `post`.`user` (`u_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comment` (
  `c_id` BIGINT(20) NOT NULL,
  `c_contents` VARCHAR(255) NULL,
  `user_u_id` BIGINT(20) NOT NULL,
  `post_p_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`c_id`, `post_p_id`),
  INDEX `fk_comment_user_idx` (`user_u_id` ASC) VISIBLE,
  INDEX `fk_comment_post1_idx` (`post_p_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_u_id`)
    REFERENCES `post`.`user` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_p_id`)
    REFERENCES `post`.`post` (`p_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `post` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
```

</div>
</details>
