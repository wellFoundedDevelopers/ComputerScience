# B Tree

---

B Tree는 일반적으로 데이터베이스 및 파일 시스템에서 사용되는 효율적인 데이터 구조임.
- 이진 트리에서 발전되어 하나의 노드가 가질 수 있는 자식 노드의 최대 숫자가 2보다 큰 트리 구조
- 모든 리프 노드들이 같은 레벨을 가질 수 있도록 자동으로 벨런스를 맞추는 트리
- 노드가 가질 수 있는 최대 자식의 수가 M일 때 M차 B-Tree라고 함.

## B Tree의 특징(규칙)

![bTreeFeature1](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdbg6Xh%2Fbtrm9EanZa3%2FLMEqmbuarz95StU5B8cFtK%2Fimg.png)
- 노드 안에 k 개의 데이터(key)가 있다면 자식 노드 수는 k+1 개

![bTreeFeature2](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FyvH4z%2FbtrndHKDArJ%2FjQXwlCpJRqRaGBNVatx2O1%2Fimg.png)
- 노드 안의 데이터(key)는 정렬되어 있어야 함.

![bTreeFeature3](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FyvH4z%2FbtrndHKDArJ%2FjQXwlCpJRqRaGBNVatx2O1%2Fimg.png)
- 자식 노드의 데이터(key)는 부모의 데이터 값에 따라 배치됨.
  - n번째 자식 노드는 부모 노드의 n번째 데이터 보다 작은 값이 배치되고 마지막 자식 노드에는 부모 노드의 마지막 값 보다 더 큰 값이 배치됨.

![bTreeFeature4](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmRag2%2FbtrndneBkgA%2FPuSFowbAHmlVThZ8RpSmBk%2Fimg.png)
- 루트 노드가 리프 노드가 아닌 경우 항상 2개 이상의 자식을 가짐

![bTreeFeature5](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FyvH4z%2FbtrndHKDArJ%2FjQXwlCpJRqRaGBNVatx2O1%2Fimg.png)
- M차 B-Tree일 때 내부 노드는 최소 M/2개 이상의 데이터(key)를 가짐

![bTreeFeature6](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FluLJ3%2Fbtrnc4M8bYt%2FQk9idLTpSyHZE6FNKzdcYK%2Fimg.png)
- 모든 리프 노드의 높이(레벨)는 같음

![bTreeFeature7](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FTvHVn%2Fbtrm82I1LBf%2FDGznqLonaatWfTbUKYtHSK%2Fimg.png)
- 리프 노드의 데이터 수는 M-1 이하임.
  - 3차 B-Tree라면 리프 노드의 데이터 수는 2개 이하

- 노드의 데이터(key)가 x개 라면 자식의 수는 x+1개
- 차수가 짝수이냐 홀수이냐에 따라 알고리즘이 다름.

### 3차 B Tree의 예시

![bTreeThatMIs3](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fddbae2c9-da94-457d-bad8-77ff6791255b%2FB%ED%8A%B8%EB%A6%AC%20%EA%B8%B0%EB%B3%B8%20%ED%98%95%ED%83%9C.png)
- 위의 그림은 3차 B Tree
- 파란색 부분은 각 노드의 데이터(key) 나타냄
- 빨간색 부분은 자식 노드들을 가리키는 포인터
- 데이터(key)들은 노드 안에서 항상 정렬된 값을 가짐
- BST와 같이 각 key들의 왼쪽 자식들은 항상 key보다 작은 값을 오른쪽 자식들은 항상 key보다 큰 값을 가짐.

## B Tree의 탐색 과정

B Tree에서 key를 탐색하는 과정은 하향식으로 수행됨.(k라는 key를 검색)

- 루트 노드에서 시작해서 key들을 순회 탐색
  - 만약 k가 존재한다면 탐색 종료
  - k와 key들의 대소 관계를 비교.
    - key값들 사이에 k가 존재한다면 해당 key들 사이의 자식 노드로 내려감
- 위의 과정을 리프 노드까지 반복.
  - 리프 노드에도 k와 같은 key가 없다면 값이 존재하지 않는 것.

![bTreeSearch1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fb7df8287-2524-4ec0-ad03-b969a8830c8e%2FB%ED%8A%B8%EB%A6%AC%20%EA%B2%80%EC%83%89%201.png)
![bTreeSearch2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fe20bdef7-e106-4c89-9560-d7f57154dce1%2FB%ED%8A%B8%EB%A6%AC%20%EA%B2%80%EC%83%89%202.png)

## B Tree의 삽입 과정

B Tree에서 key를 삽입하는 과정은(k라는 key를 삽입)
- 요소가 삽입될 적절한 리프 노드를 탐색(하향식) 후 삽입
- 필요하다면 노드 분할(상향식)  

의 순서로 이루어짐.

- 트리가 비어있다면 루트 노드를 할당하고 k를 삽입
- 트리가 비어있지 않다면 삽입하기에 적절한 리프 노드를 찾아서 k를 삽입
  - 적절한 리프 노드를 탐색하는 과정은 위에 설명된 B Tree의 탐색 과정과 동일
  - 삽입 후 노드의 분할이 필요하다면 분할

### Case 1. 분할이 일어나지 않는 경우

삽입 후 리프 노드가 가득 차지 않았다면 단순 삽입

![bTreeInsertCase1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F95b4c5c3-c267-4423-865e-778a68ad4a50%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%BD%EC%9E%85%201-1.png)

### Case 2. 분할이 일어나는 경우

삽입 후 리프 노드가 가득 찼다면
- 중앙 값에서 분할을 수행
  - 중앙 값의 왼쪽 값은 왼쪽 자식으로, 오른쪽 값은 오른쪽 자식으로 분할 후 중앙 값은 부모와 병합
- 중앙 값이 병합된 노드가 가득 찼다면 분할을 반복

![bTreeInsertCase2-1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F4b5003e5-55de-441c-a3ee-15e4db7a2abd%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%BD%EC%9E%85%202-1.png)
![bTreeInsertCase2-2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F13ab96a4-04cc-42a7-bb01-eac1276bdf67%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%BD%EC%9E%85%202-2.png)
![bTreeInsertCase2-3](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fd99cdbc8-c5b4-4667-be7d-2589adca45e8%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%BD%EC%9E%85%202-3.png)

## B Tree의 삭제 과정

B Tree에서 key를 삭제하는 과정은(k라는 key를 삭제)
- 삭제할 key가 있는 노드 탐색
- key 삭제
- 필요하다면, 트리의 균형을 조정

삭제의 과정을 설명하기 위한 몇 가지 용어
- inorder predecessor
  - 노드의 왼쪽 자손에서 가장 큰 key
- inorder successor 
  - 노드의 오른쪽 자손에서 가장 작은 key
- 부모 key: 부모 노드에 있는 key들 중 왼쪽 자식으로 본인 노드를 가지고 있는 key
  - 단, 마지막 자식 노드의 경우에는 부모의 마지막 key
- k: 삭제할 key

### Case 1. 삭제할 key가 리프에 있는 경우

**Case 1.1 현재 노드의 key의 개수가 최소 key의 개수 보다 클 경우**
- 단순 해당 key 삭제

![bTreeRemoveCase1.1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fe0e2045e-33a2-439f-a781-f6f9af8d0b66%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%AD%EC%A0%9C%201-1.png)

**Case 1.2 왼쪽 혹은 오른쪽 형제 노드의 key가 최소 key 개수 이상일 경우**
- 부모 key값으로 k를 대체
- 최소키 개수 이상의 키를 가진 형제 노드가 왼쪽 형제라면 가장 큰 값을, 오른쪽 형제라면 가장 작은 값을 부모 key로 대체

![bTreeRemoveCase1.2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F8e7b0f78-ae26-48df-8925-47171c588c48%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%AD%EC%A0%9C%201-2.png)

**Case 1.3 왼쪽, 오른쪽 형제 노드의 key가 최소 key 개수이고, 부모 노드의 key가 최소 개수 이상일 경우**
- k를 삭제한 후 부모 key를 형제 노드와 병합
- 부모 노드의 key 개수를 하나 줄이고, 자식 수 또한 하나를 줄임

![bTreeRemoveCase1.3](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fdde5e5ae-892c-4d1c-9299-4710023f7531%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%AD%EC%A0%9C%201-3.png)

**Case 1.4 자신과 형제, 부모 노드의 key 개수가 모두 최소 key 개수일 경우**
- 부모 노드를 루트 노드로 한 부분 트리의 높이가 줄어드는 경우이므로 재구조화 과정일 일어남.
  - 후술할 Case 3.2 과정을 진행

### Case 2. 삭제할 key가 리프 노드가 아니고, 노드나 자식의 key가 최소 key보다 많은 경우
- 현재 노드의 `inorder predecessor` 혹은 `inorder successor`와 k의 자리를 교체
- 리프 노드로 옮겨진 k의 값을 삭제하는 동작으로 변경 됨.
  - 즉, Case 1으로 처리

![bTreeRemoveCase2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F6d4a5d37-1633-45a1-8225-c6e558031865%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%AD%EC%A0%9C%202.png)

### Case 3. 삭제할 key가 리프 노드가 아니고, 노드의 key 개수가 최소 key 개수만큼이며 노드의 자식 key 개수도 모두 최소 key 개수인 경우
- 삭제할 k가 있는 노드도 최소, 해당 노드의 자식 노드들도 최소의 key 개수를 가지므로, k를 삭제하면 트리의 높이가 줄어들어 재 구조화가 발생

**재구조화**
1. k를 삭제하고, k의 양쪽 자식을 병합
2. k의 부모 key를 인접한 형제 노드에 붙이고 위에서 병합한 노드를 자식 노드로 설정
3. 2번까지의 과정을 수행 후 결과에 따라 두 가지 동작을 진행
  - 새로 구성된 인접 형제 노드의 key가 최대 key 개수를 넘었다면, **노드 분할**을 수행
  - 새로 구성된 인접 형제 노드가 아닌 원래 k의 부모 노드가 최소 key 개수 보다 작아지면, **부모 노드에 대하여 Case 3 과정을 다시 수행**

![bTreeRemoveCase3-1,2,3-1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F84dbc50f-fff4-4207-8e27-a34b9043f798%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%AD%EC%A0%9C%203-1.png)
![bTreeRemoveCase3-3-2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2Fe2f82f30-2f9c-4177-a908-1b5333f8e9d6%2FB%ED%8A%B8%EB%A6%AC%20%EC%82%AD%EC%A0%9C%203-2.png)

### 출처

- [[MySQL] B-tree, B+tree란? (인덱스와 연관지어서)](https://zorba91.tistory.com/293)
- [[자료구조] B-트리(B-Tree)란? B트리 그림으로 쉽게 이해하기, B트리 탐색, 삽입, 삭제 과정](https://code-lab1.tistory.com/217)
- [[자료구조] 그림으로 알아보는 B-Tree](https://velog.io/@emplam27/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-B-Tree#-case-3-%EC%82%AD%EC%A0%9C%ED%95%A0-key-k%EA%B0%80-%EB%82%B4%EB%B6%80-%EB%85%B8%EB%93%9C%EC%97%90-%EC%9E%88%EA%B3%A0-%EB%85%B8%EB%93%9C%EC%97%90-key-%EA%B0%9C%EC%88%98%EA%B0%80-%EC%B5%9C%EC%86%8Ckey-%EA%B0%9C%EC%88%98%EB%A7%8C%ED%81%BC-%EB%85%B8%EB%93%9C%EC%9D%98-%EC%9E%90%EC%8B%9D-key-%EA%B0%9C%EC%88%98%EB%8F%84-%EB%AA%A8%EB%91%90-%EC%B5%9C%EC%86%8C-key-%EA%B0%9C%EC%88%98%EC%9D%B8-%EA%B2%BD%EC%9A%B0)
- [[Data Structure] B-Tree란?](https://fomaios.tistory.com/entry/Data-Structure-B-Tree%EB%9E%80)