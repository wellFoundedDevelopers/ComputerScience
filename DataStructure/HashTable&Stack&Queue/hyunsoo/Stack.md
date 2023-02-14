# Stack

---

## Stack

![스택의 구조](https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Data_stack.svg/600px-Data_stack.svg.png)

스택은 한 쪽 끝에서만 자료를 넣거나 뺄 수 있는 LIFO(Last In First Out) 형식의 자료구조이다.
> LIFO(Last In First Out) 형식이란? 가장 최근에 들어간 데이터가 가장 먼저 나오게 되는 형식  
> -> 후입선출

## 스택의 연산

스택의 주요 연산들은 아래와 같다.

- pop: 스택의 top에 있는 항목을 제거
- push: 데이터를 스택의 top에 추가
- peek: 스택의 top에 있는 데이터를 반환(제거 x)
- isEmpty: 스택이 비어있는지 확인. 비어있다면 true를 반환

## 배열을 이용한 스택 VS 연결 자료구조를 이용한 스택

- 배열을 이용한 스택
  - 마지막 데이터의 인덱스를 가리키는 Stack Pointer를 사용.
  - Stack Pointer로 원소의 개수를 쉽게 파악할 수 있음.
  - 배열이 가지는 단점을 가짐.
    - 저장할 수 있는 데이터 수의 한계가 있음.(정적인 크기)
    - 할당하고 사용하지 않아도 할당된 크기 많큼 메모리를 차지.
- 연결 자료구조를 이용한 스택
  - 저장할 수 있는 데이터의 수가 유동적임.(동적인 크기)
  - 연산 시간이 배열로 구현한 스택에 비해서 느림.

## 스택의 시간 복잡도

| 연산  | Big O |
|-----|-------|
| 삽입  | O(1)  |
| 삭제  | O(1)  |
| 탐색  | O(N)  |

## 스택의 활용처

- 웹 혹은 앱에서 뒤로가기
- 작업 취소(undo)
- 데이터의 순서를 뒤집기(역순 문자열 만들기 등)
- 재귀 호출 최적화 -> 재귀 호출 대신 스택 + 반복문 사용

### 출처

- [위키백과](https://ko.wikipedia.org/wiki/%EC%8A%A4%ED%83%9D)
- [[자료구조] 스택(Stack)이란](https://gmlwjd9405.github.io/2018/08/03/data-structure-stack.html)