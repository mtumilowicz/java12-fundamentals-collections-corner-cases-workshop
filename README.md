[![Build Status](https://travis-ci.com/mtumilowicz/java12-fundamentals-collections-corner-cases-workshop.svg?branch=master)](https://travis-ci.com/mtumilowicz/java12-fundamentals-collections-corner-cases-workshop)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# java12-fundamentals-collections-corner-cases-workshop
* references
    * [Collections Corner Cases by Stuart Marks](https://www.youtube.com/watch?v=OXdm5BzQ8mI)

## preface
* goals of this workshop: show some corner cases concerning collections
    * connection between array and `Arrays.asList(array)`
    * connection between map and its collections: entrySet, keySet and values
    * connection between comparator and equals in context of sets
* workshop: `workshop` package, answers: `answers` package

## project description
* view collections don't contain their own elements - elements are stored elsewhere
* `Arrays.asList(array)` - proxy to array
    * e.g. if you sort `Arrays.asList(array)` the array is also sorted
* entrySet, keySet, values are mutable view collections connected to the map
    * note that you can remove element from it (and it affects the map) but you cannot add element (exception)
* comparator-equals contract
    * a comparator C on a set of elements S is said to be consistent with equals if and only 
    if `C.compare(e1, e2)==0` has the same boolean value as `e1.equals(e2)` for every e1 and e2 in S
    * it is surprisingly easy to create comparators that are inconsistent with equals
        * e.g. comparing strings ignoring case
    * many comparators for sorting are inconsistent with equals (salary)
    * there exists structures in jdk that are internally inconsistent with equals
        * `IdentityHashMap` (membership determined by `==`) 
