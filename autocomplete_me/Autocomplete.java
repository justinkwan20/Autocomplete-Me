import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;

// A data type that provides autocomplete functionality for a given set of 
// string and weights, using Term and BinarySearchDeluxe. 
public class Autocomplete {
    private Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new java.lang.NullPointerException();
        }
        this.terms = new Term [terms.length];
        for (int i = 0; i < terms.length; i++) {
            this.terms[i] = terms[i];
        }
        Arrays.sort(this.terms);
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new java.lang.NullPointerException();
        }
        Term t = new Term(prefix);
        Term[] matches = new Term[numberOfMatches(prefix)]; 
        Comparator<Term> preOrder = Term.byPrefixOrder(prefix.length());
        int i;
        i = BinarySearchDeluxe.firstIndexOf(terms, t, preOrder);
        for (int j = 0; j < numberOfMatches(prefix); j++) {
            matches[j] = terms[i+j];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new java.lang.NullPointerException();
        }
        Term t = new Term(prefix);
        Comparator<Term> preOrder = Term.byPrefixOrder(prefix.length());
        int first, last;
        first = BinarySearchDeluxe.firstIndexOf(terms, t, preOrder);
        if (first == -1) {
            return 0;
        }
        last = BinarySearchDeluxe.lastIndexOf(terms, t, preOrder);
        return last - first + 1;
    }
    
    

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query, weight); 
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
