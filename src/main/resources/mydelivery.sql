-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: mydelivery
-- Created: Sun Jan 29 01:05:49 2017
-- Workbench Version: 6.3.6
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema mydelivery
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `mydelivery` ;
CREATE SCHEMA IF NOT EXISTS `mydelivery` ;

-- ----------------------------------------------------------------------------
-- Table mydelivery.cities_master
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydelivery`.`cities_master` (
  `id` INT(11) NULL DEFAULT NULL,
  `state_id` INT(11) NULL DEFAULT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `created_on` DATETIME NULL DEFAULT NULL,
  `created_by` TEXT NULL DEFAULT NULL,
  `modified_on` DATETIME NULL DEFAULT NULL,
  `modified_by` TEXT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table mydelivery.countries_master
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydelivery`.`countries_master` (
  `id` BIGINT(20) NOT NULL,
  `short_name` VARCHAR(5) NULL DEFAULT NULL,
  `name` VARCHAR(150) NOT NULL,
  `phone_country_code` VARCHAR(10) NOT NULL,
  `status` TINYINT(3) NOT NULL DEFAULT '1',
  `ceated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ceated_by` VARCHAR(20) NULL DEFAULT NULL,
  `modified_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table mydelivery.states_master
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydelivery`.`states_master` (
  `id` INT(11) NULL DEFAULT NULL,
  `country_id` INT(11) NULL DEFAULT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `created_on` DATETIME NULL DEFAULT NULL,
  `created_by` TEXT NULL DEFAULT NULL,
  `modified_on` DATETIME NULL DEFAULT NULL,
  `modified_by` TEXT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table mydelivery.user
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydelivery`.`user` (
  `user_id` BIGINT(20) NOT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `password` VARCHAR(50) NULL DEFAULT NULL,
  `mobileNo` VARCHAR(20) NULL DEFAULT NULL,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `gender` VARCHAR(10) NULL DEFAULT NULL,
  `status` TINYINT(3) NULL DEFAULT '0',
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table mydelivery.user_profile
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydelivery`.`user_profile` (
  `profile_id` BIGINT(3) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`profile_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table mydelivery.user_profile_relation
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydelivery`.`user_profile_relation` (
  `user_id` BIGINT(3) NOT NULL,
  `profile_id` BIGINT(3) NOT NULL,
  INDEX `user_profile_relation_fk0` (`user_id` ASC),
  INDEX `user_profile_relation_fk1` (`profile_id` ASC),
  CONSTRAINT `user_profile_relation_fk0`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydelivery`.`user` (`user_id`),
  CONSTRAINT `user_profile_relation_fk1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `mydelivery`.`user_profile` (`profile_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;
SET FOREIGN_KEY_CHECKS = 1;