import java.util.ArrayList;
import java.util.Objects;


	class HashNode<K, V> {
		K key;
		V value;
		final int hashCode;

		// Reference to next node
		HashNode<K, V> next;

		// Parameterized Constructor
		public HashNode(K key, V value, int hashCode) {
			this.key = key;
			this.value = value;
			this.hashCode = hashCode;
		}
	}

	// Class to represent entire hash table

	public class Map<K, V> {

		// bucketArray is used to store array of chains
		private ArrayList<HashNode<K, V>> bucketArray;

		// Current capacity of array list
		private int numBuckets;

		// Current size of array list
		private int size;

		// Constructor (Initializes capacity, size and empty chains.
		public Map() {
			bucketArray = new ArrayList<>();
			numBuckets = 10;
			size = 0;

			// Create empty chains
			for (int i = 0; i < numBuckets; i++)
				bucketArray.add(null);
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size() == 0;
		}

		private final int hashCode(K key) {
			return Objects.hashCode(key);
		}

		// This implements hash function to find index for the key
		private int getBucketIndex(K key) {
			int hashCode = hashCode(key);
			int index = hashCode % numBuckets;
			// Ternary Operation
			index = index < 0 ? index * -1 : index;

			return index;
		}

		// Method to remove a given key
		public void remove(K key) {
			// Apply hash function to find index for given key
			int bucketIndex = getBucketIndex(key);
			int hashCode = hashCode(key);
			// Get head of chain
			HashNode<K, V> head = bucketArray.get(bucketIndex);

			// If key was not there
			if (head == null)
				return;
			else if (head.next == null && head.key.equals(key) && hashCode == head.hashCode) {
				head = null;
				bucketArray.set(bucketIndex, head); // ******** Added Newly
				return;
			}

			HashNode<K, V> temp = head;
			HashNode<K, V> left = temp;
			HashNode<K, V> right = left.next;

			if (temp.key.equals(key) && hashCode == temp.hashCode) {
				temp = temp.next;
			}

			while (right != null) {

				if (right.key.equals(key) && hashCode == right.hashCode) {
					left.next = right.next;
					break;
				}
				left = left.next;
				right = right.next;
			}

			// Reduce size
			size--;

			bucketArray.set(bucketIndex, temp);

		}

		// Returns value for a key
		public V get(K key) {
			// Find head of chain for given key
			int bucketIndex = getBucketIndex(key);
			int hashCode = hashCode(key);

			HashNode<K, V> head = bucketArray.get(bucketIndex);

			// Search key in chain
			while (head != null) {
				if (head.key.equals(key) && head.hashCode == hashCode)
					return head.value;
				head = head.next;
			}

			// If key not found
			return null;
		}

		// Adds a key value pair to hash
		public void add(K key, V value) {
			// Find head of chain for given key
			int bucketIndex = getBucketIndex(key);
			int hashCode = hashCode(key);

			HashNode<K, V> head = bucketArray.get(bucketIndex);

			// Check if key is already present
			while (head != null) {
				if (head.key.equals(key) && head.hashCode == hashCode) {
					head.value = value;
					return;
				}
				head = head.next;
			}

			// Insert key in chain
			size++;
			head = bucketArray.get(bucketIndex); // head = null;
			HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
			newNode.next = head;
			bucketArray.set(bucketIndex, newNode);

			// If load factor goes beyond threshold, then
			// double hash table size
			if ((1.0 * size) / numBuckets >= 0.7) {
				ArrayList<HashNode<K, V>> temp = bucketArray;
				bucketArray = new ArrayList<>();
				numBuckets = 2 * numBuckets;
				size = 0;

				// Assign null to newly created Buckets
				for (int i = 0; i < numBuckets; i++)
					bucketArray.add(null);

				// Copy old hash table to new Hash table
				for (HashNode<K, V> headNode : temp) {
					while (headNode != null) {
						add(headNode.key, headNode.value);
						headNode = headNode.next;
					}
				}
			}
		}

		public void display() {

			for (HashNode<K, V> head : bucketArray) {
				HashNode<K, V> temp = head;
				if (head != null)
					System.out.print(" [ " + bucketArray.indexOf(head) + " ]  ==> ");
				else
					System.out.println(" [   ]");
				while (temp != null) {
					System.out.print("[ Key: " + temp.key + " Value: " + temp.value + "] ==>");
					temp = temp.next;
				}
				if (head != null)
					System.out.println();
			}
		}

		@Override
		public String toString() {
			return "Map List{" + bucketArray + "}";
		}

	}

