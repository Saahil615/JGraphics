/**
 * Sorts the vertices of a polygon to create a valid order
 * This is a helper class for the Polygon class
 * Method employed: merge sort
 */
class Sort
{
    void merge(Vertex[] arr, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
        Vertex[] L = new Vertex[n1];
        Vertex[] R = new Vertex[n2];
        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if ((L[i].angle < R[j].angle)||((L[i].angle < R[j].angle)&&(L[i].distance < R[j].distance))) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    void sort(Vertex[] arr, int l, int r)
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
}