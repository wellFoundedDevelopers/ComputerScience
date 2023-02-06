import java.lang.Exception
import java.lang.IndexOutOfBoundsException

class MySinglyLinkedList<E> : SimpleList<E> {

    private data class MyNode<E>(var data: E, var next: MyNode<E>? = null)

    private var head: MyNode<E>? = null
    private var _size = 0
    val size
        get() = _size

    override fun contains(element: E): Boolean {
        return indexOf(element) != -1
    }

    override fun add(element: E) {
        val newNode = MyNode(element)
        if (isEmpty()) {
            head = newNode
        } else {
            val lastNode = findLastNode()
            lastNode.next = newNode
        }
        _size++
    }

    override fun add(index: Int, element: E) {

        if (size <= index) throw IndexOutOfBoundsException()

        if (index == 0) return addFirst(element)

        var cnt = 0
        val newNode = MyNode(element)
        lateinit var lastNode: MyNode<E>
        var curNode = requireNotNull(head)

        while (cnt < index) {
            lastNode = curNode
            curNode = requireNotNull(curNode.next)
            cnt++
        }

        lastNode.next = newNode
        newNode.next = curNode

    }

    override fun addFirst(element: E) {
        val newNode = MyNode(element)
        if (isEmpty()) {
            head = newNode
        } else {
            val previousNode = head
            head = newNode
            newNode.next = previousNode
        }
        _size++
    }

    override fun removeAt(index: Int) {
        if (size <= index) throw IndexOutOfBoundsException()
        _size--
        if (index == 0) return removeFirst()

        var cnt = 0
        lateinit var lastNode: MyNode<E>
        var curNode = requireNotNull(head)

        while (cnt < index) {
            lastNode = curNode
            curNode = requireNotNull(curNode.next)
            cnt++
        }
        lastNode.next = curNode.next

    }

    override fun remove() {
        removeAt(size - 1)
    }

    override fun removeFirst() {
        head = requireNotNull(head).next
    }

    override fun get(index: Int): E {

        if (size <= index) throw IndexOutOfBoundsException()

        var cnt = 0
        var curNode = requireNotNull(head)

        while (curNode.next != null) {
            curNode = requireNotNull(curNode.next)
            cnt++
            if (cnt == index) return curNode.data
        }

        throw IndexOutOfBoundsException()

    }

    override fun isEmpty(): Boolean {
        return head == null
    }

    override fun clear() {
        head = null
        _size = 0
    }

    override fun indexOf(element: E): Int {

        var cnt = 0
        var curNode = requireNotNull(head)
        while (curNode.next != null) {
            if (element == curNode.data) return cnt
            curNode = requireNotNull(curNode.next)
            cnt++
        }

        return -1
    }

    private fun findLastNode(): MyNode<E> {

        var lastNode = requireNotNull(head)

        while (lastNode.next != null) {
            lastNode = requireNotNull(lastNode.next)
        }

        return lastNode
    }

    override fun toString(): String {

        val stringBuilder = StringBuilder("[")
        var curNode = requireNotNull(head)

        while (curNode.next != null) {
            stringBuilder.append(" ${curNode.data},")
            curNode = requireNotNull(curNode.next)
        }

        stringBuilder.append(" ${curNode.data}")
        stringBuilder.append(" ]")

        return stringBuilder.toString()
    }


}

fun testLinkedList() {

    val linkedList = MySinglyLinkedList<Int>()

    println("isLinkedList Empty? ${linkedList.isEmpty()}")
    // isLinkedList Empty? true

    linkedList.add(1)
    linkedList.add(2)
    linkedList.add(3)
    linkedList.add(4)
    linkedList.add(5)
    linkedList.add(6)
    linkedList.add(0, 100)

    println("isLinkedList Empty? ${linkedList.isEmpty()}")
    // isLinkedList Empty? false

    println(linkedList)
    // [ 100, 1, 2, 3, 4, 5, 6 ]

    println(linkedList.indexOf(4)) // 4
    println(linkedList.indexOf(Int.MAX_VALUE)) // -1
    println(linkedList.get(1)) // 1

    try {
        println(linkedList.get(Int.MAX_VALUE))
    } catch (e: Exception) {
        println(e)
    } //  java.lang.IndexOutOfBoundsException

    linkedList.add(4, 999)
    println(linkedList)
    // [ 100, 1, 2, 3, 999, 4, 5, 6 ]

    linkedList.removeAt(0)
    println(linkedList)
    // [ 1, 2, 3, 999, 4, 5, 6 ]

    linkedList.removeAt(3)
    println(linkedList)
    // [ 1, 2, 3, 4, 5, 6 ]

    linkedList.remove()
    println(linkedList)
    // [ 1, 2, 3, 4, 6 ]

    linkedList.clear()

    println("isLinkedList Empty? ${linkedList.isEmpty()}")
    // isLinkedList Empty? true
}