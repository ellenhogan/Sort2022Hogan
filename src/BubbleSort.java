public class BubbleSort extends SortAlgorithm
{
    public BubbleSort(AlgorithmDelegate del, int[] array)
    {
        super(del,array);
    }

    public void run() {
        int N = array.length;
        for (int j = N-1; j>=0; j--)
        {
            for(int i = 0; i<j; i++)
            {
                if (array[i]> array[i+1])
                {
                    swap(i,i+1);
                }
                // -------------------------------------------------------------
                // include this in the middle of your algorithm to happen OFTEN.
                delegate.visualizeData(array);
                if (!keepRunning)
                    return;
                // -------------------------------------------------------------
            }

        }






        tellDelegateSortIsComplete();
    }
    public void swap(int i, int j)
    {
        int temp = array[i];
        array[i] = array [j];
        array[j] = temp;
    }

}
