class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}

}

public class BinarySearchTree {

	public Node root;

	public BinarySearchTree() {

	}

	public void insertElement(Node node, int data) {
		if (data < node.data) {
			if (node.left != null) {
				insertElement(node.left, data);
			} else {
				node.left = new Node(data);
			}
		} else if (data > node.data) {
			if (node.right != null) {
				insertElement(node.right, data);
			} else {
				node.right = new Node(data);
			}

		}
	}

	public void traverseTree(Node node) {
		if (node != null) {
			traverseTree(node.left); // traversing all left node-inorder traversing
			System.out.println(node.data + "");
			traverseTree(node.right);
		}
	}

	public static void search(Node root, int data, Node parent) {
		// if the root is null
		if (root == null) {
			System.out.print("Key Not found");
			return;
		}

		// if the key is found
		if (root.data == data) {
			if (parent == null) {
				System.out.print("The node with key " + data + " is root node");
			}

			else if (data < parent.data) {
				System.out.println("The given element " + data + " is the left node of the Parent Node " + parent.data);
			} else {
				System.out
						.println("The given element " + data + " is the right node of the Parent Node " + parent.data);
			}

			return;
		}

		// if the given key is less than the root node, recur for the left subtree;
		// otherwise, recur for the right subtree

		if (data < root.data) {
			search(root.left, data, root);
		} else {
			search(root.right, data, root);
		}
	}
}