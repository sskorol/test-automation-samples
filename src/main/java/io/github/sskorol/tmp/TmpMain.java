package io.github.sskorol.tmp;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.RandomUtils;

import java.util.AbstractMap;

import static io.vavr.API.*;

public class TmpMain {

    public static String getData() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static void main(String[] args) {
        String value = "80971234567";

        Match(value).of(
                Case($("80971234568"), () -> run(() -> System.out.println("full number"))),
                Case($(str -> str.startsWith("8096")), () -> run(() -> System.out.println("starts with 097"))),
                Case($(), str -> run(() -> System.out.println("other: " + str)))
        );

        final AbstractMap.SimpleEntry<Integer, String> entry = new AbstractMap.SimpleEntry<>(1, "some string");
        entry.getKey();
        entry.getValue();

        final Tuple2<Integer, String> tuple2 = Tuple.of(1, "some string");
        final Integer integer = tuple2._1;
        final String s = tuple2._2;

        StreamEx.of("str1", "str2", "str3")
                .map(str -> Tuple.of(str, str + RandomUtils.nextInt()))
                .forEach(t -> System.out.println(t._1 + ", " + t._2));
    }
}
