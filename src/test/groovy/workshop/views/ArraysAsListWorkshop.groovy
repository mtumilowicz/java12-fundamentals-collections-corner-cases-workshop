package workshop.views

import spock.lang.Specification

class ArraysAsListWorkshop extends Specification {

    // Objects stored into instance fields are not shared between feature methods.
    // Instead, every feature method gets its own object.
    Integer[] array = [ 1, 2, 3 ]

    def 'if you modify underlying array - Arrays.asList is modified as well'() {
        given:
        List<Integer> list = Arrays.asList(array)

        when:
        array[0] = -1

        then:
        list == [-1, 2, 3]
    }

    def 'if you modify Arrays.asList - underlying array is modified as well'() {
        given:
        List<Integer> list = Arrays.asList(array)

        when:
        list.set(0, -1)

        then:
        array == [-1, 2, 3] as Integer[]
    }

    def 'you cannot add elements to Arrays.asList'() {
        given:
        List<Integer> list = Arrays.asList(array)

        when:
        list.add(4)

        then:
        thrown(UnsupportedOperationException)
    }

    def 'you cannot remove elements to Arrays.asList'() {
        given:
        List<Integer> list = Arrays.asList(array)

        when:
        list.remove(4)

        then:
        thrown(UnsupportedOperationException)
    }
}
