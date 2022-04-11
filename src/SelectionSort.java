public class SelectionSort extends SortAlgorithm
{
    public SelectionSort(AlgorithmDelegate del, int[] array)
    {
        super(del,array);
    }

    public void run()
    {
        int n = array.length;
        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (array[j] < array[min_idx])
                    min_idx = j;
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
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


