package minspantree

class EdgeSorterComparator : Comparator<Edge> {
    override fun compare(o1: Edge?, o2: Edge?): Int {
        return o1!!.firstNodeId.compareTo(o2!!.firstNodeId)
    }
}