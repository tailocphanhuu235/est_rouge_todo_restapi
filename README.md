##### ① GET: http://localhost:8080/est/rouge/todos
Example:
URL: http://localhost:8080/est/rouge/todos?pageNo=0&pageSize=2&sortBy=status
```
Input:
```
```
Output:
{
    "result": [
        {
            "id": 1,
            "workName": "Study Flutter",
            "startDate": "2020/04/20",
            "endDate": "2020/04/25",
            "status": 0
        },
        {
            "id": 3,
            "workName": "Study JavaScript",
            "startDate": "2020/05/22",
            "endDate": "2020/05/30",
            "status": 0
        }
    ]
}
```
##### ② GET: http://localhost:8080/est/rouge/todos/{id}
Example:
URL: http://localhost:8080/est/rouge/todos/1
```
Input:
```
```
Output:
{
    "result": {
        "id": 1,
        "workName": "Study Flutter",
        "startDate": "2020/04/20",
        "endDate": "2020/04/25",
        "status": 0
    }
}
```

##### ③ DELETE: http://localhost:8080/est/rouge/todos/{id}
Example:
URL: http://localhost:8080/est/rouge/todos/1
```
Input:
```
```
Output:
{
    "result": {
        "id": 1,
        "workName": "Study Flutter",
        "startDate": "2020/04/20",
        "endDate": "2020/04/25",
        "status": 0
    }
}
```

##### ④ POST: http://localhost:8080/est/rouge/todos
Example:
URL: http://localhost:8080/est/rouge/todos
```
Input:
{
    "workName": "Study CI/CD",
    "startDate": "2020/06/22",
    "endDate": "2020/08/30",
    "status": 1
}
```
```
Output:
{
    "result": {
        "id": 11,
        "workName": "Study CI/CD",
        "startDate": "2020/06/22",
        "endDate": "2020/08/30",
        "status": 1
    }
}
```

##### ⑤ UPDATE: http://localhost:8080/est/rouge/todos/{id}
Example:
URL: http://localhost:8080/est/rouge/todos/11
```
Input:
{
    "workName": "Study N3",
    "startDate": "2020/12/15",
    "endDate": "2021/12/15",
    "status": 2
}
```
```
Output:
{
    "result": {
        "id": 11,
        "workName": "Study N3",
        "startDate": "2020/12/15",
        "endDate": "2021/12/15",
        "status": 2
    }
}
```

##### ⑥ Example response for error
```
{
    "errors": [
        {
            "code": "ERROR_CODE_500",
            "message": "500 Internal Server Error",
            "item": null
        }
    ]
}
```
##### ⑦ Example response for success
```
{
    "result": [
        {
            "id": 1,
            "workName": "Study Flutter",
            "startDate": "2020/04/20",
            "endDate": "2020/04/25",
            "status": 0
        },
        {
            "id": 2,
            "workName": "Study React Native",
            "startDate": "2020/04/22",
            "endDate": "2020/04/30",
            "status": 1
        }
    ]
}
```
##### ⑧ SQL
```
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
```