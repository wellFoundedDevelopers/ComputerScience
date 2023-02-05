interface SimpleList<E> {
    fun contains(element: E): Boolean
    fun add(element: E)
    fun add(index: Int, element: E)
    fun addFirst(element: E)
    fun removeAt(index: Int)
    fun remove()
    fun removeFirst()
    fun get(index: Int): E
    fun isEmpty(): Boolean
    fun indexOf(element: E): Int
    fun clear()
}