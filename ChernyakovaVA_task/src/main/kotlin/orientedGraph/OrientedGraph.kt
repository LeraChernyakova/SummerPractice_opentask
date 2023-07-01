package orientedGraph

import java.util.*
import kotlin.math.abs

typealias Paths = MutableMap<Char, MutableList<Pair<Char, Float>>>

class OrientedGraph(beginNode: Char, endNode: Char, path: Paths) {
	private val startNode = beginNode
	private val finishNode = endNode
	private val graphPaths = path

	private fun heuristicFunction(nodeCheck: Char): Float {
		return abs(nodeCheck.code - finishNode.code).toFloat()
	}
	private fun aStar(): MutableMap<Char, Char> {
		val nodeCost = mutableMapOf(startNode to 0F)
		val queue = PriorityQueue(compareBy<Pair<Char, Float>> { it.second }.thenByDescending { it.first.code })
		queue.add(Pair(startNode, 0F))
		val processingPath = mutableMapOf<Char, Char>()
		while (queue.isNotEmpty()) {
			val current = queue.remove().first
			if (current == finishNode)
				break
			if (graphPaths.containsKey(current)) {
				for (node in graphPaths[current]!!) {
					val cost = nodeCost[current]!! + node.second
					if ((!nodeCost.containsKey(node.first)) || (cost < nodeCost[node.first]!!)) {
						nodeCost[node.first] = cost
						val priority = cost + heuristicFunction(node.first)
						queue.add(Pair(node.first, priority))
						processingPath[node.first] = current
					}
				}
			}
		}
		return processingPath
	}

	fun aStarAnswer() {
		val resultPath = aStar()
		val answer = mutableListOf(finishNode)
		while (answer.last() != startNode) {
			answer.add(resultPath.remove(answer.last())!!)
		}
		print(answer.reversed().joinToString(""))
	}
}
