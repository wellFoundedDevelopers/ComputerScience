# HashTable

---

## HashTable

해시 테이블은 (Key, Value)로 데이터를 저장하는 자료구조 중 하나로 데이터를 *빠르게 검색*할 수 있음

- 내부적으로 버킷(배열)을 사용하여 데이터를 저장하기 때문
- 각 key값에 해시함수를 적용하여 배열의 고유한 index를 생성하고 이 Index를 활용하여 값을 저장하거나 검색함.

> 즉, hash function을 통해 key값을 hash값으로 변환 후 이 hash를 index로 사용하여 배열에 저장.

![해시 테이블](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Hash_table_3_1_1_0_1_0_0_SP.svg/630px-Hash_table_3_1_1_0_1_0_0_SP.svg.png)

> ("John Smith", "521-1234") 라는 (key,value) 형식의 데이터를 해시 테이블에 삽입하는 과정은 아래와 같다.

- hash function을 사용하여 "John Smith"의 hash를 구한다.   
  -> hash = 2
- 구한 hash값을 buckets의 index로 사용하여 value를 저장한다.  
  -> 2번 인덱스에 "521-1234"를 저장

특정 값을 찾을 때도 해시 함수를 1번만 호출하는 것으로 해당 데이터에 접근이 가능.

## HashTable의 장점

- 적은 리소스로 많은 데이터를 효율적으로 관리할 수 있음.
- key / hash가 연관이 없어 보안이 좋음.
- get(key), put(key)에 캐시 로직을 추가하면 자주 hit하는 데이터를 바로 찾을 수 있음.
  - 데이터 캐싱에 많이 사용 됨. 
- 배열의 인덱스를 사용하므로 삽입, 삭제, 검색이 평균적으로(충돌이 없는 경우) O(1)의 시간 복잡도를 가짐.

## HashTable의 단점

- 해시 충돌이 발생할 경우 시간 복잡도가 O(N)에 수렴
- ~~연속적인 메모리를 참조하기에는 비효율적임(캐시의 지역성)~~
- 순서없이 key-value로 값을 저장하므로 순서가 필요한 데이터에는 적합하지 않음(순서 보장 X)
- 공간 복잡도가 큼
- 해시 함수 의존도가 높음

## Hash Collision

해시 충돌이란 해시 함수가 서로 다른 두 개의 입력값에 대해 동일한 출력값을 내는 상황을 의미  
해시 충돌에 의한 문제를 해결하기 위한 방법은 크게 2가지가 있다.

- 분리 연결법(Separate Chaining)
- 개방 주소법(Open Addressing)

### Separate Chaining

Separate Chaining 방식은 동일한 버킷에 저장된 데이터들을 자료구조를 활용하여 저장하는 방식이다.
일반적으로 Linked List를 사용하지만 데이터의 수가 많아지면 Red-Black Tree를 사용하기도 함.(데이터가 8개 이상일 경우)
- JDK 7까지는 Linked List만 사용
- JDK 8부터 Linked List, Red Black Tree 혼용
![Separate Chaining](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Hash_table_5_0_1_1_1_1_1_LL.svg/900px-Hash_table_5_0_1_1_1_1_1_LL.svg.png)
> Linked List를 사용하여 충돌을 해결한 모습

- 인덱스가 충돌하면, 인덱스가 가리키고 있는 Linked List에 노드를 추가하여 값을 삽입.
- 데이터를 탐색할 땐 인덱스의 Linked List를 선형 탐색
- Separate Chaining은 Linked List 혹은 RB Tree 구조를 활용하므로 추가하는 데이터 수의 제약이 적음.  
  단, 한 버킷에 들어있는 데이터의 수가 많아지면 효율이 감소.

### Open Addressing

Open Addressing 방식은 Linked List와 같은 추가적인 메모리 공간을 사용하지 않고, hash table 배열의 빈 공간을 사용하는 방식이다.  
추가적인 메모리 공간을 사용하지 않으므로 Separate Chaining 방식에 비해 메모리를 덜 사용함.  
Linear Probing, Quadratic Probing, Double Hashing 등이 있음.

- Linear Probing
    - 충돌이 발생한 Hash를 기준으로 일정 수치(n)만큼 건너뛰며 비어있는 Hash를 찾음.
    - 특정 hash값의 주변 bucket이 전부 채워져 있는, Clustering이 발생함.(데이터 밀집)
- Quadratic Probing
    - 충돌이 발생한 Hash를 기준으로 n^2만큼 건너뛰며 비어있는 Hash를 찾음.
    - Linear Probing 보다는 넓은 기준으로 찾기 때문에 탐색, 삭제가 효율적일 수 있음.
    - Linear Probing과 마찬가지로 Clustering 문제가 발생할 수 있음.
- Double Hashing
    - Clustering 문제를 해결하기 위해 도입
    - 충돌이 발생하면 또 다른 hash function으로 hash를 생성함.
    - 기존 hash function은 최초 hash를 얻을 때 사용하고 다른 hash function은 탐사의 폭을 얻기 위해 사용.
    - 최초의 hash가 같더라도, 다른 hash function을 거치기 때문에 여러 공간에 골고루 저장될 확률이 높아짐.
    - 단, 또 다른 hash function으로 나온 hash 값이 bucket 사이즈와 서로소여야 함.  
      그렇지 않으면, 한 번도 접근하지는 못하는 공간이 발생.

![Linear Probing](https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Hash_table_5_0_1_1_1_1_0_SP.svg/760px-Hash_table_5_0_1_1_1_1_0_SP.svg.png)
> Linear Probing 방식

### Open Addressing 방식의 문제
중간 연결고리 역할을 하는 key(hash)를 삭제하게되면, 이미 있는 값도 없다고 판단할 수가 있음.
-> 중복된 데이터가 다른 인덱스에 저장되게 됨.

**해결책**
- 삭제 시 해당 인덱스를 그냥 지워버리는 것이 아닌 삭제했다고 *표시*
  - 해당 표시를 만나면 다음 위치도 확인해봐야함을 알 수 있음.
  - 단, 무조건 다음 위치를 확인해야해서 추가적인 비용 발생.
- 삭제 위치 다음 Open Addressing된 key들을 한 단계씩 앞으로 옮기기
  - Open Addressing이 된 값인지 확인하는 비용 발생
  - 해당 데이터들을 이동시키는 추가적인 비용 발생

## Resizing

hash table은 유한한 공간에 무한한 데이터를 담기위한 자료구조이기 때문에 어느 순간에는 bucket이 꽉 차게 된다.

- Separate changing일 경우
    - 버킷이 일정 수준으로 채워지면, 각 버킷에 있는 Linked List의 길이가 늘어나고 검색 성능이 떨어지게 됨.
    > 성능 향상을 위해 Resizing
- Open addressing일 경우
    - 고정 크기 버킷을 사용하기 때문에 버킷이 완전히 채워지는 경우가 생김.
    > 배열의 크기 확장을 위해 Resizing

리사이징은 보통 기존 버킷의 2배 크기로 새로운 버킷을 할당한 후 기존 데이터를 이전합니다.   
보통 load Factor가 0.75일 경우 발생한다.
> 즉, 현재 데이터 개수가 해시 버킷 개수의 75%가 될때 리사이징이 진행됨.

### Load Factor

- 적재율
- 현재 해시 테이블에 저장된 개수(n)을 bucket의 개수(k)로 나눈 것.
- load factor = n / k

### Java에서 HashMap과 HashTable의 차이

Java에서 둘의 차이는 동기화 지원 여부 및 null의 허용 여부이다.

| 기능      | Hash Table | Hash Map |
|---------|------------|----------|
| 동기화 지원  | O          | X        |
| Null 허용 | X          | O        |

### 출처

- [[자료구조] 해시테이블(HashTable)이란?](https://mangkyu.tistory.com/102)
- [자료구조 - Hash Table (해시 테이블)](https://think0wise.tistory.com/66)
- [[자료구조] Hash/HashTable/HashMap](https://hee96-story.tistory.com/48)
- [관련 이미지들](https://en.wikipedia.org/wiki/Hash_table)
- [해시맵의 해시 충돌 해결 방법 (hash map & hash collision 해결)](https://www.youtube.com/watch?v=dKqv1mQotNU&ab_channel=%EC%89%AC%EC%9A%B4%EC%BD%94%EB%93%9C)