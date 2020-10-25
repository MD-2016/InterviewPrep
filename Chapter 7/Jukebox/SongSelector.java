package Chapter_7.Jukebox;

public class SongSelector
{
    private Song current;
    public SongSelector(Song s)
    {
        current = s;
    }

    public void setSong(Song s)
    {
        current = s;
    }

    public Song getCurrentSong()
    {
        return current;
    }

}
