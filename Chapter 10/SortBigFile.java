package Chapter_10;

public class SortBigFile
{
    /* Just an info file
     * dont bring all the data into memory. Only bring part of the data into memory
     * Do this by dividing the file into x MB chunks, where x is the amount of memory we have available
     * Each chunk is sorted separately then saved backed into the file system
     * Once all chunks are sorted, merge chunks one by one until file is complete.
     * This is called External Sort.
     */
}
