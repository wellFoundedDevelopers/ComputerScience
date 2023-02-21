# Graph

---

## Graph

연결되어 있는 원소 사이의 n:n(다:다) 관계를 표현하는 자료구조  
(노드와 그 노드를 연결하는 간선을 하나로 모아놓은 자료 구조)

![그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdhAd4e%2FbtrlEIxX9G8%2FkopKjMST75KptVuHLCx4u1%2Fimg.png)

- 그래프는 연결할 객체를 나타내는 정점(Vertex)과 객체를 연결하는 간선(Edge)의 집합으로 구성됨.
- 그래프 G는 G = (V,E)로 정의되는데 V는 정점의 집합, E는 간선들의 집합을 의미함.
- 그래프는 여러 개의 고립된 부분 그래프(Isolated Subgraphs)로 구성될 수 있음.

### 오일러 경로

- 그래프에 존재하는 모든 간선을 한 번만 통과하면서 처음 정점으로 되돌아오는 경로
- 그래프의 모든 정점에 연결된 간선의 개수가 짝수일 때만 오일러 경로가 존재

## 그래프 종류

### 무방향 그래프

- 두 정점을 연결하는 간선에 방향이 없는 그래프

![무방향 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbgQxEp%2FbtrlEJjpKxD%2F0xsWCNAF8kz7Ze8wrWFjs1%2Fimg.png)

- 위의 그래프는 G1이라고 하자.
- 무방향 그래프에서 정점 Vi와 Vj를 연결하는 간선을 (Vi,Vj)로 표현함
  - (Vi, Vj)와 (Vj, Vi)는 같은 간선을 나타냄. 
- V(G1) = {A,B,C,D}
- E(G1) = {(A,B),(A,D)(B,C)(B,D),(C,D)}

### 방향 그래프

- 간선에 방향이 있는 그래프
- 다이그래프(Digraph)라고도 함

![방향 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F2c2fb%2FbtrlC0UbhKt%2FzhQdHKmiMMyvdTKXtK5Ro0%2Fimg.png)

- 위의 그래프는 G2라고 하자.
- 방향 그래프에서 정점 Vi와 Vj를 연결하는 간선을 <Vi, Vj>로 표현하는데 Vi를 꼬리(tail), Vj를 머리(head)라고 함.
  - <Vi, Vj>와 <Vj, Vi>는 서로 다른 간선을 나타냄.
- V(G2) = {A,B,C,D}
- E(G2) = {<A,B>,<A,D>,<B,C>,<B,D>,<C,D>}

### 완전 그래프

- 한 정점에서 다른 모든 정점과 연결되어 최대 간선 수를 갖는 그래프
- 정점이 n개인 무방향 그래프에서는 최대 간선 수가 n(n-1) / 2 개
- 정점이 n개인 방향 그래프에서는 최대 간선 수가 n(n-1) 개

![완전 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcBqrFE%2FbtrlEngy5jI%2FMruknEvKhPNAcokrUrGsKK%2Fimg.png)

- 왼쪽 그래프는 G3, 오른쪽 그래프는 G4라고 하자.
- G3은 정점의 개수가 네 개인 무방향 그래프이므로 완전 그래프기 되기 위해서 4(4-1)/2 = 6개의 간선이 필요.
- G4은 정점의 개수가 네 개인 방향 그래프이므로 완전 그래프가 되기 위해서 4(4-1) = 12개의 간선이 필요.

### 부분 그래프

- 기존의 그래프에서 일부 정점이나 간선을 제외하여 만든 그래프
- 가중치 그래프 혹은 네트워크라고 함.

![부분 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbYD4Sk%2FbtrlDwrxckE%2FEtpHwIfhwQYs2ItmNw0Z2K%2Fimg.png)

### 가중 그래프

- 정점을 연결하는 가선에 가중치를 할당한 그래프

![가중 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbTvGm8%2FbtrlDXoSOzY%2F73zB0lRx1XDJ3k8Aaowxk0%2Fimg.png)

### 유향 비순환 그래프(DAG, Directed Acyclic Graph)

- 방향 그래프에서 사이클이 없는 그래프

![유향 비순환 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FtRfOT%2Fbtrl6KawxiR%2FwnINzrdCeKHBZrGE2WQW3k%2Fimg.png)

### 연결 그래프

- 떨어져 있는 정점이 없는 그래프

![연결 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdE0Mas%2Fbtrl4MtxtMs%2FJYqV97wRGsYMJpbS98qDwK%2Fimg.png)

### 단절 그래프

- 연결되지 않은 정점이 있는 그래프

![단절 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FISdEs%2Fbtrl6LAvEs8%2FNnYsi80Ke69yT0lgKTW6qk%2Fimg.png)

## 그래프 용어

그래프에서 두 정점 V1과 Vj가 연결되어 간선 (Vi, Vj)가 있을 때
- 두 정점 Vi, Vj를 인접(Adjacent)해 있다고 함.
- 간선 (Vi, Vj)은 정점 Vi와 Vj에 부속(Incident)되어 있다고 함.

---

- **정점**: 위치(연결할 객체), node라고도 불림.
- **간선**: 위치간의 관계(객체를 연결하는 선), node를 연결하는 선
- **인접 정점**: 간선에 의해 직접 연결된 정점.
- **정점의 차수**: 무방향 그래프에서 하나의 정점에 인접한 정점의 수
- **진입 차수**: 방향 그래프에서 외부에서 오는 간선(정점을 머리로 하는 간선)의 수, 내차수 라고도 불림.
- **진출 차수**: 방향 그래프에서 외부로 향하는 간선(정점을 꼬리로 하는 간선)의 수, 외차수 라고도 불림.
- **경로**: 그래프에서 간선을 따라갈 수 있는 길을 순서대로 나열한 것.
- **경로 길이**: 경로를 구성하는데 사용된 간선의 수
  - ex) G2 그래프에서 A -> C까지의 경로는 A-B-C이고, 간선은 <A,B>, <B,C> 이므로 경로의 길이는 2 
- **단순 경로**: 경로 중에서 반복되는 정점이 없는 경우(모두 다른 정점으로 구성된 경로)
- **사이클**: 단순 경로 중 시작 정점과 종료 정점이 동일한 경로

## 그래프의 특징
- 그래프는 네트워크 모델이다.
- 2개 이상의 경로가 가능
  - 노드들 사이에 무방향/방향에서 양방향 경로를 가질 수 있음.
- self-loop 뿐 아니라 loop/circuit 모두 가능
- 루트 노드라는 개념이 없음.
- 부모-자식 관계라는 개념이 없음.
- DFS, BFS로 순회 함.
- 그래프는 순환(Cyclic) 혹은 비순환(Acyclic)임.
  - 순환 그래프: 사이클이 있는 그래프
  - 비순환 그래프: 사이클이 없는 그래프

## 그래프 구현 방법

### 인접행렬 

![인접행렬](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F7RFhy%2FbtqKkOhoYiE%2FSE3IQP2q0g3xd34EQZkjM1%2Fimg.png)
- 인접행렬은 그래프의 노드를 2차원 배열로 만든 것.
- 일반적으로는 간선이 존재하는 두 정점의 칸은 1, 없는 칸은 0으로 채워줌. 단, 가중치가 존재한다면 가중치를 넣어 줌.

**인접행렬의 장점**
- 2차원 배열안에 모든 그래프의 정보(정점, 간선 정보)가 담겨있으므로 배열의 위치를 확인하면 두 점에 대한 연결 정보를 조회할 수 있음.
  - 시간 복잡도가 O(1)
- 구현이 비교적 간단함.

**인접행렬의 단점**
- 무조건 '정점의 개수 * 정점의 개수' 크기의 2차원 배열이 필요하므로 메모리 공간이 낭비됨.
- 모든 정점에 대한 간선 정보를 입력해야하므로 O(n^2)의 시간복잡도가 발생

### 인접리스트

![인접리스트](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FNlh1G%2FbtqKicb2Wub%2FsHWVSS6bn2FZdijEJVR2r1%2Fimg.png)
- 입접리스트는 그래프의 노드들을 리스트로 표현한 것.
- 정점의 리스트 배열을 만들어 관계를 설정.

**인접리스트의 장점**
- 간선의 개수가 n개인 정점의 연결 정보를 탐색할 때 시간복잡도가 O(n).
- 필요한 만큼의 공간만 활용하므로 공간의 낭비가 적음.

**인접리스트의 단점**
- 특정 두 점이 연결되었는지 확인하려면 인접행렬에 비해서 오래걸림.
- 구현이 비교적 어려움.

## 그래프의 탐색
- 한 정점에서 시작하여 그래프에 있는 모든 정점을 한 번씩 방문하는 것을 "그래프 순회" 혹은 "그래프 탐색"이라 함.
그래프의 탐색 방법으로는 크게 깊이 우선 탐색(Depth-First Search)와 너비 우선 탐색(Breadth-First Search)가 있음.

### 깊이 우선 탐색
- 시작 정점의 한 방향으로 갈 수 있는 경로가 있는 곳 까지 깊이 탐색하며 더 이상 갈 곳이 없으면, 가장 만지막에 만났던 갈림길 간선이 있는 정점으로 되돌아와 다른 방향의 간선으로 탐색을 계속하는 방식.
- 일반적으로 후입선출 구조의 스택을 사용.
- 넓게 탐색하기 전 깊게 탐색

### 너비 우선 탐색
- 시작 정점에 인접한 정점을 모두 차레로 방문하고 나서 방문했던 정점을 시작ㅎ으로 다시 인접한 정점을 차례로 방문하는 방식.
- 가까운 정점을 먼저 방문하고 멀리 있는 정점을 나중에 방문하는 순회 방법
- 일반적으로 탐색 과정에서 정점 순서를 관리하기 위해 선입선출의 구조를 갖는 큐를 사용.
- 깊게 탐색하기 전 넓게 탐색
- 최단 경로 혹은 임의의 경로르 찾을 때 일반적으로 너비 우선 탐색을 사용함.


### 출처
- C로 배우는 쉬운 자료구조(한빛아카데미)
- [[자료구조] 그래프(Graph)의 개념 설명](https://leejinseop.tistory.com/43)
- [[자료구조] 그래프(Graph)란](https://gmlwjd9405.github.io/2018/08/13/data-structure-graph.html)
- [[Algorithm] 자료구조 그래프(Graph)란 무엇인가?](https://coding-factory.tistory.com/610)