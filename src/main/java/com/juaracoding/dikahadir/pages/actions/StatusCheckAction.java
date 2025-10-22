package com.juaracoding.dikahadir.pages.actions;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.juaracoding.dikahadir.utils.DriverService;

public class StatusCheckAction implements CompositeAction {
    private final List<String> expectedStatuses;
    private final WebDriverWait wait;

    private boolean completed = false;
    private String result = "";

    // Flexible locators
    private final By[] headersLocators = {
        By.xpath("//table//thead//th"),
        By.cssSelector("table thead th"),
        By.cssSelector("th")
    };

    private final By[] nextButtonLocators = {
        By.xpath("//button[@title='Go to next page']//*[name()='svg']"),
        By.xpath("(//*[name()='svg'][@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-vubbuv'])[4]"),
        By.cssSelector("button[aria-label*='next']"),
        By.cssSelector(".pagination button:last-child")
    };

    private final By[] tableBodyLocators = {
        By.xpath("//table//tbody//tr"),
        By.cssSelector("table tbody tr")
    };

    public StatusCheckAction(List<String> expectedStatuses) {
        this.expectedStatuses = expectedStatuses.stream()
                .map(s -> s.trim().toLowerCase())
                .collect(Collectors.toList());
        this.wait = new WebDriverWait(DriverService.getDriver(), Duration.ofSeconds(10));
    }

    @Override
    public void execute() {
        Set<String> foundStatuses = new HashSet<>();

        try {
            waitForTableToLoad();
            int statusColIndex = findStatusColumnIndex();
            if (statusColIndex == -1) throw new RuntimeException("Kolom STATUS tidak ditemukan!");

            int page = 1;
            // Loop semua halaman
            do {
                List<String> pageStatuses = extractStatusesFromCurrentPage(statusColIndex);
                foundStatuses.addAll(pageStatuses);
                System.out.println("Page " + page++ + " → ditemukan: " + pageStatuses);

                if (foundStatuses.containsAll(expectedStatuses)) {
                    System.out.println("Semua expected status sudah ditemukan, stop pagination.");
                    break;
                }
            } while (goToNextPage());

            validateResults(foundStatuses);

        } catch (Exception e) {
            result = "Error during status check: " + e.getMessage();
            throw new RuntimeException(result, e);
        }
    }

    private void waitForTableToLoad() {
        if (!tryFindAny(tableBodyLocators)) {
            throw new RuntimeException("Table tidak ditemukan atau belum load!");
        }
    }

    private int findStatusColumnIndex() {
        for (By locator : headersLocators) {
            List<WebElement> headers = DriverService.getDriver().findElements(locator);
            for (int i = 0; i < headers.size(); i++) {
                String headerText = headers.get(i).getText().trim().toLowerCase();
                if (headerText.contains("status")) return i + 1;
            }
        }
        return -1;
    }

    private List<String> extractStatusesFromCurrentPage(int statusColIndex) {
        String[] statusCellSelectors = {
            "//table//tbody//tr//td[" + statusColIndex + "]//*[self::span or self::div or self::p]",
            "//table//tbody//tr//td[" + statusColIndex + "]"
        };

        for (String selector : statusCellSelectors) {
            List<WebElement> elements = DriverService.getDriver().findElements(By.xpath(selector));
            if (!elements.isEmpty()) {
                return elements.stream()
                    .map(this::extractText)
                    .filter(text -> !text.isEmpty())
                    .map(String::toLowerCase)
                    .distinct()
                    .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    private boolean goToNextPage() throws InterruptedException {
        for (By locator : nextButtonLocators) {
            try {
                WebElement nextBtn = DriverService.getDriver().findElement(locator);
                if (isClickable(nextBtn)) {
                    scrollTo(nextBtn);
                    nextBtn.click();
                    Thread.sleep(1000); // cukup 1 detik
                    waitForTableToLoad();
                    return true;
                }
            } catch (NoSuchElementException ignored) {}
              catch (StaleElementReferenceException ignored) {}
        }
        return false;
    }

    private void validateResults(Set<String> foundStatuses) {
        List<String> missing = expectedStatuses.stream()
                .filter(s -> !foundStatuses.contains(s))
                .collect(Collectors.toList());

        if (!missing.isEmpty()) {
            String errorMsg = String.format(
                "Status tidak lengkap!\nExpected: %s\nFound: %s\nMissing: %s",
                expectedStatuses, foundStatuses, missing
            );
            result = errorMsg;
            Assert.fail(errorMsg);
        }

        completed = true;
        result = "✓ Semua status berhasil ditemukan: " + foundStatuses;
        System.out.println(result);
    }

    private boolean tryFindAny(By[] locators) {
        for (By locator : locators) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                return true;
            } catch (Exception ignored) {}
        }
        return false;
    }

    private boolean isClickable(WebElement element) {
        return element.isDisplayed() && element.isEnabled() &&
               !Optional.ofNullable(element.getAttribute("class")).orElse("").contains("disabled");
    }

    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) DriverService.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private String extractText(WebElement element) {
        String text = element.getText().trim();
        if (!text.isEmpty()) return text;
        return Optional.ofNullable(element.getAttribute("title"))
                .or(() -> Optional.ofNullable(element.getAttribute("data-value")))
                .orElse("");
    }

    @Override
    public boolean isCompleted() { return completed; }

    @Override
    public String getResult() { return result; }
}
