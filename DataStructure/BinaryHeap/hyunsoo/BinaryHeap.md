# Binary Heap

---

## Heap의 특징

- Heap은 트리 기반 자료구조임.
- 최댓값 및 최솟값을 빠르게 찾아낼 수 있음.
- 중복된 값을 허용.
- 느슨한 정렬(반 정렬) 상태를 유지함.
  - 큰 값(혹은 작은 값)이 상위 레벨에 있고 작은 값(혹은 큰 값)이 하위 레벨에 있다는 정도로만 정렬
- 힙 속성을 만족함.
  - 힙 속성이란?: A가 B의 부모노드이면, A의 키값과 B의 키값 사이에는 대소관계가 성립함.
  - 힙 속성은 오로지 부모노드와 자식노드 간에만 성립. 특히 형제 사이에는 대소관계가 정해지지 않음.
- 힙 속성으로 인해, 가장 높거나 낮은 우선순위를 갖는 노드가 항상 루트 노드에 존재하게 됨.
  - 이를 이용해서 우선순위 큐와 같은 추상적 자료형을 구현함.
- 크게 두 가지 종류가 존재함. -> 최대 힙, 최소 힙
  - 최대 힙   
    ![최대 힙](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FyXt2a%2Fbtq7ddSvksp%2FabjtbzX0kb5mbKWHgS84d1%2Fimg.png)
    - 부모노드의 키값이 자식노드의 키값보다 항상 크거나 같은(작지 않은) 힙
    - 가장 큰 값이 루트 노드에 존재
    - Key(Parent) >= Key(Child)
  - 최소 힙  
    ![최소 힙](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FLulip%2Fbtq66t3mygU%2FXhwpPwIBf7gl580EV5cLa0%2Fimg.png)
    - 부모노드의 키값이 자식노드의 키값보다 항상 작거나 같은(크지 않는) 힙
    - 가장 작은 값이 루트 노드에 존재
    - Key(Parent) <= Key(Child)
- BinaryHeap
  - 자식 노드의 개수가 최대 2개인 힙
  - BinaryHeap은 완전 이진 트리를 기반으로한 자료구조임.
  - 대부분의 힙은 이진 힙을 사용함. 

## Heap의 구현
- 일반적으로 배열을 사용하여 구현
- 구현을 쉽게 하기 위하여 배열의 첫 번째 인덱스인 0은 사용하지 않음.
- 특정 위치의 노드 번호는 데이터가 추가된다고 해서 변하지 않음.
  - ex) 루트 노드의 오른쪽 노드는 3번인데 데이터가 더 들어온다고해서 노드의 번호가 바뀌지는 않음.

## Heap에서 부모 노드와 자식 노드의 관계
![Heap에서 부모 노드와 자식 노드의 관계](https://gmlwjd9405.github.io/images/data-structure-heap/heap-index-parent-child.png)
- 왼쪽 자식의 번호(인덱스) = 부모의 번호(인덱스) * 2
- 오른쪽 자식의 번호(인덱스) = 부모의 번호(인덱스) * 2 + 1
- 부모의 번호(인덱스) = 자식의 번호(인덱스) / 2


## Heap의 주요 연산

### Heapify 
- 이진 트리에서 힙 속성을 유지하는 작업
- 위에서 아래로 내려가거나 아래에서 위로 올라가며 작업을 진행
- 작업 진행과정(최대 힙 기준, 위 -> 아래의 방향 기준)
  1. 현재 노드와 자식 노드의 값을 비교
  2. 만약 자식 노드 값이 크다면 자식들 중(왼쪽, 오른쪽) 더 큰 값으로 교환
  3. 힙 속성이 유지될 때 까지 위의 과정을 반복
  
ex) 값이 8인 노드에 대해 heapify를 수행하는 예시
![heapify](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FY4nXi%2Fbtq7bht5z6Q%2FmXCNuinbNgPwx9Y399Slo0%2Fimg.png)

### Build Heap
- 완전 이진 트리를 heap 구조로 만드는 작업
- 배열로 표현된 힙은 n/2 + 1 노드 부터 n 노드까지 Leaf Node라는 속성이 존재
  - 즉, leaf node를 제외한 나머지 노드(1 ~ n/2)에 heapify를 수행하면 heap구조로 만들 수 있음.

ex) build heap을 수행하는 과정  
![build heap](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FS3Tqt%2Fbtq66k6jqvn%2FZLIwIycf6pSa1Ip9o5vD31%2Fimg.png)

### min or max 반환
- Heap의 Root Node에 있는 Value를 반환함
- Heap에서 모든 Child의 Value는 자신의 Parent의 Value보다 작거나 크므로, Parent가 없는 Root Node의 Value가 최소 혹은 최대임이 보장됨.
- 시간복잡도: O(1)

### Insert(value)
- Heap에 Prioriy가 Value인 Node를 추가
- 힙은 완전 이진 트리이므로 우선 맨 마지막 노드로 추가
- 아래에서 위로 Heapify 연산을 사용해서 힙의 성질을 유지
- 시간복잡도: O(log n)

### Delete(value)
- Heap에 Prioriy가 제일 큰(Root) 노드를 삭제
- 루트 노드를 삭제(반환)한 후 마지막 노드를 루트 노드로 이동(마지막 노드 삭제)
- 위에서 아래로 Heapify 연산을 사용해서 힙의 성질을 유지
- 시간복잡도: O(log n)

## Heap Sort
- 최대 힙 트리나 최소 힙 트리를 구성해 정렬을 하는 방법
- 내림차순 정렬을 원하면 최대 힙을, 오름차순 정렬을 원하면 최소 힙을 구성
- 과정
  - 최대 힙을 구현
  - 루트 노드를 삭제 후 배열의 뒤에 넣어줌
  - heapify 연산
  - 위의 과정들을 반복
  
- Heap Sort 그림으로 과정 보기
![Heap_Sort_1](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F5d2c2b06-676c-45dd-b7cd-c20c22134345%2F%ED%9E%99%EC%A0%95%EB%A0%AC1.png)
![Heap_Sort_2](https://velog.velcdn.com/images%2Femplam27%2Fpost%2F38089014-6d91-4285-80bf-bd524270a4f3%2F%ED%9E%99%EC%A0%95%EB%A0%AC2.png)
  
### Heap Sort의 특징
- 시간 복잡도가 좋음
- 가장 크거나 가장 작은 값을 구할 때 유용함
  - 최소 힙 or 최대 힙의 루트 값이므로 한 번의 힙 구성을 통해 구할 수 있음

### Heap Sort의 시간 복잡도
- n개의 노드를 가진 힙 트리의 깊이는 log n(완전 이진 트리)임.
  - 하나의 요소를 힙에 삽입하거나 삭제할 때 heapify 연산을 하므로 O(log n)이 소요
- 요소의 개수가 n개 이므로 총 O(nlog n)의 시간이 소요됨.

## 우선순위 큐를 일반적으로 Heap으로 구현하는 이유

| 우선순위 큐를 구현하는 자료구조 | 삽입      | 삭제      |
|-------------------|---------|---------|
| 순서가 없는 배열         | O(1)    | O(n)    |
| 순서가 없는 연결 리스트     | O(1)    | O(n)    |
| 정렬된 배열            | O(n)    | O(1)    |
| 정렬된 연결 리스트        | O(n)    | O(1)    |
| 힙(heap)           | O(logn) | O(logn) |

### 출처
- [[자료구조] 힙(heap)이란](https://gmlwjd9405.github.io/2018/05/10/data-structure-heap.html)
- [이진 힙(Binary Heap)](https://kayuse88.github.io/binary-heap/)
- [[자료구조] 힙 (Heap) or 이진 힙(binary heap)](https://yoongrammer.tistory.com/80)
- [힙 (자료 구조)](https://ko.wikipedia.org/wiki/%ED%9E%99_(%EC%9E%90%EB%A3%8C_%EA%B5%AC%EC%A1%B0))
- [[알고리즘] 힙 정렬(heap sort)이란](https://gmlwjd9405.github.io/2018/05/10/algorithm-heap-sort.html)
- [[알고리즘] 그림으로 알아보는 힙정렬(Heap Sort)](https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-%ED%9E%99%EC%A0%95%EB%A0%ACHeap-Sort)

