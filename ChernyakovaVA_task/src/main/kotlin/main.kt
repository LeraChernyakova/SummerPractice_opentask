import orientedGraph.OrientedGraph
import orientedGraph.Paths
import java.util.Scanner

fun readingEdges(): Paths {
	val graphPaths: Paths = mutableMapOf()
	while (true) {
		val line = readln()
		if (line.isEmpty()) break
		val (startNode, finishNode, distance) = line.split(" ")
		if (!graphPaths.containsKey(startNode.single())) {
			graphPaths[startNode.single()] = mutableListOf(Pair(finishNode.single(), distance.toFloat()))
		} else {
			graphPaths[startNode.single()]?.add(Pair(finishNode.single(), distance.toFloat()))
		}
	}
	return graphPaths
}

fun solution() {
	val (startNode, finishNode) = readln().split(" ").map { it.single() }
	val graphPaths = readingEdges()
	val graph = OrientedGraph(startNode, finishNode, graphPaths)
	graph.aStarAnswer()
}

fun main() {
	solution()
}
