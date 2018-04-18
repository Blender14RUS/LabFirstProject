package com.epam.lab.library.domain;

public class Filter {
    private String select="SELECT * FROM books ";
    private String searchingTitle;
    private boolean searchAll;
    private String authors;
    private int year;

    public Filter(){}

    public Filter(String searchingTitle,boolean showNotAvailable,String sortType){
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
        select+=getSort(sortType);
    }

    private String getSort(String sortType){
        String sort="ORDER BY ";
        if(sortType.equals("alphabet")){
            return sort+"title ASC";
        }
        if(sortType.equals("alphabetRev")){
            return sort+"title DESC";
        }
        if(sortType.equals("year")){
            return sort+"year";
        }
        if(sortType.equals("amountL")){
            return sort+"available ASC";
        }
        if(sortType.equals("amountH")){
            return sort+"available DESC";
        }
        return "";
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