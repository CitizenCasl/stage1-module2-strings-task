package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        int startArgsIndex = signatureString.indexOf('(');
        String argumentsStr = signatureString.substring(startArgsIndex + 1, signatureString.indexOf(')', startArgsIndex));
        String[] arrOfArgs = argumentsStr.split(",\\s");
        for (String arg : arrOfArgs) {
            String[] str = arg.split("\\s");
            arguments.add(new MethodSignature.Argument(str[0].trim(), str[1].trim()));
        }

        String[] otherMods = signatureString.substring(0, startArgsIndex).split("\\s");
        String accessMod = "";
        String returnType;
        String methodName;
        if (otherMods.length == 2) {
            returnType = otherMods[0];
            methodName = otherMods[1];
        } else {
            accessMod = otherMods[0];
            returnType = otherMods[1];
            methodName = otherMods[2];
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessMod);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
