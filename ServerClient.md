### XMLHttpRequest 객체

Ajax의 가장 핵심적인 구성 요소는 바로 XMLHttpRequest 객체입니다.
Ajax에서 XMLHttpRequest 객체는 웹 브라우저가 서버와 데이터를 교환할 때 사용됩니다.
웹 브라우저가 백그라운드에서 계속해서 서버와 통신할 수 있는 것은 바로 이 객체를 사용하기 때문입니다.

---

### XMLHttpRequest 객체의 생성

현재 대부분의 주요 웹 브라우저는 XMLHttpRequest 객체를 내장하고 있습니다.

이러한 XMLHttpRequest 객체를 생성하는 방법은 브라우저의 종류에 따라 다음과 같이 나눠집니다.

1. XMLHttpRequest 객체를 이용한 방법
2. ActiveXObject 객체를 이용한 방법

익스플로러 7과 그 이상의 버전, 크롬, 파이어폭스, 사파리, 오페라에서는 XMLHttpRequest 객체를 사용합니다.

```
var 변수이름 = new XMLHttpRequest();
```

---

### 서버에 요청(request)하기

Ajax에서는 XMLHttpRequest 객체를 사용하여 서버와 데이터를 교환합니다.
따라서 서버에 요청을 보내기 위해서는 우선 XMLHttpRequest 인스턴스를 생성해야 합니다.
XMLHttpRequest 인스턴스의 open() 메소드와 send() 메소드를 사용하여 요청을 보낼 수 있습니다.

---

### open() 메소드

open() 메소드는 서버로 보낼 Ajax 요청의 형식을 설정합니다.

```
open(전달방식, URL주소, 동기여부);
```

전달 방식은 요청을 전달할 방식으로 GET 방식과 POST 방식 중 하나를 선택할 수 있습니다.
URL 주소는 요청을 처리할 서버의 파일 주소를 전달합니다.
동기 여부는 요청을 동기식으로 전달할지 비동기식으로 전달할지를 전달합니다.

---

### send() 메소드

send() 메소드는 작성된 Ajax 요청을 서버로 전달합니다.
이 메소드는 전달 방식에 따라 인수를 가질 수도 안 가질 수도 있습니다.

```
send();       // GET 방식
send(문자열); // POST 방식
```

---

### GET 방식과 POST 방식

GET 방식은 주소에 데이터(data)를 추가하여 전달하는 방식입니다.
GET 방식의 HTTP 요청은 브라우저에 의해 캐시되어(cached) 저장됩니다.
또한, GET 방식은 보통 쿼리 문자열(query string)에 포함되어 전송되므로, 길이의 제한이 있습니다.
따라서 보안상 취약점이 존재하므로, 중요한 데이터는 POST 방식을 사용하여 요청하는 것이 좋습니다.

POST 방식은 데이터(data)를 별도로 첨부하여 전달하는 방식입니다.
POST 방식의 HTTP 요청은 브라우저에 의해 캐시되지 않으므로, 브라우저 히스토리에도 남지 않습니다.
또한, POST 방식의 HTTP 요청에 의한 데이터는 쿼리 문자열과는 별도로 전송됩니다.
따라서 데이터의 길이에 대한 제한도 없으며, GET 방식보다 보안성이 높습니다.

> Ajax에서는 주로 GET 방식보다는 POST 방식을 사용하여 요청을 전송합니다.

---

### GET 방식으로 요청하기

Ajax에서는 서버에 GET 방식의 요청을 보내기 위해서 다음과 같이 작성합니다.

이때 서버로 전송하고자 하는 데이터는 URI에 포함되어 전송됩니다.

```
// GET 방식으로 요청을 보내면서 데이터를 동시에 전달함.
httpRequest.open("GET", "/examples/media/request_ajax.php?city=Seoul&zipcode=06141", true);
httpRequest.send();
```

위의 예제에서 사용된 다음 코드로 XMLXttpRequest 객체의 현재 상태와 서버 상의 문서 존재 유무를 확인할 수 있습니다.

```
if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200 ) {
    ...

}
```
위의 예제에서 readyState 프로퍼티의 값이 XMLHttpRequest.DONE이면, 서버에 요청한 데이터의 처리가 완료되어 응답할 준비가 완료되었다는 의미입니다.
또한, status 프로퍼티의 값이 200이면, 요청한 문서가 서버 상에 존재한다는 의미입니다.

---

### POST 방식으로 요청하기

Ajax에서는 서버에 POST 방식의 요청을 보내기 위해서 다음과 같이 작성합니다.

이때 서버로 전송하고자 하는 데이터는 HTTP 헤더에 포함되어 전송됩니다.
따라서 setRequestHeader() 메소드를 이용하여 먼저 헤더를 작성한 후에, send() 메소드로 데이터를 전송하게 됩니다.

```
// POST 방식의 요청은 데이터를 Http 헤더에 포함시켜 전송함.

httpRequest.open("POST", "/examples/media/request_ajax.php", true);
httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
httpRequest.send("city=Seoul&zipcode=06141");
```

---

### 비동기식(asynchronous) 요청

서버에 비동기식 요청을 보내기 위해서는 open() 메소드의 세 번째 인수로 true를 전달하면 됩니다.
이렇게 서버로 비동기식 요청을 보내면, 자바스크립트는 서버로부터의 응답을 기다리면서 동시에 다른 일을 할 수 있게 됩니다.

만약 open() 메소드의 세 번째 인수로 false를 전달하면, 서버에 동기식 요청을 보내게 됩니다.
이때 자바스크립트는 서버로부터 응답이 도착할 때까지 대기하게 됩니다.
따라서 사용자는 대기하는 동안 다른 어떤 작업도 할 수 없게 됩니다.

---

### 서버로부터의 응답(response) 확인

Ajax에서 서버로부터의 응답을 확인하기 위해 사용하는 XMLHttpRequest 객체의 프로퍼티는 다음과 같습니다.

 - readyState 프로퍼티
 - status 프로퍼티
 - onreadystatechange 프로퍼티
 
---

### readyState 프로퍼티

readyState 프로퍼티는 XMLHttpRequest 객체의 현재 상태를 나타냅니다.

이 프로퍼티의 값은 객체의 현재 상태에 따라 다음과 같은 주기로 변화합니다.

> 1. UNSENT (숫자 0) : XMLHttpRequest 객체가 생성됨.
> 2. OPENED (숫자 1) : open() 메소드가 성공적으로 실행됨.
> 3. HEADERS_RECEIVED (숫자 2) : 모든 요청에 대한 응답이 도착함.
> 4. LOADING (숫자 3) : 요청한 데이터를 처리 중임.
> 5. DONE (숫자 4) : 요청한 데이터의 처리가 완료되어 응답할 준비가 완료됨.

---

### status 프로퍼티

status 프로퍼티는 서버의 문서 상태를 나타냅니다.

 - 200 : 서버에 문서가 존재함.
 - 404 : 서버에 문서가 존재하지 않음.
 
---

### onreadystatechange 프로퍼티

onreadystatechange 프로퍼티는 XMLHttpRequest 객체의 readyState 프로퍼티 값이 변할 때마다 자동으로 호출되는 함수를 설정합니다.

이 함수는 서버에서 응답이 도착할 때까지 readyState 프로퍼티 값의 변화에 따라 총 5번 호출됩니다.
이 프로퍼티를 이용하면 서버에 요청한 데이터가 존재하고, 서버로부터 응답이 도착하는 순간을 특정할 수 있습니다.

```
switch (httpRequest.readyState) {

    case XMLHttpRequest.UNSET:
        currentState += "현재 XMLHttpRequest 객체의 상태는 UNSET 입니다.<br>";
        break;

    case XMLHttpRequest.OPENED:
        currentState += "현재 XMLHttpRequest 객체의 상태는 OPENED 입니다.<br>";
        break;

    case XMLHttpRequest.HEADERS_RECIEVED:
        currentState += "현재 XMLHttpRequest 객체의 상태는 HEADERS_RECEIVED 입니다.<br>";
        break;

    case XMLHttpRequest.LOADING:
        currentState += "현재 XMLHttpRequest 객체의 상태는 LOADING 입니다.<br>";
        break;

    case XMLHttpRequest.DONE:
        currentState += "현재 XMLHttpRequest 객체의 상태는 DONE 입니다.<br>";
        break;
}

document.getElementById("status").innerHTML = currentState;
if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200 ) {
    document.getElementById("text").innerHTML = httpRequest.responseText;
}
``` 

