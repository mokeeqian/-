public class MusicTitle implements MusicTitleInterface{

    /**
     * member variables
     */
    private String title;
    private String artist;
    private int price;

    /**
     * Constructor
     * @param title
     * @param artist
     * @param price
     */
    public MusicTitle(String title, String artist, int price) {
        this.title = title;
        this.artist = artist;
        this.price = price;
    }

    /**
     * Setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for artist
     * @param artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Setter for price
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter for title
     * @return
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Getter for artist
     * @return
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * Getter for price
     * @return
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
