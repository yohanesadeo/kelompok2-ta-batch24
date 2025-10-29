package com.juaracoding.dikahadir.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features = {
    "src/test/resources/features/absenpoints/SearchAbsenPoint.feature",
    "src/test/resources/features/absenpoints/ResetAbsenPoint.feature",
    "src/test/resources/features/absenpoints/HapusAbsenPoint.feature",
     "src/test/resources/features/absenpoints/EditAbsenPoint.feature",
     "src/test/resources/features/absenpoints/PaginationAbsenPoint.feature",
    "src/test/resources/features/absenpoints/TambahAbsenPoint.feature",
    "src/test/resources/features/absenpoints/TambahNegativeShifting.feature"
}, glue = {
    "com.juaracoding",
    "com.juaracoding.hadir.definitions.absenpoints",
}, plugin = {
    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
    "json:target/cucumber-reporting/reports.json",
    "json:target/cucumber.json", 
    "pretty", 
    "html:target/cucumber-reporting/reports.html"
})

public class RunnerAbsenPoint extends AbstractTestNGCucumberTests{
    
}
