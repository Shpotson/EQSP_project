package pne;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ArraySlice {

    private final int _begin;
    private final int _end;
    private final int[] _exclude;
    private final char[] _slice;
    ArraySlice(int begin, int end, List<Integer> exclude, List<Character> slice){
        _begin = begin;
        _end = end;
        _exclude = exclude.stream().mapToInt(i->i).toArray();
        _slice = slice.stream().map(Object::toString).collect(Collectors.joining()).toCharArray();
    }

    int GetEnd(){
        return _end;
    }

    int GetBegin(){
        return _begin;
    }

    char[] GetSlice(){
        return _slice;
    }

    boolean IsExcluded(int index){
        return IntStream.of(_exclude).noneMatch(x -> x == index);
    }

}
