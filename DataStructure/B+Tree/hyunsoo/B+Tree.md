# B+ Tree

---

B+ Tree는 인덱스에서 순차 접근에 대한 문제의 해결책으로 제시 된 B Tree의 변형된 형태의 자료구조
- 데이터베이스의 인덱스가 B+ Tree를 활용
- 리프 노드는 연결 리스트의 형태이므로 선형 탐색이 가능
- 검색의 시간 복잡도가 굉장히 낮음
- 키 값의 삭제는 리프 노드에서만 수행

![B+Tree](https://kookyungmin.github.io/image/DataStruc_img/DataStruc_img21.png)
- B+ Tree의 Leaf 노드들은 연결 리스트 형태로 서로 연결되어 있고, 이를 순차 집합(sequence set)이라고 하며 오름차순으로 정렬되어 있음.

![index](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F64290106-d927-4a82-9e08-8e52783c7dd3%2FDB%20%EC%9D%B8%EB%8D%B1%EC%8A%A4.jpg)
위와 같은 인덱싱을 B+Tree로 나타낸다면 아래와 같음.

![indexToB+Tree](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fbcbce100-d475-4cda-aebe-946d1813949c%2FB%ED%94%8C%EB%9F%AC%EC%8A%A4%20%ED%8A%B8%EB%A6%AC%20%EA%B8%B0%EB%B3%B8%20%ED%98%95%ED%83%9C.jpg)
B트리와 B+트리의 차이점
- B+트리는 모든 key, data가 리프 노드에 모여있음.
  - B트리는 리프 노드가 아닌 각자 key마다 data를 가짐
  - B+트리는 리프 노드에 모든 data를 가짐
- B+트리는 모든 리프노드가 연결리스트의 형태임.
  - B트리는 옆에 있는 리프 노드를 검사할 때 다시 루트 노드부터 검사해야 함.
  - B+트리는 리프 노드에서 선형 검사를 수행할 수 있어 굉장히 효율적임.

## B+ Tree의 탐색 과정
- B+ Tree의 탐색 과정은 B Tree의 탐색 과정과 동일

## B+ Tree의 삽입 과정
- B+ Tree의 삽입 과정 또한 B Tree와 유사하지만 리프 노드에서 최대 key 개수를 초과할 때의 동작이 다름

### Case 1. 분할이 일어나지 않고, 삽입 위치가 리프 노드의 가장 앞 key 자리가 아닌 경우
- B Tree와 동일한 삽입 과정을 수행

### Case 2. 분할이 일어나지 않고, 삽입 위치가 리프 노드의 가장 앞 key 자리인 경우
- 삽입 후 부모 key를 삽입된 key로 갱신하고 data를 넣어줌.

![b+TreeInsertCase2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fd69592b9-c14e-4cc5-ad7b-4ea36120035c%2F%EC%82%BD%EC%9E%85%202-1.jpg)

### Case 3. 분할이 일어나는 삽입 과정

- 분할이 일어나는 노드가 리프 노드가 아니라면
  - 기존 B트리와 똑같이 분할을 진행.
  - 중간 key를 부모 key로 올리고, 분할 한 두 개의 노드를 왼쪽, 오른쪽 자식으로 설정
- 분할이 일어나는 노드가 리프 노드라면
  - 중간 key를 부모 key로 올리지만, 오른쪽 노드에 중간 key를 포함하여 분할.
    - B+트리의 리프 노드는 연결리스트이므로 왼쪽 자식 노드와 오른쪽 자식 노드를 이어줘야함.

![b+TreeInsertCase3-1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F251a8b34-b943-41c2-9391-3fea1d9a5b29%2F%EC%82%BD%EC%9E%85%203-1.jpg)
![b+TreeInsertCase3-2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fd4b9cb28-1b18-4ac1-802c-020dee95ffb9%2F%EC%82%BD%EC%9E%85%203-2.jpg)

## B+ Tree의 삭제 과정
- 삭제 과정도 마찬가지로 B Tree와 유사함.

### Case 1. 삭제할 key가 index에 없고, 리프 노드의 처음 key가 아닌 경우
- 기존 B트리 삭제 과정과 동일

![b+TreeRemoveCase1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fce1bbae2-3ade-4970-854d-e7ad9f526283%2F%EC%82%AD%EC%A0%9C%201.jpg)

### Case 2. 삭제할 key가 리프 노드의 처음 key인 경우
- 삭제할 key가 리프 노드의 처음 key일 경우에는 항상 k가 index 내에 존재
  - 리프 노드의 k를 삭제하는 과정을 수행.
    - 만약 key의 개수가 최소 key의 개수라면 B트리의 삭제 과정에 있던 형제 노드의 key를 빌려오거나 부모 key와 병합하는 과정을 동일하게 수행
    - 단, 리프 노드가 병합할 땐 부모 key와 오른쪽 자식의 처음 key가 동일하므로 부모 key를 가져오는 과정을 생략하고 왼쪽 자식과 오른쪽 자식만 병합
  - 리프 노드의 k가 삭제되었다면, index 내의 k를 inorder successor로 변경

![b+TreeRemoveCase2-1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F25579855-5b0f-4cbb-a400-9fb5a5645d0d%2F%EC%82%AD%EC%A0%9C%202-1.jpg)
![b+TreeRemoveCase2-2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F1e256a60-912f-4369-8444-e74c535ecc3c%2F%EC%82%AD%EC%A0%9C%202-2.jpg)

### 출처
- [[자료구조] 그림으로 알아보는 B+Tree](https://velog.io/@emplam27/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-B-Plus-Tree)
- [[자료구조] 2.B+-tree](https://kookyungmin.github.io/study/2018/07/29/data_structure_02/)