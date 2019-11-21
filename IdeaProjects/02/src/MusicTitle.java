public class MusicTitle implements MusicTitleInterface {
    private String title;
    private String artist;
    private int price;


    public MusicTitle(String title, String artist, int price) {
        this.title = title;
        this.artist = artist;
        this.price = price;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Standard getter for the title
     *
     * @return The title of the music title.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Standard getter for the artist
     *
     * @return The artist of the music title.
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * Standard getter for the price
     *
     * @return The price of the music title.
     */
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "MusicTitle{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }
}
