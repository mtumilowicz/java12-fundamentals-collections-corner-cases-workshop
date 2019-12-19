package set

import spock.lang.Specification

class SetMembership_Test extends Specification {

    def 'sorted set contains no two elements (a, b) where compare(a, b) == 0'() {

        given: 'SortedSet with inconsistent-with-equals comparator'
        SortedSet<Person> persons = new TreeSet<>(Comparator.comparing({ it.name }))

        and: 'a1 is equivalent to a2 in the context of this ordering'
        def a1 = new Person(name: 'A', lastName: 'A1')
        def a2 = new Person(name: 'A', lastName: 'A2')
        def b = new Person(name: 'B', lastName: 'B')

        when:
        persons.add(a1)
        persons.add(a2)
        persons.add(b)

        then: 'comparator not only determines order but also membership'
        persons.size() == 2
        persons.collect { it.lastName } == ['A1', 'B']
        and: 'persons size is 2, but contains 3 elements'
        persons.contains(a1)
        persons.contains(b)
        persons.contains(a2)
    }

    def 'equals between two well-defined and real-life set could NOT be symmetric'() {
        given: 'tree with inconsistent-with-equals comparator'
        def tree = new TreeSet<>(String.CASE_INSENSITIVE_ORDER)
        tree.addAll(["MMM", "aaa", "zzz"])

        and: 'hashSet with exact elements as tree'
        def hash = new HashSet<>(tree)

        expect: 'set1.equals(set2) is true if sizes equal and every element of set2 is also contained in set1'
        hash == tree
        tree == hash

        when: 'we replace MMM with its lowercase equivalent mmm'
        hash.remove("MMM")
        hash.add("mmm")

        then:
        hash != tree // hash not contains MMM element that is contained in tree
        Objects.equals(tree, hash) // contrary to java - in groovy: tree.equals(hash) will return false
    }
}

class Person {
    String name
    String lastName
}
