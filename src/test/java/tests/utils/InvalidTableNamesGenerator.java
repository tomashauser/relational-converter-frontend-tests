package tests.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvalidTableNamesGenerator implements ArgumentsProvider {
    private static List<List<Boolean>> generateBooleanPerms(int len, List<Boolean> acc) {
        if (len == 0) {
            return List.of(acc);
        }

        List<Boolean> trueChoice = Stream.concat(acc.stream(), Stream.of(true)).collect(Collectors.toList());
        List<Boolean> falseChoice = Stream.concat(acc.stream(), Stream.of(false)).collect(Collectors.toList());

        List<List<Boolean>> left = generateBooleanPerms(len - 1, trueChoice);
        List<List<Boolean>>  right = generateBooleanPerms(len - 1, falseChoice);

        return Stream.concat(left.stream(), right.stream()).collect(Collectors.toList());
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Function<Boolean, String>> processingFunctions = List.of(
                (x) -> x ? "Abc" : "_ab",
                (x) -> x ? "(" : "",
                (x) -> x ? "a1" : "",
                (x) -> x ? ", a2" : "",
                (x) -> x ? ")" : ""
        );

        List<List<Boolean>> perms = generateBooleanPerms(5, new ArrayList<>()).stream().filter(perm ->
                !perm.equals(List.of(true, true, true, true, true))
             && !perm.equals(List.of(true, true, true, false, true))).collect(Collectors.toList());

        return perms.stream().map(perm -> {
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < perm.size(); i++) {
                ret.append(processingFunctions.get(i).apply(perm.get(i)));
            }

            return Arguments.of(ret.toString());
        });
    }
}
