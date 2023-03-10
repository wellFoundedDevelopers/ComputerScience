class RedBlackTree {

    class Node(
        var value: Int,
        var color: Boolean = RED,
        var dup: Int = 0,
        var _parent: Node? = nil,
        var _left: Node? = nil,
        var _right: Node? = nil
    ) {

        var parent : Node
            get() = _parent!!
            set(value) { _parent = value }
        var left : Node
            get() = _left!!
            set(value) { _left = value }
        var right : Node
            get() = _right!!
            set(value) { _right = value }

        companion object {
            val nil = Node(0, BLACK, 0, null, null, null)
        }
    }


    var root = Node.nil

    fun insert(value: Int) {
        var cur = root
        var newNode = Node(value)

        if (isNil(root)) {
            root = newNode
            newNode.color = BLACK
            return
        }

        while(isNotNil(cur)) {
            if (cur.value == value) {
                cur.dup++
                return
            }

            newNode.parent = cur
            cur = if (cur.value < value) cur.right else cur.left
        }

        if (newNode.parent.value < value) {
            newNode.parent.right = newNode
        }
        else {
            newNode.parent.left = newNode
        }

        if (newNode.parent.color == RED) {
            fixInsertion(newNode)
        }
    }

    fun delete(value: Int) {
        val target = search(value)
        if (isNil(target)) return

        var successor = target.left
        if (isNotNil(successor)) {
            while(isNotNil(successor.right)) {
                successor = successor.right
            }
        }
        else if (isNotNil(target.right)){
            successor = target.right
            while(isNotNil(successor.left)) {
                successor = successor.left
            }
        }
        else successor = target

        target.value = successor.value

        val x = remove(successor)
        if (successor.color == RED) return
        if (x.color == RED && x.parent.color == RED) {
            x.color = BLACK
        }

        fixDeletion(x, successor.parent)
    }

    fun search(value: Int): Node {
        var cur = root

        while(isNotNil(cur)) {
            if (cur.value == value) return cur
            if (cur.value < value) cur = cur.right
            else cur = cur.left
        }
        return cur
    }

    fun traverseBfs(v: Node): List<String> {
        if (isNil(v)) return emptyList()

        val list = mutableListOf<String>()
        val q = mutableListOf(v)

        while(q.isNotEmpty()) {
            val x = q.removeFirst()
            if (isNil(x)) {
                list.add("0B")
            }
            else {
                list.add("${x.value}${if (x.color) "B" else "R"}")
                q.add(x.left)
                q.add(x.right)
            }
        }

        return list
    }

    private fun rotateToLeft(x: Node) {
        if (isNil(x.right)) return

        val y = x.right

        x.right = y.left
        if (isNotNil(y.left)) {
            y.left.parent = x
        }
        y.left = x

        y.parent = x.parent
        if (isNotNil(x.parent)) {
            if (x.parent.left == x) x.parent.left = y
            else x.parent.right = y
        }
        else {
            root = y
        }
        x.parent = y
    }

    private fun rotateToRight(x: Node) {
        if (isNil(x.left)) return

        val y = x.left

        x.left = y.right
        if (isNotNil(y.right)) {
            y.right.parent = x
        }
        y.right = x

        y.parent = x.parent
        if (isNotNil(x.parent)) {
            if (x.parent.left == x) x.parent.left = y
            else x.parent.right = y
        }
        else {
            root = y
        }
        x.parent = y
    }

    private fun exchangeToLeft(x: Node) {
        val tmp = x.color
        x.color = x.left.color
        x.left.color = tmp
    }

    private fun exchangeToRight(x: Node) {
        val tmp = x.color
        x.color = x.right.color
        x.right.color = tmp
    }

    private fun isNil(v: Node) : Boolean {
        return v == Node.nil
    }

    private fun isNotNil(v: Node) : Boolean {
        return v != Node.nil
    }

    /**
     * @param v: successor로 지워질 수 있는 노드임
     * @return: 삭제된 노드의 자식
     */
    private fun remove(v: Node): Node {
        val child = if (isNotNil(v.left)) v.left else v.right
        if (isNotNil(child)) {
            child.parent = v.parent
        }

        if (isNotNil(v.parent)) {
            if (v.parent.left == v) v.parent.left = child
            else v.parent.right = child
        }
        else {
            root = child
        }

        return child
    }

    /**
     * @param _z: color == RED && parent.color == RED
     */
    private fun fixInsertion(_z: Node) {
        var z = _z
        var parent = z.parent
        if (parent == root) {
            parent.color = BLACK
            return
        }

        var grandparent = parent.parent
        var uncle = if (parent == grandparent.left) grandparent.right else grandparent.left

        if (uncle.color == RED) {
            grandparent.color = RED
            parent.color = BLACK
            uncle.color = BLACK

            if (grandparent == root) {
                grandparent.color = BLACK
                return
            }
            if (grandparent.parent.color == RED) {
                fixInsertion(grandparent)
                return
            }
        }
        else {  // uncle.color == BLACK
            if (parent == grandparent.left) {
                if (z == parent.right) {
                    rotateToLeft(z.parent)
                    z = parent
                }
                z.parent.color = BLACK
                z.parent.parent.color = RED
                rotateToRight(z.parent.parent)
            }
            else {  // parent == grandparent.right
                if (z == parent.left) {
                    rotateToRight(z.parent)
                    z = parent
                }
                z.parent.color = BLACK
                z.parent.parent.color = RED
                rotateToLeft(z.parent.parent)
            }
        }
        return
    }

    /**
     * @param _x: extra-black을 부여받은 노드 (NIL일 수 있음)
     * @param _parent: _x가 NIL일 수 있기 때문에 필요
     * 결국 _x가 NIL인 경우 (확장하면 w가 NIL인 경우까지) 처리하지 못함..
     */
    private fun fixDeletion(_x: Node, _parent: Node) {
        if (_x.color == RED || _x == root) {
            _x.color = BLACK
            return
        }

        val x = _x
        val parent = _parent
        var w: Node
        val rotate: (Node) -> Unit
        val exchange: (Node) -> Unit
        if (x == parent.left) {
            w = parent.right
            rotate = { a -> rotateToLeft(a) }
            exchange = { a -> exchangeToLeft(a) }
        }
        else {
            w = parent.left
            rotate = { a -> rotateToRight(a) }
            exchange = { a -> exchangeToRight(a) }
        }

        // case 1, 5
        if (w.color == RED) {
            rotate(parent)
            exchange(w)
            w = getBrother(x)
        }

        // 이제 w는 BLACK
        // case 2, 6
        if (w.left.color == BLACK && w.right.color == BLACK) {
            w.color = RED
            if (parent.color == RED) {
                parent.color = BLACK
                return
            }
            return fixDeletion(parent, parent.parent)
        }
        else if ((x == parent.left && w.left.color == RED) ||
            (x == parent.right && w.right.color == RED)) {    // case 3, 7
            exchange(w)
            rotate(w)
            w = getBrother(x)
        }

        // case 4, 8
        w.color = parent.color
        rotate(parent)
        w.left.color = BLACK
        w.right.color = BLACK
    }

    private fun getBrother(v: Node) : Node {
        return if (v == v.parent.left) v.parent.right else v.parent.left
    }

    companion object {
        const val RED = false
        const val BLACK = true
    }
}

fun main() {
    val redblacktree = RedBlackTree()

    // insert test
    redblacktree.run {
        insert(5)
        check(traverseBfs(root).joinToString(" ") == "5B 0B 0B")

        insert(3)
        check(traverseBfs(root).joinToString(" ") == "5B 3R 0B 0B 0B")

        insert(4)
        check(traverseBfs(root).joinToString(" ") == "4B 3R 5R 0B 0B 0B 0B")

        insert(2)
        check(traverseBfs(root).joinToString(" ") == "4B 3B 5B 2R 0B 0B 0B 0B 0B")

        insert(1)
        check(traverseBfs(root).joinToString(" ") == "4B 2B 5B 1R 3R 0B 0B 0B 0B 0B 0B")

        insert(7)
        check(traverseBfs(root).joinToString(" ") == "4B 2B 5B 1R 3R 0B 7R 0B 0B 0B 0B 0B 0B")

        insert(8)
        check(traverseBfs(root).joinToString(" ") == "4B 2B 7B 1R 3R 5R 8R 0B 0B 0B 0B 0B 0B 0B 0B")

        insert(9)
        check(traverseBfs(root).joinToString(" ") == "4B 2B 7R 1R 3R 5B 8B 0B 0B 0B 0B 0B 0B 0B 9R 0B 0B")

        insert(6)
        check(traverseBfs(root).joinToString(" ") == "4B 2B 7R 1R 3R 5B 8B 0B 0B 0B 0B 0B 6R 0B 9R 0B 0B 0B 0B")
    }

    // delete test
    redblacktree.run {
        delete(4)
        check(traverseBfs(root).joinToString(" ") == "3B 2B 7R 1R 0B 5B 8B 0B 0B 0B 6R 0B 9R 0B 0B 0B 0B")

        delete(3)
        check(traverseBfs(root).joinToString(" ") == "2B 1B 7R 0B 0B 5B 8B 0B 6R 0B 9R 0B 0B 0B 0B")

        delete(1)


    }
}