package com.juaracoding.dikahadir.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {
    "src/test/resources/features/shiftings/SearchShifting.feature",
    "src/test/resources/features/shiftings/ResetShifting.feature",
    "src/test/resources/features/shiftings/HapusShifting.feature",
    "src/test/resources/features/shiftings/EditShifting.feature",
    "src/test/resources/features/shiftings/PaginationShifting.feature",
    "src/test/resources/features/shiftings/TambahShifting.feature",
    "src/test/resources/features/shiftings/TambahNegativeShifting.feature",  
}, glue = {
    "com.juaracoding",
    "com.juaracoding.hadir.definitions.shifting",
}, plugin = {
    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
    "json:target/cucumber-reporting/reports.json",
    "json:target/cucumber.json", 
    "pretty", 
    "html:target/cucumber-reporting/reports.html"
})
public class RunnerShifting extends AbstractTestNGCucumberTests{
    
}
