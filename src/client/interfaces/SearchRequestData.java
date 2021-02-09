package client.interfaces;

public class SearchRequestData {
    String search_data;

    public SearchRequestData(String search_data) {
        this.search_data = search_data;
    }

    public String getSearch_data() {
        return search_data;
    }

    public void setSearch_data(String search_data) {
        this.search_data = search_data;
    }
}
