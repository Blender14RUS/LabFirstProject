package com.epam.lab.library.domain;

public class Filter {
    private String select="SELECT * FROM books ";
    private String searchingTitle;
    private boolean searchAll;
    private String authors;
    private int year;

    public Filter(){}

    public Filter(String searchingTitle,boolean showNotAvailable){
        boolean hasWhere=false;
        if (!searchingTitle.equals("")){
            select+="WHERE title LIKE '%"+searchingTitle+"%'";
            hasWhere=true;
        }
        if (!showNotAvailable)
            if (hasWhere){
                select+="AND books.available!=0";
            }else {
                select+="WHERE books.available!=0";
                hasWhere=true;
            }
    }


    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getSearchingTitle() {
        return searchingTitle;
    }

    public void setSearchingTitle(String searchingTitle) {
        this.searchingTitle = searchingTitle;
    }

    public boolean isSearchAll() {
        return searchAll;
    }

    public void setSearchAll(boolean searchAll) {
        this.searchAll = searchAll;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}