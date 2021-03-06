package workshop.views

import spock.lang.Specification

class MapAndItsCollectionsWorkshop extends Specification {

    def originalMap = Map.of(
            1, 'a',
            2, 'b',
            3, 'c',
            4, 'd',
            5, 'e'
    )
    def map = new HashMap<>(originalMap)

    def 'if you modify map - keySet is modified as well'() {
        given:
        def keySet = map.keySet()

        expect:
        !keySet.contains(6)

        when:
        map // 6 -> 'f'

        then:
        keySet.contains(6)
    }

    def 'if you modify map - values are modified as well'() {
        given:
        def values = map.values()

        expect:
        !values.contains('f')

        when:
        map // 6 -> 'f'

        then:
        values.contains('f')
    }

    def 'if you modify map - entrySet is modified as well'() {
        given:
        def entrySet = map.entrySet()

        expect:
        !entrySet.contains(Map.entry(6, 'f'))

        when:
        map // 6 -> 'f'

        then:
        entrySet.contains(Map.entry(6, 'f'))
    }

    def 'if you remove key from keySet - entry is removed from map as well'() {
        given:
        def keySet = map.keySet()

        expect:
        map.containsKey(1)

        when:
        keySet // remove 1

        then:
        !map.containsKey(1)
    }

    def 'if you remove value from values - entry is removed from map as well'() {
        given:
        def values = map.values()

        expect:
        map.containsValue('a')

        when:
        values // remove 'a'

        then:
        !map.containsValue('a')
    }

    def 'if you remove entry from entry answers.set - entry is removed from map as well'() {
        given:
        def entries = map.entrySet()

        expect:
        map.containsKey(1)
        map.containsValue('a')

        when:
        entries // remove entry 1, 'a'

        then:
        !map.containsKey(1)
        !map.containsValue('a')
    }

    def 'you cannot add element to keySet - UnsupportedOperationException'() {
        given:
        def keySet = map.keySet()

        when:
        keySet // try to add to keySet

        then:
        thrown(UnsupportedOperationException)
    }

    def 'if you modify values - map is modified as well'() {
        given:
        def values = map.values()

        when:
        values // try to add to values

        then:
        thrown(UnsupportedOperationException)
    }

    def 'if you modify entrySet - map is modified as well'() {
        given:
        def entrySet = map.entrySet()

        when:
        entrySet // try to add to entrySet

        then:
        thrown(UnsupportedOperationException)
    }
}
