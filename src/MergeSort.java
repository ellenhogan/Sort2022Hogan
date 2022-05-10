public class MergeSort extends SortAlgorithm{
    public MergeSort(AlgorithmDelegate del, int[] array)
    {
        super(del,array);
    }

    public void run() {
        sort(array, 0,array.length-1);
        tellDelegateSortIsComplete();

    }
    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
            // -------------------------------------------------------------
            // include this in the middle of your algorithm to happen OFTEN.
            delegate.visualizeData(array);
            if (!keepRunning)
                return;
            // -------------------------------------------------------------
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
            // -------------------------------------------------------------
            // include this in the middle of your algorithm to happen OFTEN.
            delegate.visualizeData(array);
            if (!keepRunning)
                return;
            // -------------------------------------------------------------
        }
        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;

                // -------------------------------------------------------------
                // include this in the middle of your algorithm to happen OFTEN.
                delegate.visualizeData(array);
                if (!keepRunning)
                    return;
                // -------------------------------------------------------------

            } else {
                arr[k] = R[j];
                j++;
                // -------------------------------------------------------------
                // include this in the middle of your algorithm to happen OFTEN.
                delegate.visualizeData(array);
                if (!keepRunning)
                    return;
                // -------------------------------------------------------------

            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            {                // -------------------------------------------------------------
                // include this in the middle of your algorithm to happen OFTEN.
                delegate.visualizeData(array);
                if (!keepRunning)
                    return;
                // -------------------------------------------------------------
            }
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            delegate.visualizeData(array);
            if (!keepRunning)
                return;
        }
    }

        }






