public class QuickSort extends SortAlgorithm
{
    public QuickSort(AlgorithmDelegate del, int[] array)
    {
        super(del,array);
    }

   public void run()
   {
       quickSort(array, 0, array.length-1);
   }

         void swap(int[] arr, int i, int j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

     int partition(int[] arr, int low, int high)
        {
            int pivot = arr[high];
            int i = (low - 1);
            for(int j = low; j <= high - 1; j++)
            {

                if (arr[j] < pivot)
                {
                    i++;
                    swap(arr, i, j);
                }

            }
            swap(arr, i + 1, high);

            return (i + 1);
        }

    void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {

            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            // -------------------------------------------------------------
            // include this in the middle of your algorithm to happen OFTEN.
            delegate.visualizeData(array);
            if (!keepRunning)
                return;
            // -------------------------------------------------------------
            quickSort(arr, pi + 1, high);
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






