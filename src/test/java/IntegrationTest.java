import edu.uwb.css143b2020fall.service.Indexer;
import edu.uwb.css143b2020fall.service.IndexerImpl;
import edu.uwb.css143b2020fall.service.Searcher;
import edu.uwb.css143b2020fall.service.SearcherImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private Indexer indexer;
    private Searcher searcher;

    @Before
    public void setUp() {
        indexer = new IndexerImpl();
        searcher = new SearcherImpl();
    }

    @Test
    public void testIntegration() {
        List<TestCase> cases = getTestCase();
        for (TestCase testCase : cases) {
            List<Integer> actual = searcher.search(
                    testCase.target,
                    indexer.index(testCase.documents)
            );
            assertEquals(testCase.expect, actual);
        }
    }

    @Test
    public void testIntegrationEC1() {
        List<TestCase> cases = getTestCaseEC1();
        for (TestCase testCase : cases) {
            List<Integer> actual = searcher.search(
                    testCase.target,
                    indexer.index(testCase.documents)
            );
            assertEquals(testCase.expect, actual);
        }
    }

    @Test
    public void testIntegrationEC2() {
        List<TestCase> cases = getTestCaseEC2();
        for (TestCase testCase : cases) {
            List<Integer> actual = searcher.search(
                    testCase.target,
                    indexer.index(testCase.documents)
            );
            assertEquals(testCase.expect, actual);
        }
    }

    @Test
    public void testIntegrationEC3() {
        List<TestCase> cases = getTestCaseEC3();
        for (TestCase testCase : cases) {
            List<Integer> actual = searcher.search(
                    testCase.target,
                    indexer.index(testCase.documents)
            );
            assertEquals(testCase.expect, actual);
        }
    }


    private List<TestCase> getTestCase() {
        List<String> documents = Util.getDocumentsForIntTest();

        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                new TestCase(
                        documents,
                        "",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "hello world",
                        new ArrayList<>(Arrays.asList(0, 1, 6))
                ),
                new TestCase(
                        documents,
                        "llo wor",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "wor",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "hello",
                        new ArrayList<>(Arrays.asList(0, 1, 2, 4, 5, 6))
                ),
                new TestCase(
                        documents,
                        "just world",
                        new ArrayList<>(Arrays.asList(0))
                ),
                new TestCase(
                        documents,
                        "sunday",
                        new ArrayList<>(Arrays.asList(6))
                ),
                new TestCase(
                        documents,
                        "hello world fun",
                        new ArrayList<>(Arrays.asList(6))
                ),
                new TestCase(
                        documents,
                        "world world fun",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "office",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "ryan murphy",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "new macbook",
                        new ArrayList<>(Arrays.asList(7))
                ),
                new TestCase(
                        documents,
                        "is awesome",
                        new ArrayList<>(Arrays.asList(7))
                )
        ));

        return testCases;
    }

    private List<TestCase> getTestCaseEC1() {
        List<String> documents = new ArrayList<>(
                Arrays.asList(
                        "drink water water drink",
                        "drink",
                        "water water",
                        "juice",
                        "drink juice juice"
                )
        );

        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                new TestCase(
                        documents,
                        "www",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "aaa bbb",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "ccc",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "xxx yyy zzz",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "z",
                        Util.emptyResult()
                )

        ));

        return testCases;
    }

    private List<TestCase> getTestCaseEC2() {
        List<String> documents = new ArrayList<>(
                Arrays.asList(
                        "eat veggies",
                        "veggies",
                        "meat veggies",
                        "eat",
                        "meat veggies meat"
                )
        );

        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                new TestCase(
                        documents,
                        "rr r ss",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "ggg g",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "hh hhh",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "jjj j jj",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "uu u",
                        Util.emptyResult()
                )

        ));

        return testCases;
    }

    private List<TestCase> getTestCaseEC3() {
        List<String> documents = new ArrayList<>(
                Arrays.asList(
                        "cook meat meat veggies",
                        "cook fruit",
                        "fruit meat",
                        "veggies meat fruit cook",
                        "cook cook cook"
                )
        );

        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                new TestCase(
                        documents,
                        "",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "s ss sss ssss",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "eee",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "ee e fff",
                        Util.emptyResult()
                ),
                new TestCase(
                        documents,
                        "l",
                        Util.emptyResult()
                )

        ));

        return testCases;
    }


    private class TestCase {
        private List<String> documents;
        private String target;
        private List<Integer> expect;

        public TestCase(List<String> documents, String target, List<Integer> expect) {
            this.documents = documents;
            this.target = target;
            this.expect = expect;
        }
    }
}