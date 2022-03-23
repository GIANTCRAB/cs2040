package kattis.minspantree

class EdgeSorterComparator : Comparator<Edge> {
    override fun compare(o1: Edge, o2: Edge): Int {
        val firstComparison = o1.firstNodeId.compareTo(o2.firstNodeId)
        if (firstComparison == 0) {
            return o1.secondNodeId.compareTo(o2.secondNodeId)
        }
        return firstComparison
    }
}