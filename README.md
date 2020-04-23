#####① GET: http://localhost:8080/est/rouge/todos
Example:
URL: http://localhost:8080/est/rouge/todos
```
Input:
```
```
Output:
{
    "result": [
        {
            "id": 1,
            "work_name": "Study Flutter",
            "start_date": "2020/04/20",
            "end_date": "2020/04/25",
            "status": 0
        },
        {
            "id": 2,
            "work_name": "Study React Native",
            "start_date": "2020/04/22",
            "end_date": "2020/04/30",
            "status": 1
        }
    ]
}
```
#####② GET: http://localhost:8080/est/rouge/todos/{id}
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
        "work_name": "Study Flutter",
        "start_date": "2020/04/20",
        "end_date": "2020/04/25",
        "status": 0
    }
}
```

#####③ DELETE: http://localhost:8080/est/rouge/todos/{id}
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
        "work_name": "Study Flutter",
        "start_date": "2020/04/20",
        "end_date": "2020/04/25",
        "status": 0
    }
}
```

#####④ POST: http://localhost:8080/est/rouge/todos
Example:
URL: http://localhost:8080/est/rouge/todos
```
Input:
{
    "work_name": "Study CI/CD",
    "start_date": "2020/06/22",
    "end_date": "2020/08/30",
    "status": 1
}
```
```
Output:
{
    "result": {
        "id": 11,
        "work_name": "Study CI/CD",
        "start_date": "2020/06/22",
        "end_date": "2020/08/30",
        "status": 1
    }
}
```

#####⑤ UPDATE: http://localhost:8080/est/rouge/todos/{id}
Example:
URL: http://localhost:8080/est/rouge/todos/11
```
Input:
{
    "work_name": "Study N3",
    "start_date": "2020/12/15",
    "end_date": "2021/12/15",
    "status": 2
}
```
```
Output:
{
    "result": {
        "id": 11,
        "work_name": "Study N3",
        "start_date": "2020/12/15",
        "end_date": "2021/12/15",
        "status": 2
    }
}
```

#####⑥ Example response for error
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
#####⑦ Example response for success
```
{
    "result": [
        {
            "id": 1,
            "work_name": "Study Flutter",
            "start_date": "2020/04/20",
            "end_date": "2020/04/25",
            "status": 0
        },
        {
            "id": 2,
            "work_name": "Study React Native",
            "start_date": "2020/04/22",
            "end_date": "2020/04/30",
            "status": 1
        }
    ]
}
```