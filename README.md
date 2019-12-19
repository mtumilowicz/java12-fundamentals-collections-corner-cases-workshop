# java12-collections-corner-cases-collection-views
* https://stuartmarks.files.wordpress.com/2019/09/collectionscornercases.pdf
* [Collections Corner Cases by Stuart Marks](https://www.youtube.com/watch?v=OXdm5BzQ8mI)
* if you sort Arrays.asList(array) the array is also sorted
    * Arrays.asList(array) - proxy to array
* view collections don't contain their own elements
    * elements are stored elsewhere
* many comparators for sorting are inconsistent with equals
* IdentityHashMap keys
  * membership determined by == , inconsistent with equals
* Surprisingly easy to create comparators that are
  inconsistent with equals