# Queue

---

## Queue

![큐의 구조](https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Data_Queue.svg/440px-Data_Queue.svg.png)

큐는 한쪽 끝(rear)에서는 삽입연산만, 다른 한쪽 끝(front)에서는 삭제연산만 이루어지는 FIFO(First In First Out)형식의 자료구조이다.
> FIFO(First In First Out) 형식이란? 가장 먼저 들어간 데이터가 가장 먼저 나오게 되는 형식  
> -> 선입선출> 

## 큐의 연산

큐의 주요 연산들은 아래와 같다.

- enQueue: 큐의 front에 데이터 삽입
- deQueue: 큐의 rear에 있는 데이터 삭제
- peek: 큐의 front에 있는 데이터를 반환
- isEmpty: 큐가 비어있는지 확인. 비어있다면 true를 반환

## 큐의 종류

### 배열을 이용한 큐
배열을 이용하여 구현한 큐는 크기가 한정적임.

- 선형 큐
  - front가 rear가 동일하다면 큐가 비어있음을 의미
  - rear가 마지막 인덱스를 가리킨다면 큐가 포화상태라고 인식
  - enQueue, deQueue를 반복하다보면, 앞에 빈 공간이 있음에도 포화상태로 인식하는 경우가 발생.
    - 삽입 시 rear 증가, 삭제 시 front 증가 연산이 동작.
    - 빈 공간을 활용하기 위해서는 별도의 연산이 필요. 
      - deQueue 시 데이터를 한 칸씩 앞으로 이동하는 방식 등..
- 원형 큐
  - 선형 큐의 포화상태 인식 문제를 보안하기 위한 큐
  - 논리적으로는 배열이 처음과 끝이 연결된 형태
    - 인덱스를 활용하기 위해 나머지 연산자를 이용. 
  - 공백 상태, 포화 상태를 구분하기 위해 front가 가리키는 자리는 비어있음.
    - 하나의 공간은 항상 비어있음(모든 공간을 활용하도록 구현은 가능). 
  - front와 rear가 동일한 지점을 가리킬 경우 원형큐가 비어있음을 의미
  - rear의 다음 위치가 front의 위치와 동일한 경우 원형큐가 포화상태임을 의미.

### 연결 자료구조를 이용한 큐
연결 자료구조를 이용한 큐는 크기에 제약이 없음.

- 연결 큐
  - front, rear를 인덱스가 아니라 참조로 활용 
  - 데이터의 개수에 제약이 없음.
  - 배열을 이용한 큐보다 성능은 낮음.

## 큐의 시간 복잡도

| 연산  | Big O |
|-----|-------|
| 삽입  | O(1)  |
| 삭제  | O(1)  |
| 탐색  | O(N)  |

## 큐의 활용처
통상적으로 큐는 데이터가 입력된 순서대로 처리해야할 필요가 있을 때 사용한다.
- ex) 스케쥴링 큐

### 출처

- [위키백과](https://ko.wikipedia.org/wiki/%ED%81%90_(%EC%9E%90%EB%A3%8C_%EA%B5%AC%EC%A1%B0))
- [[Data Structure] 큐(Queue)란?](https://hu-coding.tistory.com/20)
- [큐의 개념과 연산](https://velog.io/@suitepotato/00004)
- [[자료구조] 원형 큐(Circular Queue) 설명 및 구현](https://leejinseop.tistory.com/37)
- [[자료구조] 연결 큐(Linked Queue) 설명 및 구현](https://leejinseop.tistory.com/38)