/**
 *  The class is used to represent music titles in form of a title, an
 *  artist, and a price, of types String, String, and int,
 *  respectively.  login attempts.
 *  @author Manfred Kerber
 *  @version 2019-11-04
 */
public interface MusicTitleInterface {

    /**
     *  Standard getter for the title
     *  @return The title of the music title.
     */
    public String getTitle();

    /**
     *  Standard getter for the artist
     *  @return The artist of the music title.
     */
    public String getArtist();

    /**
     *  Standard getter for the price
     *  @return The price of the music title.
     */
    public int getPrice();

    /**
     * Standard toString method to represent the object in a human
     * readable form.
     * @return The object in a human readable form.
     */
    public String toString();
}
