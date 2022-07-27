
public class BinarySearchTreeMain {

	public static void main(String[] args) {
		BinarySearchTree binaryTree = new BinarySearchTree();

		Node node = new Node(56); // head node
		binaryTree.root = node;
		binaryTree.insertElement(node, 30);
		binaryTree.insertElement(node, 70);

		System.out.println("Display Node");
		binaryTree.traverseTree(node);
	}

}
