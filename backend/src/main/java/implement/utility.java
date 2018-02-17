package implement;

import implement.TrashNode;
import implement.User;

public Float getDistance(TrashNode trashNode, User userNode) {
    ArrayList<Float> trashCoordinate = trashNode.getCoordinate();
    ArrayList<Float> userCoordinate = userNode.getCoordinate();

    Float latDifference = Math.abs(trashCoordinate.get(0) - userCoordinate.get(0));
    Float longDifference = Math.abs(trashCoordinate.get(1) - userCoordinate.get(1));
    Float distance = Math.sqrt(Math.pow(latDifference, 2) + Math.pow(longDifference, 2));
    return distance;
}

