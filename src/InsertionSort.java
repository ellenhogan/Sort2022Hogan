public class InsertionSort extends SortAlgorithm{
    public InsertionSort(AlgorithmDelegate del, int[] array)
    {
        super(del,array);
    }
    public void run()
    {
            int n = array.length;
            for (int i = 1; i < n; ++i) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
                array[j + 1] = key;
                // -------------------------------------------------------------
                // include this in the middle of your algorithm to happen OFTEN.
                delegate.visualizeData(array);
                if (!keepRunning)
                    return;
                // -------------------------------------------------------------
            }
        tellDelegateSortIsComplete();
          }


    }
    




