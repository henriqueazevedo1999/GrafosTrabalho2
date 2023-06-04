//Henrique Azevedo

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public String printShortestPath() {
        if (this.getShortestPath().size() == 0)
            return null;

        StringBuilder retorno = new StringBuilder();

        for (Node node : this.getShortestPath()) {
            retorno.append(node.getName()).append(" -> ");
        }

        retorno.append(this.getName());

        return retorno.toString();
    }
}