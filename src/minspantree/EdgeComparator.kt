package minspantree

class EdgeComparator : Comparator<Edge> {
    override fun compare(o1: Edge?, o2: Edge?): Int {
        return o1!!.weight.compareTo(o2!!.weight)
    }
}