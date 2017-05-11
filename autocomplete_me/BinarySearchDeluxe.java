import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;

// Implements binary search for clients that may want to know the index of 
// either the first or last key in a (sorted) collection of keys.
public class BinarySearchDeluxe {
    // The index of the first key in a[] that equals the search key, 
    // or -1 if no such key.
   
    public static <Key> int firstIndexOf(Key[] a, Key key, 
                                         Comparator<Key> comparator) {
        if (a == null) {
            throw new java.lang.NullPointerException();
        }
        if (key == null) {
            throw new java.lang.NullPointerException();
        }
        if (comparator == null) {
            throw new java.lang.NullPointerException();
        }
        int low = 0;
        int high = a.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comp = comparator.compare(key, a[mid]);
            if (comp < 0) {
                high = mid - 1;
            } else if (comp > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
                index = mid;
            }
        }
        return index;
    }

    // The index of the last key in a[] that equals the search key, 
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, 
                                        Comparator<Key> comparator) {
        if (a == null) {
            throw new java.lang.NullPointerException();
        }
        if (key == null) {
            throw new java.lang.NullPointerException();
        }
        if (comparator == null) {
            throw new java.lang.NullPointerException();
        }
        int low = 0;
        int high = a.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comp = comparator.compare(key, a[mid]);
            if (comp < 0) {
                high = mid - 1;
            } else if (comp > 0) {
                low = mid + 1;
            } else {
                low = mid + 1;
                index = mid;
            }
        }
        return index;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query, weight); 
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println(count);
    }
}
