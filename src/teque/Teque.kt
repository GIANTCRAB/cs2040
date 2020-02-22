package teque

class Teque() {
    private val frontMap: MutableMap<Int, Int> = HashMap()
    // First available index for front of frontMap
    private var frontIndexMin = 0
    // First available index for back of frontMap
    private var frontIndexMax = 1
    private val backMap: MutableMap<Int, Int> = HashMap()
    // First available index for front of backMap
    private var backIndexMin = 0
    // First available index for back of backMap
    private var backIndexMax = 1

    fun addFront(element: Int) {
        frontMapAddFirst(element)
        balance()
    }

    fun addMiddle(element: Int) {
        frontMapPush(element)
        balance()
    }

    fun addBack(element: Int) {
        backMapPush(element)
        balance()
    }

    fun getElement(positionIndex: Int): String {
        return if (positionIndex < frontMap.size) {
            // can find from the front
            // calculate relative position
            val relativePosition = (frontIndexMin + 1) + positionIndex
            frontMap[relativePosition].toString()
        } else {
            // can only find in the back
            // calculate relative position
            val relativePosition = (backIndexMin + 1) + (positionIndex - frontMap.size)
            backMap[relativePosition].toString()
        }
    }

    // Add element to the back
    private fun frontMapPush(element: Int) {
        frontMap[frontIndexMax] = element
        frontIndexMax++
    }

    // Add element to the front
    private fun frontMapAddFirst(element: Int) {
        frontMap[frontIndexMin] = element
        frontIndexMin--
    }

    // Remove last element
    private fun frontMapPop(): Int {
        val lastElement = frontMap[frontIndexMax - 1]
        frontMap.remove(frontIndexMax - 1)
        frontIndexMax--

        return lastElement!!
    }

    private fun backMapPush(element: Int) {
        backMap[backIndexMax] = element
        backIndexMax++
    }

    // Add element to the front of backMap
    private fun backMapAddFirst(element: Int) {
        backMap[backIndexMin] = element
        backIndexMin--
    }

    // Remove first element
    private fun backMapPop(): Int {
        val firstElement = backMap[backIndexMin + 1]
        backMap.remove(backIndexMin + 1)
        backIndexMin++

        return firstElement!!;
    }

    private fun balance() {
        if (frontMap.size > backMap.size) {
            // need to pop frontMap
            val elementToShift = frontMapPop()
            backMapAddFirst(elementToShift)
        }

        if (frontMap.size + 1 < backMap.size) {
            // need to pop backMap
            val elementToShift = backMapPop()
            frontMapPush(elementToShift)
        }
    }
}