package answers.views

import spock.lang.Specification

import java.util.stream.Collectors

class MapAlgorithmProblemAnswer extends Specification {
    /*
    Given two maps mapA and mapB, find the keys of mapB
    corresponding to values that occur in both maps
    var mapA = Map.of(1, 'a', 2, 'b', 3, 'c')
    var mapB = Map.of(5, 'a', 6, 'd', 7, 'c')

    Result should be a answers.set containing: [5, 7]
     */

    def mapA = Map.of(1, 'a', 2, 'b', 3, 'c')
    def mapB = Map.of(5, "a", 6, 'd', 7, 'c')

    def 'find using stream API'() {
        given:
        def valuesOfA = mapA.values() as Set

        when:
        Set<Integer> result = mapB.entrySet().stream()
                .filter { valuesOfA.contains(it.value) }
                .map { it.key }
                .collect(Collectors.toList())

        then:
        result == [5, 7] as Set
    }

    def 'find using collection API'() {
        given:
        def valuesOfA = new HashSet<>(mapA.values())
        def copyOfB = new HashMap<>(mapB)
        def valuesOfB = copyOfB.values()

        when:
        valuesOfB.retainAll(valuesOfA)

        then:
        copyOfB.keySet() == [5, 7] as Set
    }

}
