public class AStar {

    public static void A_Star(Graph graph, String src, String dst, Complexity com) {
        Queue queue = new Queue(graph.Vertices.size());
        for (String i : graph.Vertices.keySet()) {
            graph.Vertices.get(i).setKnown(false);
            graph.Vertices.get(i).setDis(Double.MAX_VALUE);
            graph.Vertices.get(i).setHeuristic(0.0);
            graph.Vertices.get(i).setHeapIndex(-1);
            graph.Vertices.get(i).setPrev(null);
        }
        graph.Vertices.get(src).setDis(0.0);
        queue.add(graph.Vertices.get(src));
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            if (v.getCity().getName().equals(dst)) {
                return;
            }

            if (!v.isKnown()) {
                v.setKnown(true);
                for (int i = 0; i < v.getAdjacents().size(); i++) {
                    if (!v.getAdjacents().get(i).getVertex().isKnown()) {
                        v.setHeuristic(getHeuristic(v.getAdjacents().get(i), graph.Vertices.get(dst)));
                        if (v.getAdjacents().get(i).getDistance() + v.getDis() < v.getAdjacents().get(i).getVertex().getDis()) {
                            com.time++;
                            v.getAdjacents().get(i).getVertex().setPrev(v);
                            v.getAdjacents().get(i).getVertex().setDis(v.getAdjacents().get(i).getDistance() + v.getDis());
                            if (v.getAdjacents().get(i).getVertex().getHeapIndex() == -1)
                                queue.add(v.getAdjacents().get(i).getVertex());
                            else
                                queue.modifyPosition(v.getAdjacents().get(i).getVertex());

                            if (queue.size() > com.space)
                                com.space = queue.size();
                        }
                    }
                }
            }
        }
    }

    private static double getHeuristic(Edge edge, Vertex vertex) {
        return Math.sqrt(Math.pow((edge.getVertex().getCity().getY() - vertex.getCity().getY()),2) + Math.pow((edge.getVertex().getCity().getX() - vertex.getCity().getX()),2));
//        return Math.abs((edge.getVertex().getCity().getY() - vertex.getCity().getY()) / (edge.getVertex().getCity().getX() - vertex.getCity().getX())) * 4.694;
    }

}