package example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;


public class CalculatorTest {

    Calculator cut = new Calculator();

    @TestFactory
    Stream<DynamicTest> addition() {
        return DataSet.parseRuleFile("addition-rules.txt")
            .map(dataSet -> dynamicTest(getAdditionDisplayName(dataSet), () -> {
                int result = cut.add(dataSet.getValueA(), dataSet.getValueB());
                assertThat(result).isEqualTo(dataSet.getExpectedResult());
            }));
    }

    String getAdditionDisplayName(DataSet dataSet) {
        return dataSet.getValueA() + " + " + dataSet.getValueB() + " = " + dataSet.getExpectedResult();
    }

    @TestFactory
    Stream<DynamicTest> subtraction() {
        return DataSet.parseRuleFile("subtraction-rules.txt")
            .map(dataSet -> dynamicTest(getSubtractionDisplayName(dataSet), () -> {
                int result = cut.subtract(dataSet.getValueA(), dataSet.getValueB());
                assertThat(result).isEqualTo(dataSet.getExpectedResult());
            }));
    }

    String getSubtractionDisplayName(DataSet dataSet) {
        return dataSet.getValueA() + " - " + dataSet.getValueB() + " = " + dataSet.getExpectedResult();
    }

    @Test
    void someOtherTest(){
        // Because IntelliJ is not detecting @TestFactory tests, yet.
    }

}
