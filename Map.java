import java.util.ArrayList;
import java.util.Objects;

class HashNode<K, V> {
	K key;
	V value;
	final int hashCode;

	HashNode<K, V> next;

	public HashNode(K key, V value, int hashCode) {
		this.key = key;
		this.value = value;
		this.hashCode = hashCode;
	}
}

public class Map<K, V> {

	private ArrayList<HashNode<K, V>> bucketArray;
	private int numBuckets;
	private int size;

	public Map() {
		bucketArray = new ArrayList<>();
		numBuckets = 10;
		size = 0;

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

	private int getBucketIndex(K key) {
		int hashCode = hashCode(key);
		int index = hashCode % numBuckets;

		index = index < 0 ? index * -1 : index;

		return index;
	}

	public void remove(K key) {

		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);

		HashNode<K, V> head = bucketArray.get(bucketIndex);

		if (head == null)
			return;
		else if (head.next == null && head.key.equals(key) && hashCode == head.hashCode) {
			head = null;
			bucketArray.set(bucketIndex, head);
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

		size--;

		bucketArray.set(bucketIndex, temp);

	}

	public V get(K key) {

		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);

		HashNode<K, V> head = bucketArray.get(bucketIndex);

		while (head != null) {
			if (head.key.equals(key) && head.hashCode == hashCode)
				return head.value;
			head = head.next;
		}

		return null;
	}

	public void add(K key, V value) {

		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);

		HashNode<K, V> head = bucketArray.get(bucketIndex);

		while (head != null) {
			if (head.key.equals(key) && head.hashCode == hashCode) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		size++;
		head = bucketArray.get(bucketIndex); // head = null;
		HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);

		if ((1.0 * size) / numBuckets >= 0.7) {
			ArrayList<HashNode<K, V>> temp = bucketArray;
			bucketArray = new ArrayList<>();
			numBuckets = 2 * numBuckets;
			size = 0;

			for (int i = 0; i < numBuckets; i++)
				bucketArray.add(null);

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