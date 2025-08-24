package datastructures;

import model.Vehicle;

public class MyBinarySearchTree {
    private class Node {
        Vehicle vehicle;
        Node left, right;

        Node(Vehicle vehicle) {
            this.vehicle = vehicle;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public MyBinarySearchTree() {
        root = null;
    }

    public void insert(Vehicle vehicle) {
        root = insertRec(root, vehicle);
    }

    private Node insertRec(Node root, Vehicle vehicle) {
        if (root == null) {
            return new Node(vehicle);
        }
        if (vehicle.getRegNum().compareTo(root.vehicle.getRegNum()) < 0) {
            root.left = insertRec(root.left, vehicle);
        } else if (vehicle.getRegNum().compareTo(root.vehicle.getRegNum()) > 0) {
            root.right = insertRec(root.right, vehicle);
        }
        return root;
    }

    public Vehicle search(String regNum) {
        return searchRec(root, regNum);
    }

    private Vehicle searchRec(Node root, String regNum) {
        if (root == null || root.vehicle.getRegNum().equals(regNum)) {
            return root == null ? null : root.vehicle;
        }
        if (regNum.compareTo(root.vehicle.getRegNum()) < 0) {
            return searchRec(root.left, regNum);
        }
        return searchRec(root.right, regNum);
    }

    public Vehicle remove(String regNum) {
        Node parent = null;
        Node current = root;
        while (current != null && !current.vehicle.getRegNum().equals(regNum)) {
            parent = current;
            if (regNum.compareTo(current.vehicle.getRegNum()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            return null;
        }
        Vehicle removed = current.vehicle;
        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parent.left == current) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null;
            }
        } else if (current.left == null || current.right == null) {
            Node child = current.left != null ? current.left : current.right;
            if (current != root) {
                if (parent.left == current) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else {
                root = child;
            }
        } else {
            Node successor = findMin(current.right);
            Vehicle successorVehicle = successor.vehicle;
            remove(successorVehicle.getRegNum());
            current.vehicle = successorVehicle;
        }
        return removed;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void inOrderTraversal(MyMinHeap heap, MyArray array) {
        inOrderTraversalRec(root, heap, array);
    }

    private void inOrderTraversalRec(Node root, MyMinHeap heap, MyArray array) {
        if (root != null) {
            inOrderTraversalRec(root.left, heap, array);
            heap.insert(root.vehicle);
            array.add(root.vehicle);
            inOrderTraversalRec(root.right, heap, array);
        }
    }

    public void collectMaintenanceRecords(StringBuilder sb) {
        collectMaintenanceRecordsRec(root, sb);
    }

    private void collectMaintenanceRecordsRec(Node root, StringBuilder sb) {
        if (root != null) {
            collectMaintenanceRecordsRec(root.left, sb);
            sb.append(root.vehicle.getMaintenanceRecords());
            collectMaintenanceRecordsRec(root.right, sb);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderToString(root, sb);
        return sb.toString();
    }

    private void inOrderToString(Node root, StringBuilder sb) {
        if (root != null) {
            inOrderToString(root.left, sb);
            sb.append(root.vehicle.toString());
            inOrderToString(root.right, sb);
        }
    }
}