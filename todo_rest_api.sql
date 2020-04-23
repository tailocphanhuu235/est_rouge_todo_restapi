DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo` (
  `id` int(11) NOT NULL DEFAULT '1',
  `work_name` varchar(50) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('1', 'Study Flutter', '2020/04/20', '2020/04/25', '0');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('2', 'Study React Native', '2020/04/22', '2020/04/30', '1');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('3', 'Study JavaScript', '2020/05/22', '2020/05/30', '0');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('4', 'Study AWS', '2020/05/22', '2020/06/30', '2');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('5', 'Study Spring Cloud', '2020/06/22', '2020/08/30', '0');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('6', 'Study Spring Security', '2020/06/22', '2020/08/30', '2');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('7', 'Study HTML', '2020/06/22', '2020/08/30', '2');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('8', 'Study CSS', '2020/06/22', '2020/08/20', '1');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('9', 'Study PL SQL', '2020/07/22', '2020/08/11', '1');
INSERT INTO `todo_app`.`todo` (`id`, `work_name`, `start_date`, `end_date`, `status`) VALUES ('10', 'Study Hadoop', '2020/07/22', '2020/08/30', '1');