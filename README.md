## Prerequisites

- Java 17+
  

## How to Run

1. **Build the project**:

```bash
mvn clean install
```
2. **Run the project**:

```bash
mvn spring-boot:run
```

3. **Access the API**:

```bash
[GET][POST][DELETE] [LABEL](http://localhost:8000/note)
```
4. **Front-End Demo**

https://github.com/PramudiaPutra/astrapay-angular-external

## Screenshot
**Validation**
<br>
[Empty Title]
<br>
<img src="screenshot/validation1.png">
<br>
[Filled Title]
<br>
<img src="screenshot/validation2.png">
<br>
[API Empty or Null Field]
<br>
<img src="screenshot/validation3.png">
<br>
[Get Id Not Found]
<br>
<img src="screenshot/validation_postman_get_by_id.png">
<br>
[Delete Id Not Found]
<br>
<img src="screenshot/validation_postman_delete_by_id.png">
<br>
<br>
**Postman**
<br>
[Create Note]
<br>
<img src="screenshot/postman_create.png">
<br>
[Get All Note]
<br>
<img src="screenshot/postman_get_all.png">
<br>
[Get Note by Id]
<br>
<img src="screenshot/postman_get_by_id.png">
<br>
[Delete Note by Id]
<br>
<img src="screenshot/postman_delete_by_id.png">
<br>
<br>
**Front End**
<br>
[Empty Note]
<br>
<img src="screenshot/front_end_empty.png">
<br>
[Insert 6 Note]
<br>
<img src="screenshot/front_end_items.png">
