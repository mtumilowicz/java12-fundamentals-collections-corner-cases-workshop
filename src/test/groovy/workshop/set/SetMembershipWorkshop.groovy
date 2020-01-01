package workshop.set

import person.Person
import spock.lang.Specification

class SetMembershipWorkshop extends Specification {

    def 'sorted answers.set contains no two elements (a, b) where compare(a, b) == 0'() {

        given: 'SortedSet with inconsistent-with-equals comparator'
        SortedSet<Person> persons = null // hint: TreeSet, compare by name

        and: 'a1 is equivalent to a2 in the context of this ordering'
        def a1 = new Person(name: 'A', lastName: 'A1')
        def a2 = new Person(name: 'A', lastName: 'A2')
        def b = new Person(name: 'B', lastName: 'B')

        when:
        persons << a1 << a2 << b

        then: 'comparator not only determines order but also membership'
        persons.size() == 2
        persons.collect { it.lastName } == ['A1', 'B']

        and: 'persons size is 2, but contains 3 elements'
        persons.contains a1
        persons.contains b
        persons.contains a2
    }

    def 'equals between two well-defined and real-life answers.set could NOT be symmetric'() {

        given: 'tree with inconsistent-with-equals comparator'
        def tree = null // hint: TreeSet, case insensitive
        tree << 'MMM' << 'aaa' << 'zzz'

        and: 'hashSet with exact elements as tree'
        def hash = null // hint: create hashSet from tree

        expect: 'set1.equals(set2) is true if sizes equal and every element of set2 is also contained in set1'
        1 == 1 // verify that hash equals tree
        1 == 1 // verify that tree equals hash

        when: 'we replace MMM with its lowercase equivalent mmm'
        hash.remove 'MMM'
        hash << 'mmm'

        then: 'hash not contains MMM element that is contained in tree'
        1 != 1 // verify that hash is not equal to tree
        1 == 1 // verify that tree is equal to hash, hint: contrary to java - in groovy: tree.equals(hash) will return false
    }

    def 'sorted list is often not easily transformable into sorted tree'() {
        given: 'A, C have the same salary'
        def a = new Person(name: 'A', salary: 1000)
        def b = new Person(name: 'B', salary: 1500)
        def c = new Person(name: 'C', salary: 1000)
        def persons = [a, b, c]
        Comparator<Person> salaryOrder = null // hint: by salary

        expect: 'comparator is not consistent with equals'
        a != c
        salaryOrder.compare(a, c) == 0

        when: 'sorted by salary'
        // sort persons using salaryOrder, hint: Collections.sort

        then: 'a = c < b'
        persons == [a, c, b]

        when: 'suppose your manager asks you to maintain a data structure in the same order'
        def tree = null // hint: TreeSet, hint 2: bad approach!
        tree.addAll(persons)

        then: 'only a and b, duplicates elided!'
        tree.collect {it.name} == ['A', 'B']
    }
}

