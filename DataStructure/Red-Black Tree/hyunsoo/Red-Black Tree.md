# Red-Black Tree

---

## Red-Black Tree

![레드-블랙 트리](https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Red-black_tree_example.svg/1000px-Red-black_tree_example.svg.png) 

Red-Black Tree(이하 RB Tree)는 
- **자가 균형 이진 탐색 트리** 임.
- 대표적으로는 연관 배열 등을 구현하는 데 쓰임.
- RB Tree의 읽기 전용 동작(탐색 등)은 이진 탐색 트리의 읽기 전용 동작을 변경하지 않고 사용 가능
  - RB Tree가 이진 탐색 트리의 특수한 한 형태이기 때문.
  - 단, 삽입, 삭제 시에는 색변형 혹은 트리 회전을 통하여 RB Tree의 특성을 맞춰야함.
- 트리에 n개의 원소가 있을 때 O(log n)의 시간복잡도로 삽입, 삭제, 검색을 할 수 있음.

## Red-Black Tree의 조건(성질)
RB Tree는 다음과 같은 조건들을 만족함. - 즉, RB Tree의 성질
1. 모든 노드는 레드 혹은 블랙
2. 루트 노드는 블랙
3. 모든 리프 노드(NIL)들은 블랙.
    - NIL: null leaf, 자료를 갖지 않고 트리의 끝을 나타내는 노드
4. 레드 노드의 자식은 블랙.
    - == No Double Red(레드 노드가 연속으로 나올 수 없다.)
    - 블랙 노드만이 레드 노드의 부모 노드가 될 수 있음.
5. 모든 리프 노드에서 Black Depth는 같음.
    - == 리프 노드에서 루트 노드까지 가는 경로에서 만나는 블랙 노드의 개수가 같음.

## Red-Black Tree의 특성

- 두 자녀가 같은 색상의 노드라면 자녀들과 부모의 색상을 바꿔줘도 5번 속성이 유지됨.
- **루트 노드부터 가장 먼 리프 노드 경로까지의 거리가, 가장 가까운 리프 노드 경로까지의 거리의 두 배 보다 항상 작음.**
  - 즉, RB Tree는 대략적으로 균형이 잡혀있음.
    - 이진 탐색 트리는 최악의 경우 트리의 높이(혹은 깊이)에 따라 시간복잡도가 결정되는데, RB Tree는 대략적으로 균형이 잡혀있어 효율적임.
- 4번 속성에 따라, 최단 경로는 모두 블랙 노드로만 구성되어 있다고 했을 때, 최장 경로는 블랙 노드와 레드 노드가 번갈아 나오는 것이 될 것임.  
- 5번 속성에 따라, 모든 경로에서 블랙 노드의 수가 동일하므로 존재하는 모든 경로에 대해 최장 경로의 거리는 최단 경로의 두 배 이상이 될 수 없음.

## Red-Black Tree의 삽입 연산
RB Tree에서의 삽입은 단순 이진 탐색 트리에서 하는 것과 같이 노드를 삽입하고 노드를 붉은 색으로 지정하는 것으로 시작.
그 후 주위 노드의 색에 따라 다음 동작이 정해짐.

이후의 설명에서는 아래의 규칙을 따름.
- 삽입하는 노드: N
- N의 부모 노드: P
- P의 부모: G
- N의 삼촌 노드: U
  - **삼촌 노드(uncle node)란?** 부모의 형제 노드를 뜻함.

### 삽입 연산의 Case

1. N이라는 새로운 노드가 트리의 Root에 위치할 경우
   - 1번 속성을 지키기 위하여, 해당 노드를 레드 -> 블랙으로 바꾸어 주면 됨.

2. 새로운 노드의 부모 노드 P가 블랙일 경우
   - 모든 속성을 다 만족하는 RB Tree의 형태이므로 문제가 없음. 

3. 부모 노드 P와 삼촌 노드 U가 모두 레드일 경우

![Insert case 3](https://upload.wikimedia.org/wikipedia/commons/c/c8/Red-black_tree_insert_case_3.png)

- 5번 속성을 지키기 위하여, P와 U를 모두 검은색으로 바꾸고, G를 붉은 색으로 교체
  - 즉, P,U와 G의 색상을 교체
- 해당 작업 이후 G가 2번 속성 혹은 4번 속성을 만족하지 않을 수 있음.
  - 이를 만족시킬 때까지 G에 대하여 첫 번째 경우부터 세 번째 경우까지 재귀적으로 적용
    - 회전하기 전에 적용해야함

4. 부모 노드 P는 레드이고 삼촌 노드 U는 블랙일 때, N이 P의 왼쪽 자식 노드이며 P는 G의 왼쪽 자식일 때 

![Insert case 4](https://upload.wikimedia.org/wikipedia/commons/6/66/Red-black_tree_insert_case_5.png)

- G을 기준으로 오른쪽 회전을 진행
  - 회전의 결과로 P가 왼쪽 자식으로 N을 오른쪽 자식으로 G를 가지게 됨.
    - P는 레드, G는 블랙이므로 4번째 속성을 만족시키기 위하여 P를 블랙, G를 레드로 바꿔주어야함.

> 삽입된 red 노드가 부모의 왼쪽* 자녀 & 부모도 red고 할이버지의 왼쪽* 자녀 & 삼촌은 black일 경우  
> 부모와 할아버지의 색을 바꾼 후 할아버지를 기준으로 오른쪽* 으로 회전
- 오른쪽 왼쪽을 바꿔도 성립

5. 부모 노드 P는 레드이고 삼촌 노드 U는 블랙일 때, N이 P의 오른쪽 자식 노드이고 P는 G의 왼쪽 자식 일 때

![Insert case 5](https://upload.wikimedia.org/wikipedia/commons/5/56/Red-black_tree_insert_case_4.png)

- N과 P의 역할을 변경하기 위해 P를 기준으로 왼쪽 회전을 진행.
  - case 4의 경우와 동일한 상태이므로 해당 문제를 해결하기 위해 동일한 조치를 수행.

> 삽입된 red 노드가 부모의 오른쪽* 자녀 & 부모도 red고 할아버지의 왼쪽* 자녀 & 삼촌은 black일 경우  
> 부모를 기준으로 왼쪽* 으로 회전한 뒤 case 4의 방식으로 해결

## Red-Black Tree의 삭제 연산
삭제 방식 자체는 일반적인 BST와 동일.
- 일반적인 BST의 삭제연산
  - 삭제할 노드가 단말 노드일 경우, 
    - 단순 삭제
  - 삭제할 노드가 하나의 자식 서브트리만 가지고 있는 경우, 
    - 해당 노드 삭제 후 삭제한 노드의 자식 서브트리를 삭제한 노드의 위치로 이동
  - 삭제할 노드가 두 개의 서브트리를 가지고 있는 경우,
    - 해당 노드 삭제 후 삭제한 노드의 오른쪽 서브트리의 최솟값 혹은 왼쪽 서브트리의 최댓값을 삭제한 노드의 위치로 이동.
    
삭제 후 RB Tree의 속성 위반 여부를 확인한 후 조정
- RB Tree에서 노드를 삭제할 때 어떤 색이 삭제되는지(삭제되는 색)가 속성 위반 여부를 확인할 때 매우 중요
  - **삭제되는 색이 red라면, 어떠한 속성도 위반하지 않음.**
  - **삭제되는 색이 black이라면, 2, 4, 5 속성을 위반할 수 있음.**

**삭제되는 색을 확인하는 법**

- 삭제하려는 노드의 자녀가 없거나 하나라면 삭제되는 색 == 삭제되는 노드의 색  
- 삭제하려는 노드의 자녀가 둘이라면 삭제되는 색 == 삭제되는 노드의 successor의 색
  - 일반적인 배열에서 successor는 바로 다음 값을 뜻하지만, 이진 트리에서는 바로 다음 크기 값을 뜻함
  - 위에서 뜻하는 자녀는 NIL이 아닌 유효한 노드를 뜻함.

### 삭제 연산의 Case

1. 2번 속성을 위반하였을 경우
- 루트 노드를 black으로 바꾸면 해결

2. 5번 속성을 위반하였을 경우
- 삭제된 색의 위치를 대체한 노드에 extra black을 부여함.
  - 경로에서 black 수를 카운트할 때 extra black은 하나의 black으로 카운트
  - extra black을 부여받은 노드는 doubly black이나 red-and-black이 됨.

![base](https://user-images.githubusercontent.com/90144041/223387037-fac8a05e-ffd9-4fdc-91e3-3b54cd2a0970.png)
위의 RB Tree를 기준으로 설명할 것임.

![after remove 10](https://user-images.githubusercontent.com/90144041/223387288-fe7c1d1e-0b19-465f-a742-fe9fffead7dd.png)
black인 10이 삭제된다면, 5번 속성을 위반하게 됨.

![resolve after remove 10](https://user-images.githubusercontent.com/90144041/223387921-1596963b-996d-45c7-bc77-5b5e8cc14f53.png)
삭제한 노드에 extra black을 부여하여 해결

![after remove 80](https://user-images.githubusercontent.com/90144041/223390305-7a2e8d5d-f7a0-4e94-b99e-6c5a45bb721b.png)
만약 80을 삭제하게 된다면 RB Tree는 위와 같은 형태로 조정됨.

![after remove 30](https://user-images.githubusercontent.com/90144041/223389320-5fd49772-8791-4692-85b2-acedd209b24d.png)
만약 30을 삭제하게 된다면 RB Tree는 위와 같은 형태로 조정됨.
red 노드에 extra black을 부여하여 해결
- extra black이 부여된 red 노드는 red-and-black이라고 함.

### red-and-black & doubly black 해결하기
red-and-black 해결
- red-and-black 노드를 black노드로 변경만 시켜주면 해결

doubly black 해결
- 형제의 색과 그 형제의 자녀들의 색을 기준으로 4가지 케이스가 존재

1. doubly black의 오른쪽 형제가 black & 그 형제의 오른쪽 자녀가 red일 경우 - case 1
> 그 red를 doubly black의 위로 옮긴 후 해당 red에 extra black을 전달  
> extra black을 받은 red는 red-and-black이 되고, 이를 해결하기 위해 black으로 변환

![doubly black case 1-1](https://user-images.githubusercontent.com/90144041/223393596-d1df10a9-d744-4fc5-a577-8ea7b21e31a2.png)
red를 왼쪽으로 보내기 위해서는 D의 위치가 red가 되어야 하고, 이를 위해 D의 black을 C와 E로 보내고 D를 red로 변환.

![doubly black case 1-2](https://user-images.githubusercontent.com/90144041/223395432-f4ff7ec3-0e9e-4a06-947f-caaf0228f458.png)
- 여전히 5번 속성을 만족하고 있음.

red가 (회전을 통하여)왼쪽으로 넘어갈 수 있도록 B와 D의 색을 바꿔줌.

![doubly black case 1-3](https://user-images.githubusercontent.com/90144041/223395910-1c68fa44-113f-48e1-8d65-ccfc70339259.png)
B를 기준으로 왼쪽으로 회전

![doubly black case 1-4](https://user-images.githubusercontent.com/90144041/223396174-1f122fc8-ecce-4664-bbb9-2fffcab556e1.png)
두 개의 extra black을 모두 부모 red로 보낸 후 red-and-black으로 변환된 부모를 black으로 변환

![doubly black case 1 result](https://user-images.githubusercontent.com/90144041/223397292-8a434c52-9f8d-44cf-8e30-5d0ee8a3ccff.png)
- doubly black이 해결된 모습

> doubly black의 오른쪽* 형제가 black & 그 형제의 오른쪽* 자녀가 red일 때
- 오른쪽* 형제는 부모의 색으로, 오른쪽* 형제의 오른쪽* 자녀는 black으로, 부모는 black으로 바꾼 후에 부모를 기준으로 왼쪽* 으로 회전하면 해결
  - 오른쪽 왼쪽을 바꿔도 성립

2. doubly black의 오른쪽 형제가 black & 그 형제의 왼쪽 자녀가 red & 그 형제의 오른쪽 자녀는 black일 경우 - case 2
> doubly black의 형제의 오른쪽 자녀가 red가 되게 만든 후 case 1을 적용하여 해결

![doubly black case 2-1](https://user-images.githubusercontent.com/90144041/223398657-dcd33d6a-f0e1-4f7b-a021-a64c33fc714b.png)
E위치에 red가 오도록 만들기 위하여 C와 D의 색을 바꾼 후 D를 기준으로 오른쪽으로 회전

![doubly black case 2-2](https://user-images.githubusercontent.com/90144041/223399100-ff6b876d-4ca8-4e36-8760-18d6fa34c5d3.png)
case 1의 방식을 적용하여 해결

> doubly black의 오른쪽* 형제가 black & 그 형제의 왼쪽* 자녀가 red & 그 형제의 오른쪽* 자녀는 black일 때
- doubly black의 형제의 오른쪽* 자녀를 red가 되게 만든 후(case 1) case 1의 과정을 진행하여 해결
    - 오른쪽 왼쪽을 바꿔도 성립

3. doubly black의 형제가 black & 그 형제의 두 자녀 모두 black일 경우 - case 3
> doubly black과 그 형제의 black을 모은 후 부모에게 전달해서 부모가 extra black을 해결하도록 위임

![doubly black case 3-1](https://user-images.githubusercontent.com/90144041/223400080-2f528e0d-e086-4f51-947c-40c0743d9c5c.png)
A의 extra black과 D의 black을 부모인 B에게 전달하여 부모가 extra black을 해결하도록 위임

![doubly black case 3-2](https://user-images.githubusercontent.com/90144041/223400436-73acb4f0-e8b1-46f1-89c5-40d2502826a0.png)
B가 extra black을 받은 후 
- red-and-black이 되었다면 
  - black으로 변경하여 해결
- doubly black이 되었다면
  - B가 루트 노드일 경우 그냥 black으로 변경하여 해결
  - case 1, 2, 3, 4의 경우 중 하나로 해결

4. doubly black의 형제가 red일 경우 - case 4
> doubly black의 형제를 black으로 만든 후 case 1, 2, 3 중 하나로 해결

![doubly black case 4-1](https://user-images.githubusercontent.com/90144041/223401431-f81624af-3ac6-430f-a22c-2f7508fe0ea4.png)
B를 기준으로 왼쪽으로 회전하면 doubly black A의 형제는 C가 됨(형제가 black이 됨)
- 단, 회전 후에도 5번 속성을 만족하기 위하여 B와 D의 색상을 바꾼 후 회전

![doubly black case 4-2](https://user-images.githubusercontent.com/90144041/223402157-7044fe57-d314-474a-b2d5-9613062ea9ea.png)
doubly black의 형제가 black이 됐으므로 case 1, 2, 3 중 하나로 해결

> doubly black의 오른쪽* 형제가 red일 때
- 부모와 형제의 색을 바꾸고 부모를 기준으로 왼쪽* 으로 회전한 뒤 doubly black을 기준으로 case 1, 2, 3 중 하나로 해결

### 출처

- [레드-블랙 트리](https://ko.wikipedia.org/wiki/%EB%A0%88%EB%93%9C-%EB%B8%94%EB%9E%99_%ED%8A%B8%EB%A6%AC)
- [Red-Black Tree 속성/검색/삽입/삭제](https://lemonlemon.tistory.com/135)
- [(1부) 레드블랙트리(red-black tree)의 기본 개념과 특징을 살펴보고, 삽입 때 레드블랙트리가 어떻게 동작하는지를 아주 자세히 설명합니다~ 헷갈리시는 분들 커몬요](https://www.youtube.com/watch?v=2MdsebfJOyM&ab_channel=%EC%89%AC%EC%9A%B4%EC%BD%94%EB%93%9C)
- [(2부) 레드블랙트리(red-black tree)의 삭제는 어떻게 동작할까요? 시간 복잡도는 어떻게 될까요? AVL 트리와 차이는 무엇일까요? 이 영상으로 후련하게 해결하세요 :)](https://www.youtube.com/watch?v=6drLl777k-E&ab_channel=%EC%89%AC%EC%9A%B4%EC%BD%94%EB%93%9C)