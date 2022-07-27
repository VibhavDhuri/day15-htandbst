
public class BinarySearchTreeMain {

	public static void main(String[] args) {
		BinarySearchTree binaryTree = new BinarySearchTree();
		Node node = new Node(56);
		binaryTree.root = node;
		binaryTree.insertElement(node, 30);
		binaryTree.insertElement(node, 70);
		binaryTree.insertElement(node, 3);
		binaryTree.insertElement(node, 11);
		binaryTree.insertElement(node, 16);
		binaryTree.insertElement(node, 22);
		binaryTree.insertElement(node, 40);
		binaryTree.insertElement(node, 60);
		binaryTree.insertElement(node, 95);
		binaryTree.insertElement(node, 65);
		binaryTree.insertElement(node, 63);
		binaryTree.insertElement(node, 67);

		System.out.println("Display Node");
		binaryTree.traverseTree(node);
		System.out.println();
		BinarySearchTree.search(binaryTree.root, 63, null);
	}

}
