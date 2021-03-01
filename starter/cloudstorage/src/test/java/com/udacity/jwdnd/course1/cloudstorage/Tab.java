package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class Tab<RowType> {
    static int TIME_OUT_IN_SECONDS = 3;
    protected final WebDriver driver;
    protected List<RowType> rows;

    public abstract List<RowType> createRows();

    public List<RowType> getRows() {
        return this.rows;
    }

    public Tab(WebDriver driver, String id) {
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.rows = this.createRows();
    }

    public RowType getLastRow() {
        int noOfRows = this.rows.size();
        if (noOfRows == 0) {
            return null;
        }
        return this.rows.get(noOfRows - 1);
    }
}
