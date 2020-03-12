package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class TestRunner {

    @Karate.Test
    Karate testAll(){
        Results results = Runner.parallel(getClass(), 1);
        TestRunner.generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
        return new Karate().relativeTo(getClass());

    }

/*    @Karate.Test
    Karate testAll(){

        Results results = Runner.parallel(getClass(), 5);
        TestRunner.generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
        return new Karate().relativeTo(getClass());
    }*/

/*
    @Karate.Test
    Karate testBooks(){
        return new Karate().feature("BookTest").relativeTo(getClass());
    }*/

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "karateSpringBoot");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
